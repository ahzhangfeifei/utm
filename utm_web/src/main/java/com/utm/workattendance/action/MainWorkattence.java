package com.utm.workattendance.action;

import com.utm.basic.action.BasicAction;
import com.utm.basic.entity.PageModel;
import com.utm.workattendance.entity.StudentEntity;
import com.utm.workattendance.entity.TeacherEntity;
import com.utm.workattendance.entity.WorkattenceEntity;
import com.utm.workattendance.service.IMainWorkattenceService;
import com.utm.workattendance.service.IStudentService;
import com.utm.workattendance.service.ITeacherService;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainWorkattence extends BasicAction {

    private WorkattenceEntity workattence;

    @Resource
    private IMainWorkattenceService mainWorkattenceService;

    @Resource
    private ITeacherService teacherService;

    @Resource
    private IStudentService studentService;

    private String type;

    private String flag;

    private String cardCode;

    private String lastCardCode;

    private String lastWorkTime;

    public IMainWorkattenceService getMainWorkattenceService() {
        return mainWorkattenceService;
    }

    public String getLastCardCode() {
        return lastCardCode;
    }

    public void setLastCardCode(String lastCardCode) {
        this.lastCardCode = lastCardCode;
    }

    public String getLastWorkTime() {
        return lastWorkTime;
    }

    public void setLastWorkTime(String lastWorkTime) {
        this.lastWorkTime = lastWorkTime;
    }

    public String work() throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");//设置时间格式
        String nowDate=df.format(new Date());
        String nowTime=tf.format(new Date());

        if(!"1".equals(flag))
        {
            if(workattence.getType()!=null && !"".equals(workattence.getType()))
            {

                PageModel<WorkattenceEntity> pageModelResult = mainWorkattenceService.queryWorkattencesPage(cardCode,pageModel
                ,nowDate,workattence.getType());
                request.setAttribute("pageModel", pageModelResult);
                return "main_workattence";
            }

        }

        if(cardCode==null || "".equals(cardCode.trim()))
        {
            request.setAttribute("result","失败！请输入卡号。");
            return "main_workattence";
        }

        //重复刷卡必须间隔30秒以上
        if(cardCode.equals(lastCardCode)){
            Date begin=tf.parse(lastWorkTime);
            Date end=tf.parse(nowTime);
            long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
            if(between<30){
                request.setAttribute("result","失败！重复刷卡必须间隔30秒以上。");
                return "main_workattence";
            }
        }

        request.setAttribute("lastCardCode",cardCode);
        request.setAttribute("lastWorkTime",nowTime);


        type=null;
        if(cardCode!=null && !"".equals(cardCode.trim())) {
            TeacherEntity teacherResult=null;
            TeacherEntity teacher = new TeacherEntity();
            teacher.setCode(cardCode.trim());
            teacherResult = teacherService.getTeacher(teacher);

            if(teacherResult!=null) {
                type="T";
                workattence.setTeacherByTeacherid(teacherResult);
                workattence.setType("T");
                request.setAttribute("result","成功! 教师："+teacherResult.getName()+" 日期："+nowDate+" "+nowTime);

            }
        }

        if(cardCode!=null && !"".equals(cardCode.trim()))
        {
            StudentEntity studentResult=null;
            StudentEntity student=new StudentEntity();
            student.setCode(cardCode.trim());
            studentResult=studentService.getStudent(student);

            if(studentResult!=null) {
                type="S";
                workattence.setStudentByStudentid(studentResult);
                workattence.setType("S");
                request.setAttribute("result","成功! 学生："+studentResult.getName()+" 日期："+nowDate+" "+nowTime);
            }
        }

        if(type!="T" && type!="S")
        {
            request.setAttribute("result","失败！该卡未注册或已停用!（请联系管理员）。");
            return "main_workattence";
        }



        request.setAttribute("type",type);

        workattence.setState("是");
        workattence.setComedate(nowDate);
        workattence.setCometime(nowTime);

        //判断是登入还是登出
        WorkattenceEntity workattenceEntity=mainWorkattenceService.queryWorkattencesList(workattence,nowDate,type);

        if(workattenceEntity!=null)
        {
            workattenceEntity.setMore(workattenceEntity.getMore()+"登出");
            workattenceEntity.setLeavetime(nowTime);
            workattenceEntity.setLeavedate(nowDate);
            mainWorkattenceService.saveOrUpdate(workattenceEntity);
            request.setAttribute("logintype","登出-");

        }else{
            workattence.setMore("电脑端：登入-");
            mainWorkattenceService.saveOrUpdate(workattence);
            request.setAttribute("logintype","登入-");
        }


        request.setAttribute("workattence",workattence);

        PageModel<WorkattenceEntity> pageModelResult = mainWorkattenceService.queryWorkattences(workattence,pageModel,nowDate,nowDate,type);

        request.setAttribute("pageModel", pageModelResult);

        return "main_workattence";

    }

    /**
     * @MethodName : queryWorkattence
     * @Description : 查询考勤记录
     * @return
     */
    public String showMainWorkattence() {

        return "main_workattence";
    }

    public WorkattenceEntity getWorkattence() {
        return workattence;
    }
    public void setWorkattence(WorkattenceEntity workattence) {
        this.workattence = workattence;
    }

    public void setMainWorkattenceService(IMainWorkattenceService mainWorkattenceService) {
        this.mainWorkattenceService = mainWorkattenceService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public void setStudentService(IStudentService studentManage) {
        this.studentService = studentManage;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }


}
