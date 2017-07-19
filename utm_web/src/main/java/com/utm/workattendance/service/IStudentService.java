package com.utm.workattendance.service;

import com.utm.basic.service.IBasicService;
import com.utm.basic.entity.PageModel;
import com.utm.workattendance.entity.StudentEntity;
import java.util.List;


public interface IStudentService extends IBasicService<StudentEntity,String>{

    StudentEntity checkStudent(StudentEntity studentEntity);
    StudentEntity getStudent(StudentEntity studentEntity);
    void deletePersonAndWorkattenceById(String id);
    void deletePersonAndWorkattenceByArray(String id[]);
    PageModel<StudentEntity> queryStudents(StudentEntity studentEntity, PageModel<StudentEntity> pageModel,String dateBegin,String dateEnd);
    List<StudentEntity> findStudentList(StudentEntity studentEntity,String dateBegin,String dateEnd);

}
