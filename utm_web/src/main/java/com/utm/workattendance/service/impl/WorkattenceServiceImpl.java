package com.utm.workattendance.service.impl;

import com.utm.basic.service.impl.BasicService;
import com.utm.basic.dao.IBasicDao;
import com.utm.basic.entity.PageModel;
import com.utm.workattendance.dao.IWorkattenceDao;
import com.utm.workattendance.entity.WorkattenceEntity;
import com.utm.workattendance.service.IWorkattenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description :考勤记录管理
 */
public class WorkattenceServiceImpl extends BasicService<WorkattenceEntity,String> implements IWorkattenceService {

    private IWorkattenceDao workattenceDao;

    @Override
    @Autowired
    @Qualifier("workattenceDao")    //搜索Qualifier，参考百度百科，就明白这里的意思了
    public void setBaseDao(IBasicDao<WorkattenceEntity, String> workattenceDao) {
        this.baseDao = workattenceDao;
        this.workattenceDao = (IWorkattenceDao) workattenceDao;
    }

    @Override
    public WorkattenceEntity getWorkattence(WorkattenceEntity workattenceEntity) {
        LinkedHashMap<Object, Object> equalFields =new  LinkedHashMap<Object, Object>();
     
        return workattenceDao.get(equalFields,null,null,null,null);
    }


    @Override
    public PageModel<WorkattenceEntity> queryStudentWorkattences(WorkattenceEntity workattenceEntity,PageModel<WorkattenceEntity> pageModel,String dateBegin,String dateEnd) {
        // 初始化Like查询条件承载体
        LinkedHashMap<String, String> likeFields = new LinkedHashMap<String, String>();
        //教师类别
        likeFields.put("type","S");

        // 初始化Like查询条件
        if (workattenceEntity != null) {

            if (workattenceEntity.getStudentByStudentid().getCode() != null && !"".equals(workattenceEntity.getStudentByStudentid().getCode())) {
                // 卡号
                likeFields.put("studentByStudentid.code", workattenceEntity.getStudentByStudentid().getCode());
            }

            if (workattenceEntity.getStudentByStudentid().getName() != null && !"".equals(workattenceEntity.getStudentByStudentid().getName())) {
                // 用户名称
                likeFields.put("studentByStudentid.name", workattenceEntity.getStudentByStudentid().getName());
            }

            if (workattenceEntity.getState() != null && !"".equals(workattenceEntity.getState())) {
//                //是否可用
//                likeFields.put("state", workattenceEntity.getState());

                //是否可用
                likeFields.put("studentByStudentid.state", workattenceEntity.getState());
            }

        }


        String whereHql=null;

        if((dateBegin!=null && !"".equals(dateBegin)) && (dateEnd!=null && !"".equals(dateEnd)))
        {
            whereHql="where cast(o.comedate as date) >= cast('"+dateBegin+"' as date) and  cast(o.comedate as date) <= cast('"+dateEnd+"' as date)";
        }

        LinkedHashMap<String, String> orderByFields =new  LinkedHashMap<String, String>();
        orderByFields.put("comedate", "DESC");
        return workattenceDao.queryPageModel(null,null,likeFields,null,orderByFields,whereHql,pageModel);
    }



    @Override
    public PageModel<WorkattenceEntity> queryTeacherWorkattences(WorkattenceEntity workattenceEntity,PageModel<WorkattenceEntity> pageModel,String dateBegin,String dateEnd) {
        // 初始化Like查询条件承载体
        LinkedHashMap<String, String> likeFields = new LinkedHashMap<String, String>();
        //教师类别
        likeFields.put("type","T");

        // 初始化Like查询条件
        if (workattenceEntity != null) {

            if (workattenceEntity.getTeacherByTeacherid().getCode() != null && !"".equals(workattenceEntity.getTeacherByTeacherid().getCode())) {
                // 卡号
                likeFields.put("teacherByTeacherid.code", workattenceEntity.getTeacherByTeacherid().getCode());
            }

            if (workattenceEntity.getTeacherByTeacherid().getName() != null && !"".equals(workattenceEntity.getTeacherByTeacherid().getName())) {
                // 用户名称
                likeFields.put("teacherByTeacherid.name", workattenceEntity.getTeacherByTeacherid().getName());
            }

            if (workattenceEntity.getState() != null && !"".equals(workattenceEntity.getState())) {
//                //是否可用
//                likeFields.put("state", workattenceEntity.getState());

                //是否可用
                likeFields.put("teacherByTeacherid.state", workattenceEntity.getState());
            }

        }


        String whereHql=null;

        if((dateBegin!=null && !"".equals(dateBegin)) && (dateEnd!=null && !"".equals(dateEnd)))
        {
            whereHql="where cast(o.comedate as date) >= cast('"+dateBegin+"' as date) and  cast(o.comedate as date) <= cast('"+dateEnd+"' as date)";
        }

        LinkedHashMap<String, String> orderByFields =new  LinkedHashMap<String, String>();
        orderByFields.put("comedate", "DESC");
        return workattenceDao.queryPageModel(null,null,likeFields,null,orderByFields,whereHql,pageModel);
    }


