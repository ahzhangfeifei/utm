package com.utm.basic.dao.impl;

import com.utm.basic.dao.HibernateDaoUtil;
import com.utm.basic.entity.PageModel;
import com.utm.basic.dao.IBasicDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;


public class HibernateBasicDao<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBasicDao<M,PK> {

    @Resource
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //事务必须是开启的(Required)，否则获取不到
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

//    private final String HQL_LIST_ALL;
    private final Class<M> entityClass;
//    private String pkName = null;

    public HibernateBasicDao() {
        this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        Field[] fields = this.entityClass.getDeclaredFields();
//        for(Field f : fields) {
//            if(f.isAnnotationPresent(Id.class)) {
//                this.pkName = f.getName();
//            }
//        }

//        HQL_LIST_ALL = "from " + this.entityClass.getSimpleName() + " order by " + pkName + " desc";
    }

    //基本CRUD
    @Override
    public M save(M model) {
        return (M) getSession().save(model);
    }
    @Override
    public void saveOrUpdate(M model) {
        getSession().saveOrUpdate(model);
    }
    @Override
    public void update(M model) {
        getSession().update(model);
    }
    @Override
    public void merge(M model) {
        getSession().merge(model);
    }
    @Override
    public void delete(PK id) {
        getSession().delete(this.get(id));

    }

    @Override
    public void delete(Class<?> entityClass, LinkedHashMap<Object, Object> equalFields,
                       String whereJpql) {
        Query query = getSession().createQuery("delete from " + entityClass.getSimpleName()
                + " as o "
                + HibernateDaoUtil.buildWhereJpql(equalFields, null, null, null, null, whereJpql));
        query = HibernateDaoUtil.SetQueryParameter(query, equalFields, null, null);
        query.executeUpdate();
    }


    @Override
    public void deleteObject(M model) {
        getSession().delete(model);

    }

    @Override
    public void deleteObjectList(List<M> list) {
        //TODO:解决删除数组对象
//        final int size =list.size();
//        String[] ids=(String[])list.toArray(new String[size]);
//        PK[] pks=(PK[])ids;


//        String[] pks=new String[list.size()];
//        for(int i=0;i<list.size();i++)
//        {
//            pks[i]=((M)list.get(i)).
//        }



//         deleteArray(pks);

    }

    @Override
    public void deleteArray(PK[] id) {
//        for (int i = 0; i < id.length; i++) {
//            delete(id[i]);
//        }

        if (id != null && id.length >0) {
            StringBuffer sbDelete = new StringBuffer();
            sbDelete.append("delete from ").append(entityClass.getSimpleName())
                    .append(" as o where o.id in (");
            for (int i = 0; i < id.length; i++) {
                sbDelete.append("?" + ",");
            }
            sbDelete.delete(sbDelete.length() - 1, sbDelete.length());
            sbDelete.append(")");
            Query query = getSession().createQuery(sbDelete.toString());
            for (int i = 0; i < id.length; i++) {
                query.setParameter(i, id[i]);
            }
            query.executeUpdate();
        }


    }

    @Override
    public boolean exists(PK id) {
        return get(id) != null;
    }
    @Override
    public M get(PK id) {
        return (M) getSession().get(this.entityClass,id);
    }

    @Override
    public M get(LinkedHashMap<Object, Object> equalFields,
                 LinkedHashMap<Object, Object> notEqualFields,
                 LinkedHashMap<String, String> LikeFields,
                 LinkedHashMap<String, String> nullFields, String whereHql) {
        // 获取实体名
        String entityName = entityClass.getSimpleName();

        Query query = getSession().createQuery("select o from "
                + entityName
                + " as o "
                + HibernateDaoUtil.buildWhereJpql(equalFields, notEqualFields, LikeFields, nullFields,
                null, whereHql));

        // 给查询参数赋值
        query = HibernateDaoUtil.SetQueryParameter(query, equalFields, notEqualFields, LikeFields);

        //query.setHint("org.hibernate.cacheable", true);
        List list= query.list();
        if(list!=null && list.size()>0)
        {
            return (M) query.list().get(0);
        }else{
            return null;
        }

    }

    // =============获取List========================

    @Override
    public  List<M> findResultList(
            LinkedHashMap<Object, Object> equalFields,
            LinkedHashMap<Object, Object> notEqualFields,
            LinkedHashMap<String, String> LikeFields,
            LinkedHashMap<String, String> nullFields,
            LinkedHashMap<String, String> orderByFields, String whereHql){
      return this.findResultList(equalFields,notEqualFields,LikeFields,nullFields,orderByFields,whereHql,0,0);
    }

