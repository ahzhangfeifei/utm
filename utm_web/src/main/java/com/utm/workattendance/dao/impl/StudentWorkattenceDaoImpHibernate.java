package com.utm.workattendance.dao.impl;

import com.utm.basic.dao.impl.HibernateBasicDao;
import com.utm.workattendance.dao.IStudentWorkattenceDao;
import com.utm.workattendance.dao.IWorkattenceDao;
import com.utm.workattendance.entity.WorkattenceEntity;

/**
 * @Description :考勤记录管理
 */
public class StudentWorkattenceDaoImpHibernate extends HibernateBasicDao<WorkattenceEntity,String> implements IStudentWorkattenceDao {


}
