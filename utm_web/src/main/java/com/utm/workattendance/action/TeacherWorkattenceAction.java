package com.utm.workattendance.action;

import javax.annotation.Resource;
import com.utm.basic.action.AjaxObject;
import com.utm.basic.action.BasicAction;
import com.utm.basic.entity.PageModel;
import com.utm.basic.tool.EncodeUtil;
import com.utm.basic.tool.ExcelUtil;
import com.utm.workattendance.entity.TeacherEntity;
import com.utm.workattendance.entity.WorkattenceEntity;
import com.utm.workattendance.service.ITeacherService;
import com.utm.workattendance.service.IWorkattenceService;

import java.util.LinkedHashMap;
import java.util.List;

/**

 * @Description :考勤记录管理
 */
public class TeacherWorkattenceAction extends BasicAction {

    private WorkattenceEntity workattence;

    @Resource
    private IWorkattenceService workattenceService;

    @Resource
    private ITeacherService teacherService;

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

    public void setTeacherService(ITeacherService teacherManage) {
        this.teacherService = teacherManage;
    }

    public void setWorkattenceService(IWorkattenceService workattenceManage) {
        this.workattenceService = workattenceManage;
    }

    public WorkattenceEntity getWorkattence() {
        return workattence;
    }

    public void setWorkattence(WorkattenceEntity workattence) {
        this.workattence = workattence;
    }

    
    /**
     * @MethodName : addWorkattence
     * @Description : 添加考勤记录
     */
    public void addWorkattence() {
        TeacherEntity teacher=new TeacherEntity();
        teacher.setCode(workattence.getTeacherByTeacherid().getCode());

        TeacherEntity teacherResult=teacherService.getTeacher(teacher);

        if(teacherResult==null)
        {
            outMsg(AjaxObject.newOk("该卡未注册或已停用!").setCallbackType("").setNavTabId("teacherWorkattenceListview").toString());

        }else{
            workattence.setTeacherByTeacherid(teacherResult);
            //教师类别
            workattence.setType("T");
            workattenceService.save(workattence);
            outMsg(AjaxObject.newOk("操作成功!").setNavTabId("teacherWorkattenceListview").toString());
        }


    }

    /**
     * @MethodName : deleteWorkattenceByArray
     * @Description : 根据考勤记录id数组删除考勤记录
     */
    public void deleteWorkattenceByArray() {
            workattenceService.deleteArray(super.getIds());
            outMsg(AjaxObject.newOk("操作成功!").setCallbackType("")
                    .setNavTabId("teacherWorkattenceListview").toString());
    }

    /**
     * @MethodName : showUpdateWorkattence
     * @Description : 显示修改考勤记录页面
     * @return
     */
    public String showUpdateWorkattence() {
        WorkattenceEntity oldWorkattenceEntity = workattenceService.get(workattence.getId());
        request.setAttribute("workattence", oldWorkattenceEntity);
        return "workattence_update";
    }



    /**
     * @MethodName : deleteWorkattenceById
     * @Description : 删除考勤记录
     */
    public void deleteWorkattenceById() {
            workattenceService.delete(workattence.getId());
            outMsg(AjaxObject.newOk("操作成功!").setCallbackType("")
                    .setNavTabId("teacherWorkattenceListview").toString());
    }

    /**
     * @MethodName : updateWorkattence
     * @Description : 修改考勤记录
     */
    public void updateWorkattence() {
        WorkattenceEntity oldWorkattence=workattenceService.get(workattence.getId());

        oldWorkattence.setComedate(workattence.getComedate());
        oldWorkattence.setCometime(workattence.getCometime());
        oldWorkattence.setLeavedate(workattence.getLeavedate());
        oldWorkattence.setLeavetime(workattence.getLeavetime());
        oldWorkattence.setState(workattence.getState());
        oldWorkattence.setMore(workattence.getMore());

        workattenceService.update(oldWorkattence);
        outMsg(AjaxObject.newOk("操作成功!").setNavTabId("teacherWorkattenceListview").toString());
    }

    /**
     * @MethodName : queryWorkattence
     * @Description : 查询考勤记录
     * @return
     */
    public String queryWorkattence() {
        PageModel<WorkattenceEntity> pageModelResult = workattenceService.queryTeacherWorkattences(workattence, pageModel, dateBegin, dateEnd);
        request.setAttribute("pageModel", pageModelResult);
        return "workattence_list";
    }

    public void exportTeacherWorkattence() {
        workattence=(WorkattenceEntity) EncodeUtil.compositeEntityDecode(workattence);

        List<WorkattenceEntity> workattenceEntityList = workattenceService.findTeacherWorkattences(workattence, dateBegin, dateEnd);

        // 设置类的属性和导出后Excel列名的对应关系
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
        fieldMap.put("teacherByTeacherid.code", "卡号");
        fieldMap.put("teacherByTeacherid.name", "姓名");
        fieldMap.put("comedate", "登入日期");
        fieldMap.put("cometime", "登入时间");
        fieldMap.put("leavedate", "登出日期");
        fieldMap.put("leavetime", "登出时间");
        fieldMap.put("state", "状态");
        fieldMap.put("more", "备注");
        fieldMap.put("id", "唯一标识");
        try {
            // 导出Excel
            ExcelUtil.listToExcel(workattenceEntityList, fieldMap, "教师考勤记录", 100, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
