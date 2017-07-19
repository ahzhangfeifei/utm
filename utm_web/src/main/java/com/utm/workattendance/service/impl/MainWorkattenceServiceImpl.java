package com.utm.workattendance.service.impl;

import com.utm.basic.dao.IBasicDao;
import com.utm.basic.entity.PageModel;
import com.utm.basic.service.impl.BasicService;
import com.utm.workattendance.dao.IWorkattenceDao;
import com.utm.workattendance.entity.WorkattenceEntity;
import com.utm.workattendance.service.IMainWorkattenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description :考勤记录管理
 */
public class MainWorkattenceServiceImpl extends BasicService<WorkattenceEntity,String> implements IMainWorkattenceService {

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
    public PageModel<WorkattenceEntity> queryWorkattences(WorkattenceEntity workattenceEntity,PageModel<WorkattenceEntity> pageModel,String dateBegin,String dateEnd,String type) {
        // 初始化Like查询条件承载体
        LinkedHashMap<String, String> likeFields = new LinkedHashMap<String, String>();
        //类别
        likeFields.put("type",type);

        if("S".equals(type))
        {
            likeFields.put("studentByStudentid.code", workattenceEntity.getStudentByStudentid().getCode());
            likeFields.put("studentByStudentid.state","是");
        }else
        {
            likeFields.put("teacherByTeacherid.code", workattenceEntity.getTeacherByTeacherid().getCode());
            likeFields.put("teacherByTeacherid.state","是");
        }


        String whereHql=null;

        if((dateBegin!=null && !"".equals(dateBegin)) && (dateEnd!=null && !"".equals(dateEnd)))
        {
            whereHql="where cast(o.comedate as date) >= cast('"+dateBegin+"' as date) and  cast(o.comedate as date) <= cast('"+dateEnd+"' as date)";
        }

        LinkedHashMap<String, String> orderByFields =new  LinkedHashMap<String, String>();
        orderByFields.put("cometime", "DESC");
        return workattenceDao.queryPageModel(null,null,likeFields,null,orderByFields,whereHql,pageModel);
    }



    @Override
    public PageModel<WorkattenceEntity> queryWorkattencesPage(String code,PageModel<WorkattenceEntity> pageModel,String dateNow,String type) {
        // 初始化Like查询条件承载体
        LinkedHashMap<String, String> likeFields = new LinkedHashMap<String, String>();
        //类别
        likeFields.put("type",type);

        if("S".equals(type))
        {
            likeFields.put("studentByStudentid.code", code);
            likeFields.put("studentByStudentid.state","是");
        }else
        {
            likeFields.put("teacherByTeacherid.code", code);
            likeFields.put("teacherByTeacherid.state","是");
        }


        String whereHql=null;

        if(dateNow!=null && !"".equals(dateNow))
        {
            whereHql="where cast(o.comedate as date) >= cast('"+dateNow+"' as date) and  cast(o.comedate as date) <= cast('"+dateNow+"' as date)";
        }

        LinkedHashMap<String, String> orderByFields =new  LinkedHashMap<String, String>();
        orderByFields.put("cometime", "DESC");
        return workattenceDao.queryPageModel(null,null,likeFields,null,orderByFields,whereHql,pageModel);
    }

    @Override
    public WorkattenceEntity queryWorkattencesList(WorkattenceEntity workattenceEntity, String dateNow, String type) {
        // 初始化Like查询条件承载体
        LinkedHashMap<String, String> likeFields = new LinkedHashMap<String, String>();
        //类别
        likeFields.put("type",type);



        if("S".equals(type))
        {
            likeFields.put("studentByStudentid.code", workattenceEntity.getStudentByStudentid().getCode());
            likeFields.put("studentByStudentid.state","是");
        }else
        {
            likeFields.put("teacherByTeacherid.code", workattenceEntity.getTeacherByTeacherid().getCode());
            likeFields.put("teacherByTeacherid.state","是");
        }


        // 初始化Like查询条件承载体
        LinkedHashMap<String, String> nullFields = new LinkedHashMap<String, String>();
        nullFields.put("leavedate","leavedate");

        String whereHql=null;

        if(dateNow!=null && !"".equals(dateNow))
        {
            whereHql="where cast(o.comedate as date) >= cast('"+dateNow+"' as date) and  cast(o.comedate as date) <= cast('"+dateNow+"' as date)";
        }

        LinkedHashMap<String, String> orderByFields =new  LinkedHashMap<String, String>();
        orderByFields.put("cometime", "DESC");
        List<WorkattenceEntity> workattenceEntityList= workattenceDao.findResultList(null,null,likeFields,nullFields,orderByFields,whereHql);

        if(workattenceEntityList.size()>0)
        {
            return workattenceEntityList.get(0);
        }

        return null;

    }
}
