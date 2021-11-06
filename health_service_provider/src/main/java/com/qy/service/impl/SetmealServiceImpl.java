package com.qy.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qy.constant.RedisConstant;
import com.qy.dao.SetmealDao;
import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.pojo.Setmeal;
import com.qy.service.SetmealService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 体检套餐服务实现类
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${out_put_path}")//从属性文件读取输出目录的路径
    private String outputpath ;

    //新增套餐
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        if(checkgroupIds != null && checkgroupIds.length > 0){
            //绑定套餐和检查组的多对多关系
            setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
        }
        //将图片名称保存到Redis
        savePic2Redis(setmeal.getImg());

        // 当添加套餐后需要重新生成静态页面(套餐列表页面，套餐详情页面 )
        generateMobileStaticHtml();

    }

    // 用于生成静态页面
    public void generateHtml(String templateName, String htmlPageName, Map map){
        Configuration configuration = freeMarkerConfigurer.getConfiguration();// 获取配置对象
        Writer out = null;
        try {
            Template template = configuration.getTemplate(templateName);
            // 构造输出流
            out = new FileWriter(new File(outputpath + "/" + htmlPageName));
            // 输出文件
            template.process(map,out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //生成当前方法所需的静态页面
    public void generateMobileStaticHtml() {
        // 在生成静态页面之前需要查询数据
        List<Setmeal> list = setmealDao.findAll();

        // 需要生成套餐列表静态页面
        generateMobileSetmealListHtml(list);

        // 需要生成套餐详情静态页面
        generateMobileSetmealDetailHtml(list);

    }

    //生成套餐列表静态页面
    public void generateMobileSetmealListHtml(List<Setmeal> list) {
        Map map = new HashMap<>();
        // 为模板提供数据,用于生成静态页面
        map.put("setmealList", list);
        this.generateHtml("mobile_setmeal.ftl","m_setmeal.html",map);
    }

    //生成套餐详情静态页面（多个）
    public void generateMobileSetmealDetailHtml(List<Setmeal> list) {
        for (Setmeal setmeal : list) {
            Map map = new HashMap();
            map.put("setmeal", setmealDao.findById(setmeal.getId()));
            this.generateHtml("mobile_setmeal_detail.ftl",
                    "setmeal_detail_"+setmeal.getId()+".html",
                    map);
        }
    }

    // 分页检查
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);// 分页插件，会在执行sql之前将分页关键字追加到SQL后面
        Page<Setmeal> page = setmealDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    // 查询套餐中所有图片名字
    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    // 通过id查询套餐详情(套餐基本信息、套餐对应的检查组信息.、检查组对应的检查项信息.)
    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    // 查询套餐总数
    @Override
    public List<Map<String, Object>> findSetmealCount() {
        return setmealDao.findSetmealCount();
    }

    //绑定套餐和检查组的多对多关系
    private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
        for (Integer checkgroupId : checkgroupIds) {
            Map<String,Integer> map = new HashMap<>();
            map.put("setmeal_id",id);
            map.put("checkgroup_id",checkgroupId);
            setmealDao.setSetmealAndCheckGroup(map);
        }
    }

    //将图片名称保存到Redis
    private void savePic2Redis(String pic){
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pic);
    }
}