    @Override
    public List<WorkattenceEntity> findTeacherWorkattences(WorkattenceEntity workattenceEntity, String dateBegin, String dateEnd) {
        // 初始化Like查询条件承载体
        LinkedHashMap<String, String> likeFields = new LinkedHashMap<String, String>();
        //教师类别
        likeFields.put("type","T");

        // 初始化Like查询条件
        if (workattenceEntity != null) {

            if (workattenceEntity.getTeacherByTeacherid().getCode() != null && !"".equals(workattenceEntity.getTeacherByTeacherid().getCode())) {
                // 卡号
                likeFields.put("teacherByTeacherid.code", workattenceEntity.getTeacherByTeacherid().getCode());
            }

            if (workattenceEntity.getTeacherByTeacherid().getName() != null && !"".equals(workattenceEntity.getTeacherByTeacherid().getName())) {
                // 用户名称
                likeFields.put("teacherByTeacherid.name", workattenceEntity.getTeacherByTeacherid().getName());
            }

            if (workattenceEntity.getState() != null && !"".equals(workattenceEntity.getState())) {
//                //是否可用
//                likeFields.put("state", workattenceEntity.getState());

                //是否可用
                likeFields.put("teacherByTeacherid.state", workattenceEntity.getState());
            }

        }


        String whereHql=null;

        if((dateBegin!=null && !"".equals(dateBegin)) && (dateEnd!=null && !"".equals(dateEnd)))
        {
            whereHql="where cast(o.comedate as date) >= cast('"+dateBegin+"' as date) and  cast(o.comedate as date) <= cast('"+dateEnd+"' as date)";
        }

        LinkedHashMap<String, String> orderByFields =new  LinkedHashMap<String, String>();
        orderByFields.put("comedate", "DESC");
        return workattenceDao.findResultList(null,null,likeFields,null,orderByFields,whereHql);
    }

    @Override
    public List<WorkattenceEntity> findStudentWorkattences(WorkattenceEntity workattenceEntity, String dateBegin, String dateEnd) {
        // 初始化Like查询条件承载体
        LinkedHashMap<String, String> likeFields = new LinkedHashMap<String, String>();
        //教师类别
        likeFields.put("type","S");

        // 初始化Like查询条件
        if (workattenceEntity != null) {

            if (workattenceEntity.getStudentByStudentid().getCode() != null && !"".equals(workattenceEntity.getStudentByStudentid().getCode())) {
                // 卡号
                likeFields.put("studentByStudentid.code", workattenceEntity.getStudentByStudentid().getCode());
            }

            if (workattenceEntity.getStudentByStudentid().getName() != null && !"".equals(workattenceEntity.getStudentByStudentid().getName())) {
                // 用户名称
                likeFields.put("studentByStudentid.name", workattenceEntity.getStudentByStudentid().getName());
            }

            if (workattenceEntity.getState() != null && !"".equals(workattenceEntity.getState())) {
//                //是否可用
//                likeFields.put("state", workattenceEntity.getState());

                //是否可用
                likeFields.put("studentByStudentid.state", workattenceEntity.getState());
            }

        }


        String whereHql=null;

        if((dateBegin!=null && !"".equals(dateBegin)) && (dateEnd!=null && !"".equals(dateEnd)))
        {
            whereHql="where cast(o.comedate as date) >= cast('"+dateBegin+"' as date) and  cast(o.comedate as date) <= cast('"+dateEnd+"' as date)";
        }

        LinkedHashMap<String, String> orderByFields =new  LinkedHashMap<String, String>();
        orderByFields.put("comedate", "DESC");
        return workattenceDao.findResultList(null,null,likeFields,null,orderByFields,whereHql);
    }
}
