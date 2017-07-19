package com.utm.basic.tool;

import com.utm.workattendance.entity.TeacherEntity;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 各种格式的编码加码工具类. 集成Commons-Codec,Commons-Lang及JDK提供的编解码方法.
 * @author zhangfeifei
 */
public class EncodeUtil {

    private static final String DEFAULT_URL_ENCODING = "UTF-8";


    public static void main(String[] args) {
        TeacherEntity teacherEntity=new TeacherEntity();
        teacherEntity.setName("王老师");
        teacherEntity.setState("是");
        teacherEntity.setSchool("一中");

        EncodeUtil.entityDecode(teacherEntity);
    }

    /**将实体各字段的值解码
     *
     * @param obj 实体对象
     * @return
     */
    public static Object entityDecode(Object obj){

        if(obj==null)
        {
            return obj;
        }

        Field[] fields=ReflectionUtil.getFields(obj);

        for(int i=0;i<fields.length;i++)
        {
            Object objFieldValue=ReflectionUtil.getFieldValue(obj,fields[i].getName());
            if(objFieldValue!=null && !"".equals(objFieldValue))
            {
                ReflectionUtil.setFieldValue(obj,fields[i].getName(),EncodeUtil.urlDecode(objFieldValue.toString()));
            }

        }

        return obj;
    }

    /**将复合实体各字段的值解码
     *
     * @param obj 复合实体对象（对象套对象的情况）
     * @return
     */
    public static Object compositeEntityDecode(Object obj){

        if(obj==null)
        {
            return obj;
        }

        Field[] fields=ReflectionUtil.getFields(obj);

        for(int i=0;i<fields.length;i++)
        {
            Object objFieldValue=ReflectionUtil.getProperty(obj,fields[i].getName());

            if(objFieldValue!=null && !"".equals(objFieldValue))
            {

                if(objFieldValue instanceof String)
                {
                    ReflectionUtil.setFieldValue(obj,fields[i].getName(),EncodeUtil.urlDecode(objFieldValue.toString()));

                }else{
                    //递归
                    compositeEntityDecode(objFieldValue);
                }

            }

        }

        return obj;
    }



    /**
     * Hex编码.
     */
    public static String hexEncode(byte[] input) {
        return Hex.encodeHexString(input);
    }

    /**
     * Hex解码.
     */
    public static byte[] hexDecode(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new IllegalStateException("Hex Decoder exception", e);
        }
    }

    /**
     * Base64编码.
     */
    public static String base64Encode(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }

    /**
     * Base64编码, URL安全(将Base64中的URL非法字符如+,/=转为其他字符, 见RFC3548).
     */
    public static String base64UrlSafeEncode(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    /**
     * Base64解码.
     */
    public static byte[] base64Decode(String input) {
        return Base64.decodeBase64(input);
    }

    /**
     * URL 编码, Encode默认为UTF-8.
     */
    public static String urlEncode(String input) {
        try {
            return URLEncoder.encode(input, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * URL 解码, Encode默认为UTF-8.
     */
    public static String urlDecode(String input) {
        try {
            return URLDecoder.decode(input, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * Html 转码.
     */
    public static String htmlEscape(String html) {
        return StringEscapeUtils.escapeHtml(html);
    }

    /**
     * Html 解码.
     */
    public static String htmlUnescape(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml(htmlEscaped);
    }

    /**
     * Xml 转码.
     */
    public static String xmlEscape(String xml) {
        return StringEscapeUtils.escapeXml(xml);
    }

    /**
     * Xml 解码.
     */
    public static String xmlUnescape(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }
}
