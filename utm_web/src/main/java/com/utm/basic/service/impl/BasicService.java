package com.utm.basic.service.impl;

import com.utm.basic.entity.PageModel;
import com.utm.basic.dao.IBasicDao;
import com.utm.basic.service.IBasicService;

import java.util.LinkedHashMap;
import java.util.List;


public abstract class BasicService<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBasicService<M, PK> {

   // @Resource
    protected IBasicDao<M, PK> baseDao;


    public abstract void setBaseDao(IBasicDao<M, PK> baseDao);

//    public void setBaseDao(IBasicDao<M, PK> baseDao) {
//        this.baseDao = baseDao;
//    }

    @Override
    public M save(M model) {
        baseDao.save(model);
        return model;
    }

    @Override
    public void saveOrUpdate(M model) {
        baseDao.saveOrUpdate(model);
    }

    @Override
    public void update(M model) {
        baseDao.update(model);
    }

    @Override
    public void merge(M model) {
        baseDao.merge(model);
    }

    @Override
    public void delete(PK id) {
        baseDao.delete(id);
    }

    @Override
    public void deleteObject(M model) {
        baseDao.deleteObject(model);
    }

    @Override
    public void deleteArray(PK[] id) {
        baseDao.deleteArray(id);
    }

    @Override
    public boolean exists(PK id) {
        return baseDao.exists(id);
    }

    @Override
    public M get(PK id) {
        return baseDao.get(id);
    }

    @Override
    public M get(LinkedHashMap<Object, Object> equalFields, LinkedHashMap<Object, Object> notEqualFields, LinkedHashMap<String, String> LikeFields, LinkedHashMap<String, String> nullFields, String whereJpql) {
        return baseDao.get(equalFields,notEqualFields,LikeFields,nullFields,whereJpql);
    }

    @Override
    public Long getTotalCount(LinkedHashMap<Object, Object> equalFields, LinkedHashMap<Object, Object> notEqualFields, LinkedHashMap<String, String> LikeFields, LinkedHashMap<String, String> nullFields, String whereJpql) {
        return baseDao.getTotalCount(equalFields, notEqualFields, LikeFields, nullFields, whereJpql);
    }

    @Override
    public List<M> findResultList(LinkedHashMap<Object, Object> equalFields, LinkedHashMap<Object, Object> notEqualFields, LinkedHashMap<String, String> LikeFields, LinkedHashMap<String, String> nullFields, LinkedHashMap<String, String> orderByFields, String whereJpql, int firstResult, int maxResult) {
        return findResultList(equalFields,notEqualFields,LikeFields,nullFields,orderByFields,whereJpql,firstResult,maxResult);
    }

    @Override
    public PageModel<M> queryPageModel(LinkedHashMap<Object, Object> equalFields, LinkedHashMap<Object, Object> notEqualFields, LinkedHashMap<String, String> LikeFields, LinkedHashMap<String, String> nullFields, LinkedHashMap<String, String> orderByFields, String whereJpql, PageModel<M> pageModel) {
        return baseDao.queryPageModel(equalFields,notEqualFields,LikeFields,nullFields,orderByFields,whereJpql,pageModel);
    }

    @Override
    public PageModel<M> queryPageModelByEqual(LinkedHashMap<Object, Object> equalFields, PageModel<M> pageModel, LinkedHashMap<String, String> orderByFields) {
        return baseDao.queryPageModel(equalFields, null, null, null,
                orderByFields, null, pageModel);
    }

    @Override
    public PageModel<M> queryPageModelByLike(LinkedHashMap<String, String> likeFields, PageModel<M> pageModel, LinkedHashMap<String, String> orderByFields) {
        return baseDao.queryPageModel(null, null, likeFields, null,
                orderByFields, null, pageModel);
    }

    @Override
    public PageModel<M> queryPageModelByLikeAndEqual(LinkedHashMap<Object, Object> equalFields, LinkedHashMap<String, String> likeFields, LinkedHashMap<String, String> orderByFields, PageModel<M> pageModel) {
        return baseDao.queryPageModel(equalFields, null, likeFields, null,
                orderByFields, null, pageModel);
    }
}
