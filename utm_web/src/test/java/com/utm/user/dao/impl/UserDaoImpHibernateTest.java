package com.utm.user.dao.impl;

import com.utm.basic.entity.PageModel;
import com.utm.test.basic.AbstractTestCase;
import com.utm.user.dao.IUserDao;
import com.utm.user.entity.UserEntity;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImpHibernateTest extends AbstractTestCase{

    //@Resource(name="userDao")代表根据名称来查找配置文件，userDao即为配置文件中的名称
    @Resource(name="userDao")
    private IUserDao userDao;

    @Test
    public void testSave() throws Exception {

        UserEntity userEntity=new UserEntity("张三","zhangsan",10);
        userDao.save(userEntity);
        UserEntity userEntity1=userDao.get(userEntity.getId());

        assertEquals("张三",userEntity1.getName());
        assertEquals("zhangsan",userEntity1.getPassword());
        assertEquals(10,userEntity1.getType());
        assertNotNull(userEntity1.getId());
    }

    @Test
    public void testUpdate() throws Exception {
        UserEntity userEntity=new UserEntity("张三","zhangsan",10);
        userDao.save(userEntity);
        UserEntity userEntity1=userDao.get(userEntity.getId());

        userEntity1.setName("李四");
        userEntity1.setPassword("lisi");
        userEntity1.setType(5);

        userDao.update(userEntity1);

        UserEntity userEntity2=userDao.get(userEntity.getId());

        assertEquals("李四", userEntity2.getName());
        assertEquals("lisi", userEntity2.getPassword());
        assertEquals(5, userEntity2.getType());
    }

    @Test
    public void testDelete() throws Exception {
        UserEntity userEntity=new UserEntity("张三","zhangsan",10);
        userDao.save(userEntity);

        userDao.delete(userEntity.getId());

        UserEntity userEntity1=userDao.get(userEntity.getId());
        assertNull(userEntity1);


    }


    @Test
    public void testDeleteArray() throws Exception {
        UserEntity userEntity1=new UserEntity("aa","bb",1);
        UserEntity userEntity2=new UserEntity("dd","ee",2);
        UserEntity userEntity3=new UserEntity("ff","gg",3);
        userDao.save(userEntity1);
        userDao.save(userEntity2);
        userDao.save(userEntity3);



        String[] ids=new String[]{userEntity1.getId(),userEntity2.getId(),userEntity3.getId()};

        userDao.deleteArray(ids);

//        assertNull(userDao.get(ids[0]));
//        assertNull(userDao.get(ids[1]));
//        assertNull(userDao.get(ids[2]));

    }

    @Test
    public void testGet() throws Exception {
        UserEntity userEntity=new UserEntity("张三","zhangsan",10);
        userDao.save(userEntity);
        UserEntity userEntity1=userDao.get(userEntity.getId());

        assertEquals("张三",userEntity1.getName());
        assertEquals("zhangsan",userEntity1.getPassword());
        assertEquals(10,userEntity1.getType());
        assertNotNull(userEntity1.getId());
    }

    @Test
    public void testGetUser() throws Exception {
        UserEntity userEntity=new UserEntity("张三","zhangsan",10);
        userDao.save(userEntity);

        LinkedHashMap<Object, Object> equalFields =new  LinkedHashMap<Object, Object>();
        equalFields.put("name", userEntity.getName());
        equalFields.put("password", userEntity.getPassword());

        UserEntity userEntity1=userDao.get(equalFields,null,null,null,null);

        assertEquals("张三",userEntity1.getName());
        assertEquals("zhangsan",userEntity1.getPassword());
        assertEquals(10,userEntity1.getType());
        assertNotNull(userEntity1.getId());
    }

    @Test
    public void testGetTotalCount() throws Exception {
        initSomeUser();

        LinkedHashMap<Object, Object> equalFields =new  LinkedHashMap<Object, Object>();
        equalFields.put("type", 1);

        Long result=userDao.getTotalCount(equalFields,null,null,null,null);

        assertEquals(new Long(3),result);
    }

    @Test
    public void testFindResultList() throws Exception {
        initSomeUser();

        LinkedHashMap<Object, Object> equalFields =new  LinkedHashMap<Object, Object>();
        equalFields.put("type", 1);

        LinkedHashMap<String, String> likeFields =new  LinkedHashMap<String, String>();
        likeFields.put("name", "e");

        List<UserEntity> userEntityList =userDao.findResultList(equalFields,null,likeFields,null,null,null,0,20);

        assertEquals(3,userEntityList.size());

    }

    @Test
    public void testQueryPageModelByEqual() throws Exception {
        initSomeUser();

        LinkedHashMap<Object, Object> equalFields =new  LinkedHashMap<Object, Object>();
        equalFields.put("type", 1);

        PageModel<UserEntity> pageModel=initPageMode();

        LinkedHashMap<String,String> orderFields=new LinkedHashMap<String, String>();
        orderFields.put("name","desc");

        pageModel= userDao.queryPageModelByEqual(equalFields,pageModel,orderFields);

        assertEquals(2,pageModel.getList().size());
        assertEquals(3,(long)pageModel.getTotalRecords());
        assertEquals("name",pageModel.getOrderField());
        assertEquals("desc",pageModel.getOrderDirection());

    }

    @Test
    public void testQueryPageModelByLike() throws Exception {
        initSomeUser();
        LinkedHashMap<String, String> likeFields =new  LinkedHashMap<String, String>();
        likeFields.put("name", "e");
        LinkedHashMap<String,String> orderFields=new LinkedHashMap<String, String>();
        orderFields.put("name","desc");
        PageModel<UserEntity> pageModel=initPageMode();
        pageModel= userDao.queryPageModelByLike(likeFields,pageModel,orderFields);
        assertEquals(2,pageModel.getList().size());
        assertEquals(3,(long)pageModel.getTotalRecords());
        assertEquals("name",pageModel.getOrderField());
        assertEquals("desc",pageModel.getOrderDirection());

    }

    @Test
    public void testQueryPageModelByLikeAndEqual() throws Exception {
        initSomeUser();

        LinkedHashMap<Object, Object> equalFields =new  LinkedHashMap<Object, Object>();
        equalFields.put("type", 1);
        LinkedHashMap<String, String> likeFields =new  LinkedHashMap<String, String>();
        likeFields.put("name", "e");
        LinkedHashMap<String,String> orderFields=new LinkedHashMap<String, String>();
        orderFields.put("name","desc");

        PageModel<UserEntity> pageModel=initPageMode();

        pageModel= userDao.queryPageModelByLikeAndEqual(equalFields,likeFields,orderFields,pageModel
        );

        assertEquals(2,pageModel.getList().size());
        assertEquals(3,(long)pageModel.getTotalRecords());
        assertEquals("name",pageModel.getOrderField());
        assertEquals("desc",pageModel.getOrderDirection());
    }

    private void initSomeUser() {
        UserEntity userEntity1=new UserEntity("ea","bb",1);
        UserEntity userEntity2=new UserEntity("ed","ee",1);
        UserEntity userEntity3=new UserEntity("ef","gg",1);
        userDao.save(userEntity1);
        userDao.save(userEntity2);
        userDao.save(userEntity3);
    }

    private PageModel<UserEntity> initPageMode() {
        PageModel<UserEntity> pageModel=new PageModel<UserEntity>();
        pageModel.setNumPerPage(2);
        pageModel.setPageNum(1);

        return pageModel;
    }


}
