package com.utm.workattendance.service;

import com.utm.basic.entity.PageModel;
import com.utm.basic.service.IBasicService;
import com.utm.workattendance.entity.WorkattenceEntity;

/**
 * @Description :考勤记录管理
 */
public interface IMainWorkattenceService extends IBasicService<WorkattenceEntity,String>{

    public WorkattenceEntity getWorkattence(WorkattenceEntity workattenceEntity);

    public PageModel<WorkattenceEntity> queryWorkattences(WorkattenceEntity workattenceEntity, PageModel<WorkattenceEntity> pageModel, String dateBegin, String dateEnd,String type);

    public PageModel<WorkattenceEntity> queryWorkattencesPage(String code,PageModel<WorkattenceEntity> pageModel,String dateNow,String type) ;

    public WorkattenceEntity queryWorkattencesList(WorkattenceEntity workattenceEntity, String dateNow, String type) ;

}
