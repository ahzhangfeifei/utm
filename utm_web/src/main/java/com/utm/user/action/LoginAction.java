package com.utm.user.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.utm.basic.tool.PathUtil;
import com.utm.user.entity.UserEntity;
import com.utm.user.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class LoginAction extends ActionSupport {

    private final Log logger = LogFactory.getLog(getClass());




    private UserEntity userEntity;

    @Resource
    private IUserService userService;

    @Override
    public String execute() throws Exception {

        logger.info("项目的根路径："+PathUtil.root());
        //logger.error("这是错误信息："+PathUtil.root());
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        System.out.println(request.getCharacterEncoding());

        if("".equals(userEntity.getName()) || "".equals(userEntity.getPassword()))
        {
            request.setAttribute("loginResult","请输入用户名和密码！");
            return "fail";
        }

        if ("root".equals(userEntity.getName()) && "root123".equals(userEntity.getPassword()))
        {
            session.put("userEntity.name", userEntity.getName());
            System.out.println("登陆成功,用户名为=" + userEntity.getName());
            logger.info("登陆成功！！！");
            return "success";
        }


        if(userService.getUser(userEntity)!=null)
        {
            session.put("userEntity.name", userEntity.getName());
            System.out.println("登陆成功,用户名为=" + userEntity.getName());
            logger.info("登陆成功！！！");
            return "success";
        }

        logger.info("登陆失败！！！");
        request.setAttribute("loginResult","用户名或密码错误！");
        return "fail";
    }

    public String loginOut()
    {
        Map session = ActionContext.getContext().getSession();
        session.clear();
        return "laginOut";
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setUserService(IUserService userManage) {
        this.userService = userManage;
    }

    public IUserService getUserService() {
        return userService;
    }

}
