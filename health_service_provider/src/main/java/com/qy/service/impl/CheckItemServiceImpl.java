package com.qy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qy.dao.CheckItemDao;
import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.pojo.CheckItem;
import com.qy.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务 你的zk在哪
 */

@Service(interfaceClass = CheckItemService.class)
@Transactional // 事务注解，加了之后，上面service注解必须加括号中的内容
public class CheckItemServiceImpl implements CheckItemService {

    // 注入dao对象
    @Autowired
    private CheckItemDao checkItemDao;


    // ============================检查项相关============================================================

    // 测试类
    @Override
    public String check(String msg) {
        return "service : " + msg;
    }

    // 添加检查项目进数据库
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    // 检查项分页查询
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        //页码
        Integer currentPage = queryPageBean.getCurrentPage();
        //每页记录数
        Integer pageSize = queryPageBean.getPageSize();
        //查询条件
        String queryString = queryPageBean.getQueryString();

        // 完成分页查询,基于mybatis框架提供的分页助手插件完成,限制每页10条
        // select * from t_checkitem limit 0,10
        PageHelper.startPage(currentPage,pageSize);
        // 查询数据库
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        // 获取总记录数
        long total = page.getTotal();
        // 当前页结果
        List<CheckItem> rows = page.getResult();

        return new  PageResult(total,rows);
    }

    // 根据id删除检查项
    @Override
    public void deleteById(Integer id) {
        // 查询当前检查项是否和检查组关联
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            //当前检查项被引用，不能删除
            throw new RuntimeException("当前检查项被引用，不能删除");
        }
        checkItemDao.deleteById(id);
    }

    // 编辑检查项
    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    // 根据id查询检查项
    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }


    // ============================检查组相关============================================================

    // 查询所有检查项
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }


}
