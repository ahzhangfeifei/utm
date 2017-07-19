package com.utm.workattendance.action;

import com.utm.basic.action.AjaxObject;
import com.utm.basic.action.BasicAction;
import com.utm.basic.entity.PageModel;
import com.utm.basic.tool.EncodeUtil;
import com.utm.basic.tool.ExcelUtil;
import com.utm.workattendance.entity.StudentEntity;
import com.utm.workattendance.entity.TeacherEntity;
import com.utm.workattendance.service.IStudentService;
import com.utm.workattendance.service.ITeacherService;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;


public class StudentAction extends BasicAction {

    private StudentEntity student;

    private String dateBegin;
    private String dateEnd;

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    @Resource
    private IStudentService studentService;

    @Resource
    private ITeacherService teacherService;

    public void setTeacherService(ITeacherService teacherManage) {
        this.teacherService = teacherManage;
    }

    public void setStudentService(IStudentService studentManage) {
        this.studentService = studentManage;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    
    /**
     * @MethodName : addStudent
     * @Description : 添加学生
     */
    public void addStudent() {

        TeacherEntity teacherEntity=new TeacherEntity();
        teacherEntity.setCode(student.getCode());

        TeacherEntity teacherEntityResult=teacherService.getTeacher(teacherEntity);

        if(teacherEntityResult!=null) {
            outMsg(AjaxObject.newOk("该卡已经存在! 属于教师：" + teacherEntityResult.getName()).setCallbackType("").setNavTabId("studentListview").toString());

        }else {
            StudentEntity studentEntity=studentService.getStudent(student);
            if(studentEntity==null)
            {
                studentService.save(student);
                outMsg(AjaxObject.newOk("操作成功!").setNavTabId("studentListview").toString());
            }else
            {
                outMsg(AjaxObject.newOk("该卡已经存在! 属于学生："+studentEntity.getName()).setCallbackType("").setNavTabId("studentListview").toString());
            }
        }

    }

    /**
     * @MethodName : deleteStudentByArray
     * @Description : 根据学生id数组删除学生
     */
    public void deleteStudentByArray() {
        studentService.deletePersonAndWorkattenceByArray(super.getIds());
        outMsg(AjaxObject.newOk("操作成功!").setCallbackType("")
                .setNavTabId("studentListview").toString());

    }

    /**
     * @MethodName : showUpdateStudent
     * @Description : 显示修改学生页面
     * @return
     */
    public String showUpdateStudent() {
        StudentEntity oldStudentEntity = studentService.get(student.getId());
        request.setAttribute("student", oldStudentEntity);
        return "student_update";
    }


    /**
     * @MethodName : deleteStudentById
     * @Description : 删除学生
     */
    public void deleteStudentById() {
        studentService.deletePersonAndWorkattenceById(student.getId());
        outMsg(AjaxObject.newOk("操作成功!").setCallbackType("")
                    .setNavTabId("studentListview").toString());
    }

    /**
     * @MethodName : updateStudent
     * @Description : 修改学生
     */
    public void updateStudent() {
        studentService.update(student);
        outMsg(AjaxObject.newOk("操作成功!").setNavTabId("studentListview").toString());
    }

    /**
     * @MethodName : queryStudent
     * @Description : 查询学生
     * @return
     */
    public String queryStudent() {
        PageModel<StudentEntity> pageModelResult = studentService.queryStudents(student,pageModel,dateBegin,dateEnd);
        request.setAttribute("pageModel", pageModelResult);
        return "student_list";
    }

    /**
     * @MethodName : exportStudent
     * @Description : 导出教师
     * @return
     */
    public void exportStudent() {

        //因为是get提交，这里要转码
        student= (StudentEntity)EncodeUtil.entityDecode(student);

        List<StudentEntity> studentEntityList=studentService.findStudentList(student,dateBegin,dateEnd);

        // 设置类的属性和导出后Excel列名的对应关系
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();

        fieldMap.put("code", "卡号");
        fieldMap.put("password", "密码");
        fieldMap.put("name", "姓名");
        fieldMap.put("sex", "性别");
        fieldMap.put("age", "年龄");
        fieldMap.put("school", "学校");
        fieldMap.put("grade", "年级");
        fieldMap.put("clazz", "班级");
        fieldMap.put("address", "地址");
        fieldMap.put("familyphone", "家庭电话");
        fieldMap.put("phone", "电话");
        fieldMap.put("registtime", "注册时间");
        fieldMap.put("state", "状态");
        fieldMap.put("more", "备注");
        fieldMap.put("id", "唯一标识");


        try {
            // 导出Excel
            ExcelUtil.listToExcel(studentEntityList, fieldMap, "学生信息",100, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
