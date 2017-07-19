package com.utm.user.dao.impl;

import com.utm.basic.dao.impl.HibernateBasicDao;
import com.utm.user.dao.IUserDao;

import com.utm.user.entity.UserEntity;

public class UserDaoImpHibernate extends HibernateBasicDao<UserEntity,String> implements IUserDao {


}
