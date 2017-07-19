package com.utm.user.service;

import com.utm.basic.service.IBasicService;
import com.utm.basic.entity.PageModel;
import com.utm.user.entity.UserEntity;

public interface IUserService extends IBasicService<UserEntity,String>{

    public UserEntity getUser(UserEntity userEntity);

    public PageModel<UserEntity> queryUsers(UserEntity userEntity,PageModel<UserEntity> pageModel);
}
