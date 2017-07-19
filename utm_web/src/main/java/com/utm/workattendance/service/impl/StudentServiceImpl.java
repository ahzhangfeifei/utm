package com.utm.workattendance.service.impl;

import com.utm.basic.dao.IBasicDao;
import com.utm.basic.entity.PageModel;
import com.utm.basic.service.impl.BasicService;
import com.utm.workattendance.dao.IStudentDao;
import com.utm.workattendance.dao.IWorkattenceDao;
import com.utm.workattendance.entity.StudentEntity;
import com.utm.workattendance.entity.WorkattenceEntity;
import com.utm.workattendance.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;


public class StudentServiceImpl extends BasicService<StudentEntity,String> implements IStudentService {

    private IStudentDao studentDao;

    @Override
    @Autowired
    @Qualifier("studentDao")    //搜索Qualifier，参考百度百科，就明白这里的意思了
    public void setBaseDao(IBasicDao<StudentEntity, String> studentDao) {
        this.baseDao = studentDao;
        this.studentDao = (IStudentDao) studentDao;
    }

    public void setWorkattenceDao(IWorkattenceDao workattenceDao) {
        this.workattenceDao = workattenceDao;
    }

    @Resource
    private IWorkattenceDao workattenceDao;


    @Override
    public void deletePersonAndWorkattenceByArray(String[] ids)
    {
        for(int i=0;i<ids.length;i++)
        {
            this.deletePersonAndWorkattenceById(ids[i]);
        }
    }

    @Override
    public void deletePersonAndWorkattenceById(String id) {
        StudentEntity studentEntity= studentDao.get(id);

        List<WorkattenceEntity> workattenceEntityList=(List<WorkattenceEntity>)studentEntity.getWorkattencesById();
        if(workattenceEntityList!=null && workattenceEntityList.size()>0)
        {
            String[] ids=new String[workattenceEntityList.size()];
            for(int i=0;i<workattenceEntityList.size();i++)
            {
                ids[i]=workattenceEntityList.get(i).getId();
            }

            workattenceDao.deleteArray(ids);
        }

        studentDao.deleteObject(studentEntity);
    }


    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public StudentEntity checkStudent(StudentEntity studentEntity) {
        LinkedHashMap<Object, Object> equalFields =new  LinkedHashMap<Object, Object>();
        equalFields.put("name",studentEntity.getName());
        equalFields.put("state","是");
        equalFields.put("password",studentEntity.getPassword());
        return studentDao.get(equalFields,null,null,null,null);
    }

    @Override
    public StudentEntity getStudent(StudentEntity studentEntity) {
        LinkedHashMap<Object, Object> equalFields =new  LinkedHashMap<Object, Object>();
        equalFields.put("code",studentEntity.getCode());
        equalFields.put("state","是");
        return studentDao.get(equalFields,null,null,null,null);
    }

    @Override
    public PageModel<StudentEntity> queryStudents(StudentEntity studentEntity,PageModel<StudentEntity> pageModel,String dateBegin,String dateEnd) {
        // 初始化Like查询条件承载体
        LinkedHashMap<String, String> likeFields = null;
        // 初始化Like查询条件
        if (studentEntity != null) {
            likeFields = new LinkedHashMap<String, String>();
            if (studentEntity.getCode() != null && !"".equals(studentEntity.getCode())) {
                // 卡号
                likeFields.put("code", studentEntity.getCode());
            }

            if (studentEntity.getName() != null && !"".equals(studentEntity.getName())) {
                // 用户名称
                likeFields.put("name", studentEntity.getName());
            }

            if (studentEntity.getSchool() != null && !"".equals(studentEntity.getSchool())) {
                //学校
                likeFields.put("school", studentEntity.getSchool());
            }

            if (studentEntity.getState() != null && !"".equals(studentEntity.getState())) {
                //是否在读
                likeFields.put("state", studentEntity.getState());
            }

        }

        String whereHql=null;

        if((dateBegin!=null && !"".equals(dateBegin)) && (dateEnd!=null && !"".equals(dateEnd)))
        {
            whereHql="where cast(o.registtime as date) >= cast('"+dateBegin+"' as date) and  cast(o.registtime as date) <= cast('"+dateEnd+"' as date)";
        }


        LinkedHashMap<String, String> orderByFields =new  LinkedHashMap<String, String>();
        orderByFields.put("code", "DESC");
        return studentDao.queryPageModel(null,null,likeFields,null,orderByFields,whereHql,pageModel);
    }


    @Override
    public List<StudentEntity> findStudentList(StudentEntity studentEntity,String dateBegin,String dateEnd) {
        // 初始化Like查询条件承载体
        LinkedHashMap<String, String> likeFields = null;
        // 初始化Like查询条件
        if (studentEntity != null) {
            likeFields = new LinkedHashMap<String, String>();
            if (studentEntity.getCode() != null && !"".equals(studentEntity.getCode())) {
                // 卡号
                likeFields.put("code", studentEntity.getCode());
            }

            if (studentEntity.getName() != null && !"".equals(studentEntity.getName())) {
                // 用户名称
                likeFields.put("name", studentEntity.getName());
            }

            if (studentEntity.getSchool() != null && !"".equals(studentEntity.getSchool())) {
                //学校
                likeFields.put("school", studentEntity.getSchool());
            }

            if (studentEntity.getState() != null && !"".equals(studentEntity.getState())) {
                //是否在读
                likeFields.put("state", studentEntity.getState());
            }

        }

        String whereHql=null;

        if((dateBegin!=null && !"".equals(dateBegin)) && (dateEnd!=null && !"".equals(dateEnd)))
        {
            whereHql="where cast(o.registtime as date) >= cast('"+dateBegin+"' as date) and  cast(o.registtime as date) <= cast('"+dateEnd+"' as date)";
        }


        LinkedHashMap<String, String> orderByFields =new  LinkedHashMap<String, String>();
        orderByFields.put("code", "DESC");
        return studentDao.findResultList(null,null,likeFields,null,orderByFields,whereHql);
    }

}
