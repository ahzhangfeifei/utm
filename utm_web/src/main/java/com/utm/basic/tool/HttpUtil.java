package com.utm.basic.tool;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class HttpUtil {

    public static HttpClient httpClient=new DefaultHttpClient();
    public static final String BASE_URL="";

    public static String getRequest(String url) {
        try{
            HttpGet get=new HttpGet(url);
            HttpResponse httpResponse=httpClient.execute(get);

            if(httpResponse.getStatusLine().getStatusCode()==200)
            {
                String result=EntityUtils.toString(httpResponse.getEntity());
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();

        }

        return null;
    }


    public static String postRequest(String url,Map<String,String> rawParams)
    {
        try{

            HttpPost post=new HttpPost(url);

            List<NameValuePair> params=new ArrayList<NameValuePair>();

            for(String key:rawParams.keySet())
            {
                params.add(new BasicNameValuePair(key,rawParams.get(key)));
            }

            post.setEntity(new UrlEncodedFormEntity(params,"utf-8"));

            HttpResponse httpResponse=httpClient.execute(post);

            if(httpResponse.getStatusLine().getStatusCode()==200)
            {
                String result=EntityUtils.toString(httpResponse.getEntity());
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;

    }


    public static void main(String[] args) throws Exception {
        testTeacherLogin();
        //testStudent();
        //testTeacher();
    }

    private static void testStudentLogin(){
        LinkedHashMap<String,String> userMap=new LinkedHashMap<String, String>();
        userMap.put("userEntity.name","qqqq");
        userMap.put("userEntity.password","1111");
        String result=HttpUtil.postRequest("http://192.168.24.12:8080/workattence/LoginForAndroidAction",userMap);

        System.out.println("结果:"+result);

    }

    private static void testTeacherLogin(){
        LinkedHashMap<String,String> userMap=new LinkedHashMap<String, String>();
        userMap.put("userEntity.name","rrrrrrr");
        userMap.put("userEntity.password","44");
        String result=HttpUtil.postRequest("http://192.168.24.12:8080/workattence/LoginForAndroidAction",userMap);

        System.out.println("结果:"+result);

    }

    private static void testTeacher(){
        LinkedHashMap<String,String> teacherMap=new LinkedHashMap<String, String>();
        teacherMap.put("teacherEntity.id","4028988c4651f14a014651f3cc620003");
        teacherMap.put("teacherEntity.code","44");
        teacherMap.put("type","T");
        //teacherMap.put("loginType","come");
        teacherMap.put("loginType","leave");
        String result=HttpUtil.postRequest("http://192.168.24.12:8080/workattence/AndroidMainWorkattenceAction",teacherMap);
        System.out.println("结果:"+result);
    }

    private static void testStudent(){

        LinkedHashMap<String,String> studentMap=new LinkedHashMap<String, String>();
        studentMap.put("studentEntity.id","4028988c4651f14a014651f2b2fa0001");
        studentMap.put("studentEntity.code","22");
        studentMap.put("type","S");
        studentMap.put("loginType","come");
        //studentMap.put("loginType","leave");
        String result=HttpUtil.postRequest("http://192.168.24.12:8080/workattence/AndroidMainWorkattenceAction",studentMap);

        System.out.println("结果:"+result);
    }


}
