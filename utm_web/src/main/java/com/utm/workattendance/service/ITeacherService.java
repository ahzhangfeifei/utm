package com.utm.workattendance.service;

import com.utm.basic.service.IBasicService;
import com.utm.basic.entity.PageModel;
import com.utm.workattendance.entity.TeacherEntity;

import java.util.List;


public interface ITeacherService extends IBasicService<TeacherEntity,String>{
    public TeacherEntity checkTeacher(TeacherEntity teacherEntity);
    public TeacherEntity getTeacher(TeacherEntity teacherEntity);
    public void deletePersonAndWorkattenceByArray(String[] ids);
    public void deletePersonAndWorkattenceById(String id);
    public PageModel<TeacherEntity> queryTeachers(TeacherEntity teacherEntity, PageModel<TeacherEntity> pageModel,String dateBegin,String dateEnd);
    public List<TeacherEntity> findTeacherList(TeacherEntity teacherEntity,String dateBegin,String dateEnd);
}
