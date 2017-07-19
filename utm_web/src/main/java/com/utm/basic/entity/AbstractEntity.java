package com.utm.basic.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;


public abstract class AbstractEntity implements java.io.Serializable  {

    //private static final long serialVersionUID =1L;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    public void save() {
//        ICommonService commonService = SpringContextUtil.getBean("CommonService");
//        commonService.save(this);
    }

    public void delete() {
//        ICommonService commonService = SpringContextUtil.getBean("CommonService");
//        commonService.deleteObject(this);
    }

    public void update() {
//        ICommonService commonService = SpringContextUtil.getBean("CommonService");
//        commonService.update(this);
    }
}
