package com.utm.basic.dao;

import org.hibernate.Query;

import java.util.LinkedHashMap;


public class HibernateDaoUtil {

    /**
     * @MethodName	: SetQueryParameter
     * @Description	: 给query中查询参数赋值
     * @param query  EntityManager创建出来的query对象
     * @param equalFields  等于条件
     * @param notEqualFields  不等条件
     * @param LikeFields  Like条件
     * @return  Query 设置好参数的Query对象
     */
    public static Query SetQueryParameter(Query query,LinkedHashMap<Object, Object> equalFields,
                                      LinkedHashMap<Object, Object> notEqualFields,
                                      LinkedHashMap<String, String> LikeFields)
    {
        //单纯的Hibernate 是从0开始，jpa的hibernate实现是从1开始
        int i=0;
        if(equalFields!=null && equalFields.size() >0)
        {

            for (Object key : equalFields.keySet()) {
                query.setParameter(i , equalFields.get(key));
                i++;
            }

        }

        if(notEqualFields!=null && notEqualFields.size() >0)
        {
            for (Object key : notEqualFields.keySet()) {
                query.setParameter(i , notEqualFields.get(key));
                i++;
            }

        }

        if(LikeFields!=null && LikeFields.size() >0)
        {

            for (String key : LikeFields.keySet()) {
                query.setParameter(i , LikeFields.get(key)+"%");
                i++;
            }

        }


        return query;

    }

    /**
     * @MethodName	: buildWhereJpql
     * @Description	: 拼接where查询字符串
     * @param equalFields 等于条件
     * @param notEqualFields  不等条件
     * @param LikeFields   Like条件
     * @param nullFields   null条件
     * @param orderByFields   排序条件
     * @param whereHql   自定义jpql
     * @return  条件字符串
     */

    //单纯的hibernate 参数应该是  name=？ and age=?
    //在hibernate实现的jpa中，参数为name=？1 and age=?2
    public static String buildWhereJpql(	LinkedHashMap<Object, Object> equalFields,
                                        LinkedHashMap<Object, Object> notEqualFields,
                                        LinkedHashMap<String, String> LikeFields,
                                        LinkedHashMap<String, String> nullFields,
                                        LinkedHashMap<String, String> orderByFields,
                                        String whereHql)
    {

        StringBuffer out = new StringBuffer("");

        boolean flag=false;
        int i=1;

        if(equalFields!=null && equalFields.size() >0)
        {
            out.append(" where ");
            flag=true;
            for (Object key : equalFields.keySet()) {
                out.append(" o." + key + "=?"+" ");
                out.append("and");
                i++;
            }
            out.delete(out.length() - 3, out.length());

        }

        if(notEqualFields!=null && notEqualFields.size() >0)
        {
            if(flag==false)
            {
                out.append(" where ");
                flag=true;
            }else {
                out.append("and");
            }



            for (Object key : notEqualFields.keySet()) {
                out.append(" o." + key + "<>?"+" ");
                out.append("and");
                i++;
            }
            out.delete(out.length() - 3, out.length());

        }

        if(LikeFields!=null && LikeFields.size() >0)
        {
            if(flag==false)
            {
                out.append(" where ");
                flag=true;
            }else {
                out.append("and");
            }



            for (String key : LikeFields.keySet()) {
                out.append(" o." + key + " like ?"+" ");
                out.append("and");
                i++;
            }
            out.delete(out.length() - 3, out.length());

        }

        if(nullFields!=null && nullFields.size() >0)
        {
            if(flag==false)
            {
                out.append(" where ");
                flag=true;
            }else {
                out.append("and");
            }



            for (String key : nullFields.keySet()) {
                out.append(" o." + key + " is null ");
                out.append("and");

                i++;
            }
            out.delete(out.length() - 3, out.length());

        }

        if(whereHql!=null && !"".equals(whereHql))
        {
            if(flag==false)
            {
                out.append(" "+whereHql);
                flag=true;
            }else {
                out.append("and");
                out.append(" "+whereHql.replace("where", ""));
            }
        }

        if (orderByFields != null && orderByFields.size() > 0) {
            out.append(" order by ");
            for (String key : orderByFields.keySet()) {
                out.append("o." + key + " " + orderByFields.get(key));
                out.append(",");
            }
            out.deleteCharAt(out.length() - 1);
        }

        return out.toString();

    }

    /**
     * @MethodName	: buildsetWhereJpql
     * @Description	: 拼接set字段和where字段的hql语句
     * @param setFields  set字段
     * @param whereFields where字段
     * @return
     */
    //单纯的hibernate 参数应该是  name=？ and age=?
    //在hibernate实现的jpa中，参数为name=？1 and age=?2
    public static String buildsetWhereJpql(Object[] setFields, Object[] whereFields) {
        StringBuffer out = new StringBuffer("");
        int index=0;
        if (setFields != null && setFields.length > 0) {
            out.append(" set ");
            for (int i = 0; i < setFields.length; i++,index++) {
                out.append(" o." + setFields[i] + "=?" + " ");
                out.append(",");
            }
            out.delete(out.length() - 1, out.length());
        }
        if (whereFields != null && whereFields.length > 0) {
            out.append(" where ");
            for (int i = 0; i< (whereFields.length);i++,index++) {
                out.append(" o." + whereFields[i] + "=?"
                     + " ");
                out.append("and");
            }
            out.delete(out.length() - 3, out.length());
        }
        return out.toString();
    }
}
