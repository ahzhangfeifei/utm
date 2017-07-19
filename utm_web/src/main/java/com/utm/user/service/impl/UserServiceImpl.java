package com.utm.user.service.impl;

import com.utm.basic.service.impl.BasicService;
import com.utm.basic.dao.IBasicDao;
import com.utm.basic.entity.PageModel;
import com.utm.user.dao.IUserDao;
import com.utm.user.entity.UserEntity;
import com.utm.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.LinkedHashMap;

public class UserServiceImpl extends BasicService<UserEntity,String> implements IUserService {

    private IUserDao userDao;

    @Override
    @Autowired
    @Qualifier("userDao")    //搜索Qualifier，参考百度百科，就明白这里的意思了
    public void setBaseDao(IBasicDao<UserEntity, String> userDao) {
        this.baseDao = userDao;
        this.userDao = (IUserDao) userDao;
    }

    @Override
    public UserEntity getUser(UserEntity userEntity) {
        LinkedHashMap<Object, Object> equalFields =new  LinkedHashMap<Object, Object>();
        equalFields.put("name", userEntity.getName());
        equalFields.put("password", userEntity.getPassword());
        return userDao.get(equalFields,null,null,null,null);
    }

    @Override
    public PageModel<UserEntity> queryUsers(UserEntity userEntity,PageModel<UserEntity> pageModel) {
        // 初始化Like查询条件承载体
        LinkedHashMap<String, String> likeFields = null;

        // 初始化Like查询条件
        if (userEntity != null) {
            if (!"".equals(userEntity.getName()) && userEntity.getName() != null) {
                // 用户名称
                likeFields = new LinkedHashMap<String, String>();
                likeFields.put("name", userEntity.getName());
            }
        }

        LinkedHashMap<String, String> orderByFields =new  LinkedHashMap<String, String>();
        orderByFields.put("name", "DESC");
        return userDao.queryPageModelByLike(likeFields,pageModel,orderByFields);
    }
}
