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
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description :教师管理
 */
public class TeacherAction extends BasicAction {

    private TeacherEntity teacher;

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
    private ITeacherService teacherService;

    @Resource
    private IStudentService studentService;

    public void setStudentService(IStudentService studentManage) {
        this.studentService = studentManage;
    }

    public void setTeacherService(ITeacherService teacherManage) {
        this.teacherService = teacherManage;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }


    /**
     * @MethodName : addTeacher
     * @Description : 添加教师
     */
    public void addTeacher() {

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setCode(teacher.getCode());

        StudentEntity studentEntityResult = studentService.getStudent(studentEntity);

        if (studentEntityResult != null) {
            outMsg(AjaxObject.newOk("该卡已经存在! 属于学生：" + studentEntityResult.getName()).setCallbackType("").setNavTabId("studentListview").toString());

        } else {
            TeacherEntity teacherEntity = teacherService.getTeacher(teacher);
            if (teacherEntity == null) {
                teacherService.save(teacher);
                outMsg(AjaxObject.newOk("操作成功!").setNavTabId("teacherListview").toString());
            } else {
                outMsg(AjaxObject.newOk("该卡已经存在! 属于教师：" + teacherEntity.getName()).setCallbackType("").setNavTabId("teacherListview").toString());
            }
        }

    }

    /**
     * @MethodName : deleteTeacherByArray
     * @Description : 根据教师id数组删除教师
     */
    public void deleteTeacherByArray() {
        teacherService.deletePersonAndWorkattenceByArray(super.getIds());
        outMsg(AjaxObject.newOk("操作成功!").setCallbackType("")
                .setNavTabId("teacherListview").toString());
    }

    /**
     * @return
     * @MethodName : showUpdateTeacher
     * @Description : 显示修改教师页面
     */
    public String showUpdateTeacher() {
        TeacherEntity oldTeacherEntity = teacherService.get(teacher.getId());
        request.setAttribute("teacher", oldTeacherEntity);
        return "teacher_update";
    }


    /**
     * @MethodName : deleteTeacherById
     * @Description : 删除教师
     */
    public void deleteTeacherById() {
        teacherService.deletePersonAndWorkattenceById(teacher.getId());
        outMsg(AjaxObject.newOk("操作成功!").setCallbackType("")
                .setNavTabId("teacherListview").toString());
    }

    /**
     * @MethodName : updateTeacher
     * @Description : 修改教师
     */
    public void updateTeacher() {
        teacherService.update(teacher);
        outMsg(AjaxObject.newOk("操作成功!").setNavTabId("teacherListview").toString());
    }

    /**
     * @return
     * @MethodName : queryTeacher
     * @Description : 查询教师
     */
    public String queryTeacher() {
        PageModel<TeacherEntity> pageModelResult = teacherService.queryTeachers(teacher, pageModel, dateBegin, dateEnd);
        request.setAttribute("pageModel", pageModelResult);
        return "teacher_list";
    }


    /**
     * @return
     * @MethodName : queryTeacher
     * @Description : 导出教师
     */
    public void exportTeacher() throws UnsupportedEncodingException {
        //说明：http://liuhaili123.blog.163.com/blog/static/196562304201331121638237/
       /* String paramsTrans = null;
        try {
            paramsTrans = new String(teacher.getName().getBytes("ISO-8859-1"),"UTF-8");
            String params = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
            teacher.setName(params);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

       /* String params = java.net.URLDecoder.decode(teacher.getName(), "UTF-8");
        teacher.setName(params);*/

        //因为是get提交，这里要转码
        teacher = (TeacherEntity) EncodeUtil.entityDecode(teacher);

        List<TeacherEntity> teacherEntityList = teacherService.findTeacherList(teacher, dateBegin, dateEnd);


        // 设置类的属性和导出后Excel列名的对应关系
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
        fieldMap.put("code", "卡号");
        fieldMap.put("password", "密码");
        fieldMap.put("name", "姓名");
        fieldMap.put("sex", "性别");
        fieldMap.put("age", "年龄");
        fieldMap.put("school", "学校");
        fieldMap.put("address", "地址");
        fieldMap.put("phone", "电话");
        fieldMap.put("registtime", "登记时间");
        fieldMap.put("state", "状态");
        fieldMap.put("more", "备注");
        fieldMap.put("id", "唯一标识");

        try {
            // 导出Excel
            ExcelUtil.listToExcel(teacherEntityList, fieldMap, "教师信息", 100, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