    @Override
    public Long getTotalCount(
                                  LinkedHashMap<Object, Object> equalFields,
                                  LinkedHashMap<Object, Object> notEqualFields,
                                  LinkedHashMap<String, String> LikeFields,
                                  LinkedHashMap<String, String> nullFields, String whereHql) {
        // 获取实体名
        String entityName = entityClass.getSimpleName();

        // 获得query，并构建查询条件，排序条件
        Query query = getSession().createQuery("select count(o) from "
                + entityName
                + " as o "
                + HibernateDaoUtil.buildWhereJpql(equalFields, notEqualFields, LikeFields, nullFields,
                null, whereHql));

        // 给查询参数赋值
        query = HibernateDaoUtil.SetQueryParameter(query, equalFields, notEqualFields, LikeFields);

        //query.setHint("org.hibernate.cacheable", true);

        // 查询，返回list
        return (Long) query.list().get(0);
    }
    @Override
    public  List<M> findResultList(
                                      LinkedHashMap<Object, Object> equalFields,
                                      LinkedHashMap<Object, Object> notEqualFields,
                                      LinkedHashMap<String, String> LikeFields,
                                      LinkedHashMap<String, String> nullFields,
                                      LinkedHashMap<String, String> orderByFields, String whereHql,
                                      int firstResult, int maxResult) {
        // 获取实体名
        String entityName = entityClass.getSimpleName();

        // 获得query，并构建查询条件，排序条件
        Query query = getSession().createQuery("select o from "
                + entityName
                + " as o "
                + HibernateDaoUtil.buildWhereJpql(equalFields, notEqualFields, LikeFields, nullFields,
                orderByFields, whereHql));

        // 给查询参数赋值
        query = HibernateDaoUtil.SetQueryParameter(query, equalFields, notEqualFields, LikeFields);

        //query.setHint("org.hibernate.cacheable", true);

        // 分页条件
        if (maxResult != 0) {
            query.setMaxResults(maxResult).setFirstResult(firstResult);
        }

        // 查询，返回list
        return (List<M>) query.list();
    }


    //获取pageModel
    @Override
    public PageModel<M> queryPageModel(
                                        LinkedHashMap<Object, Object> equalFields,
                                        LinkedHashMap<Object, Object> notEqualFields,
                                        LinkedHashMap<String, String> LikeFields,
                                        LinkedHashMap<String, String> nullFields,
                                        LinkedHashMap<String, String> orderByFields, String whereHql,
                                        PageModel<M> pageModel) {
        int pageNo = pageModel.getPageNum();
        int pageSize = pageModel.getNumPerPage();
        if(pageModel.getOrderField()!=null && !"".equals(pageModel.getOrderField()) && pageModel.getOrderDirection()!=null && !"".equals(pageModel.getOrderDirection())){
            orderByFields=new LinkedHashMap<String, String>();
            orderByFields.put(pageModel.getOrderField(), pageModel.getOrderDirection());
        }else {
            //第一次查询返回的结果是有顺序的，所以要保留这两个值，在jsp端处理时，能看出是用什么排序的。
            if(orderByFields!=null)
            {
                for (String key : orderByFields.keySet()) {
                    pageModel.setOrderField(key);
                    pageModel.setOrderDirection(orderByFields.get(key));
                }
            }

        }
        // 获取数据总数
        Long totalCount = getTotalCount( equalFields, notEqualFields,
                LikeFields, nullFields, whereHql);

        // 根据查询条件获取数据
        int firstResult = (pageNo - 1) * pageSize;
        if (firstResult < 0) {
            firstResult = 0;
        }
        List list = findResultList( equalFields, notEqualFields, LikeFields,
                nullFields, orderByFields, whereHql, firstResult, pageSize);
        // 设置pageModel
        pageModel.setList(list);
        pageModel.setTotalRecords(totalCount);
        pageModel.setNumPerPage(pageSize);
        pageModel.setPageNum(pageNo);

        return pageModel;
    }
    @Override
    public  PageModel<M> queryPageModelByEqual(
                                               LinkedHashMap<Object, Object> equalFields, PageModel<M> pageModel,
                                               LinkedHashMap<String, String> orderByFields) {

        return this.queryPageModel( equalFields, null, null, null,
                orderByFields, null, pageModel);
    }
    @Override
    public  PageModel<M> queryPageModelByLike(
                                              LinkedHashMap<String, String> likeFields, PageModel<M> pageModel,
                                              LinkedHashMap<String, String> orderByFields) {
        return this.queryPageModel( null, null, likeFields, null,
                orderByFields, null, pageModel);
    }
    @Override
    public  PageModel<M> queryPageModelByLikeAndEqual(
                                                      LinkedHashMap<Object, Object> equalFields,
                                                      LinkedHashMap<String, String> likeFields,
                                                      LinkedHashMap<String, String> orderByFields, PageModel<M> pageModel) {
        return this.queryPageModel(equalFields, null, likeFields, null,
                orderByFields, null, pageModel);
    }

}
