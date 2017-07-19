package com.utm.workattendance.action;

import com.utm.basic.action.BasicAction;
import com.utm.basic.tool.JsonUtil;
import com.utm.workattendance.entity.StudentEntity;
import com.utm.workattendance.entity.TeacherEntity;
import com.utm.workattendance.entity.WorkattenceEntity;
import com.utm.workattendance.service.IMainWorkattenceService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AndroidMainWorkattence extends BasicAction {

    private TeacherEntity teacherEntity;
    private StudentEntity studentEntity;

    @Resource
    private IMainWorkattenceService mainWorkattenceService;

    private String type;

    private String loginType;


    public void work(){
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("UTF-8");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");//设置时间格式
        String nowDate=df.format(new Date());
        String nowTime=tf.format(new Date());

        WorkattenceEntity workattence=new WorkattenceEntity();
        try {
            System.out.println("打卡来自手机！");
            if("T".equals(type) && teacherEntity!=null){
                workattence.setTeacherByTeacherid(teacherEntity);
                workattence.setType("T");
            }

            if("S".equals(type) && studentEntity!=null)
            {
                workattence.setStudentByStudentid(studentEntity);
                workattence.setType("S");
            }
            workattence.setState("是");
            workattence.setComedate(nowDate);
            workattence.setCometime(nowTime);

            //如果是打卡登入，随时可以。
            //如果是打卡登出，当天必须有未结束的登入，才能够登出。
            if("come".equals(loginType))
            {
                workattence.setMore("安卓端：登入-");
                mainWorkattenceService.saveOrUpdate(workattence);
                workattence.setTeacherByTeacherid(null);
                workattence.setStudentByStudentid(null);
                response.getWriter().write(JsonUtil.toJson(workattence));
            }else if("leave".equals(loginType)){
                WorkattenceEntity workattenceEntity=mainWorkattenceService.queryWorkattencesList(workattence,nowDate,type);
                if(workattenceEntity!=null)
                {
                    workattenceEntity.setLeavetime(nowTime);
                    workattenceEntity.setLeavedate(nowDate);
                    workattenceEntity.setMore(workattenceEntity.getMore()+"登出");
                    mainWorkattenceService.saveOrUpdate(workattenceEntity);
                    workattenceEntity.setTeacherByTeacherid(null);//TODO：防止tojson时返回null；
                    workattenceEntity.setStudentByStudentid(null);
                    response.getWriter().write(JsonUtil.toJson(workattenceEntity));
                }else {
                    response.getWriter().write("");
                }

            }else{

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setMainWorkattenceService(IMainWorkattenceService mainWorkattenceService) {
        this.mainWorkattenceService = mainWorkattenceService;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

}
