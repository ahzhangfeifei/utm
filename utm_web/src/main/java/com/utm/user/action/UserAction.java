package com.utm.user.action;

import com.utm.basic.action.AjaxObject;
import com.utm.basic.action.BasicAction;
import com.utm.basic.entity.PageModel;
import com.utm.user.entity.UserEntity;
import com.utm.user.service.IUserService;

import javax.annotation.Resource;

public class UserAction extends BasicAction {

    private UserEntity userEntity;

    @Resource
    private IUserService userService;

    public void setUserService(IUserService userManage) {
        this.userService = userManage;
    }

    //TODO:如果注释掉get方法，user的name属性就是空指针，但password不是空指针。
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    
    /**
     * @MethodName : addUser
     * @Description : 添加用户
     */
    public void addUser() {
        logger.debug("log添加用户");
        userService.save(userEntity);
        outMsg(AjaxObject.newOk("操作成功!").setNavTabId("userListview").toString());
    }

    /**
     * @MethodName : deleteUserByArray
     * @Description : 根据用户id数组删除用户
     */
    public void deleteUserByArray() {
        try {
            logger.debug("log删除用户组");
            userService.deleteArray(super.getIds());
            outMsg(AjaxObject.newOk("操作成功!").setCallbackType("")
                    .setNavTabId("userListview").toString());
        } catch (RuntimeException e) {
            outMsg(AjaxObject.newError(e.getMessage()).setCallbackType("")
                    .setNavTabId("userListview").toString());
        }

    }

    /**
     * @MethodName : showUpdateUser
     * @Description : 显示修改用户页面
     * @return
     */
    public String showUpdateUser() {
        logger.debug("log显示更改用户界面");
        UserEntity oldUserEntity = userService.get(userEntity.getId());
        request.setAttribute("userEntity", oldUserEntity);
        return "user_update";
    }



    /**
     * @MethodName : deleteUserById
     * @Description : 删除用户
     */
    public void deleteUserById() {
        try {
            logger.debug("log根据用户ID删除用户");
            userService.delete(userEntity.getId());
            outMsg(AjaxObject.newOk("操作成功!").setCallbackType("")
                    .setNavTabId("userListview").toString());
        } catch (RuntimeException e) {
            outMsg(AjaxObject.newError(e.getMessage()).setCallbackType("")
                    .setNavTabId("userListview").toString());
        }
    }

    /**
     * @MethodName : updateUser
     * @Description : 修改用户
     */
    public void updateUser() {
        logger.debug("log更改用户");
        userService.update(userEntity);
        outMsg(AjaxObject.newOk("操作成功!").setNavTabId("userListview").toString());
    }

    /**
     * @MethodName : queryUser
     * @Description : 查询用户
     * @return
     */
    public String queryUser() {
        logger.debug("log查询用户");
        PageModel<UserEntity> pageModelResult = userService.queryUsers(userEntity,pageModel);
        request.setAttribute("pageModel", pageModelResult);
        return "user_list";
    }

}
