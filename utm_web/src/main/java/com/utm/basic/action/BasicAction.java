package com.utm.basic.action;

import com.opensymphony.xwork2.ActionSupport;
import com.utm.basic.annotation.Logger;
import com.utm.basic.entity.PageModel;
import org.apache.commons.logging.Log;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BasicAction extends ActionSupport {
    @Logger
    protected  Log logger;// = LogFactory.getLog(getClass());

    protected Integer pageNum = 1; // 当前是第几页

    protected Integer numPerPage = 20; // 每页显示多少条

    protected String orderField; // 排序字段

    protected String orderDirection; // 排序方向

    protected PageModel pageModel = new PageModel();

    protected HttpServletRequest request = ServletActionContext.getRequest();

    protected HttpServletResponse response = ServletActionContext.getResponse();

    private String[] ids; // id数组


    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    // 将处理信息发送到客户端
    protected void outMsg(String msg) {

        response.setCharacterEncoding("utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(msg);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(Integer numPerPage) {
        this.numPerPage = numPerPage;
        pageModel.setNumPerPage(numPerPage);
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        pageModel.setPageNum(pageNum);
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
        pageModel.setOrderField(orderField);
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
        pageModel.setOrderDirection(orderDirection);
    }

}
