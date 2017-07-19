package com.utm.workattendance.service;

import com.utm.basic.service.IBasicService;
import com.utm.basic.entity.PageModel;
import com.utm.workattendance.entity.WorkattenceEntity;

import java.util.List;


public interface IWorkattenceService extends IBasicService<WorkattenceEntity,String>{

    public WorkattenceEntity getWorkattence(WorkattenceEntity workattenceEntity);
    public PageModel<WorkattenceEntity> queryStudentWorkattences(WorkattenceEntity workattenceEntity,PageModel<WorkattenceEntity> pageModel,String dateBegin,String dateEnd);
    public List<WorkattenceEntity> findTeacherWorkattences(WorkattenceEntity workattenceEntity,String dateBegin,String dateEnd);
    public List<WorkattenceEntity> findStudentWorkattences(WorkattenceEntity workattenceEntity,String dateBegin,String dateEnd);
    public PageModel<WorkattenceEntity> queryTeacherWorkattences(WorkattenceEntity workattenceEntity, PageModel<WorkattenceEntity> pageModel,String dateBegin,String dateEnd);
}
