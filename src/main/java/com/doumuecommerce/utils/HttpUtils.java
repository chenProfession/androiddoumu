package com.doumuecommerce.utils;

import android.app.Activity;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class HttpUtils {
    private static Logger logger = Logger.getLogger(HttpUtils.class.getName());

    public String sendRequestPost(String pathUrl, JSONObject jsonData){

        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        String jsonString = null;

        try {
            URL url = new URL(pathUrl);

            /** json串转string类型 **/
            String content = String.valueOf(jsonData);

            /** 开启链接 **/
            urlConnection = (HttpURLConnection) url.openConnection();

            /** optional request header **/
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            /** optional request header **/
            urlConnection.setRequestProperty("Accept", "application/json");

            /** for Get request **/
            urlConnection.setConnectTimeout(5000);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");

            /** 写输出流，将要转的参数写入流里 **/
            OutputStream os = urlConnection.getOutputStream();

            /** 字符串写进二进流 **/
            os.write(content.getBytes());
            os.close();

            int code = urlConnection.getResponseCode();

            /** 与后台交互成功返回 200 **/
            if(code==200){

                /** 读取返回的json数据 **/
                inputStream = urlConnection.getInputStream();

                /** 调用自己写的NetUtils() 将流转成string类型 **/
                jsonString = readString(inputStream);

            }else{
                logger.info("数据提交失败");
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return jsonString;
    }

    public String sendRequestGet(String pathUrl, Map<String, String> mapData){

        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        String jsonString = null;
        StringBuffer buf=new StringBuffer();

        try {

            /** 取Map中的所有key **/
            Set<String> keySet = mapData.keySet();

            /** 遍历存放所有key的Set集合 **/
            Iterator<String> it =keySet.iterator();
            /** 利用了Iterator迭代器 **/
            while(it.hasNext()){
                /** 得到每一个key **/
                String key = it.next();
                /** 通过key获取对应的value **/
                String value = mapData.get(key);

                buf.append( "&" + key + "=" + value );
            }

            String urlWithParams = pathUrl + "?" + buf.toString();

            URL url = new URL(urlWithParams);

            /** 开启链接 **/
            urlConnection = (HttpURLConnection) url.openConnection();

            /** optional request header **/
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            /** optional request header **/
            urlConnection.setRequestProperty("Accept", "application/json");

            /** for Get request **/
            urlConnection.setConnectTimeout(5000);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");
            int code = urlConnection.getResponseCode();

            /** 与后台交互成功返回 200 **/
            if(code==200){

                /** 读取返回的json数据 **/
                inputStream = urlConnection.getInputStream();

                /** 调用自己写的NetUtils() 将流转成string类型 **/
                jsonString = readString(inputStream);

            }else{
                logger.info("数据提交失败");
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return jsonString;
    }

    public String sendRequestGet(String pathUrl){

        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        String jsonString = null;

        try {

            URL url = new URL(pathUrl);

            /** 开启链接 **/
            urlConnection = (HttpURLConnection) url.openConnection();

            /** optional request header **/
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            /** optional request header **/
            urlConnection.setRequestProperty("Accept", "application/json");

            /** for Get request **/
            urlConnection.setConnectTimeout(5000);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");
            int code = urlConnection.getResponseCode();

            /** 与后台交互成功返回 200 **/
            if(code==200){

                /** 读取返回的json数据 **/
                inputStream = urlConnection.getInputStream();

                /** 调用自己写的NetUtils() 将流转成string类型 **/
                jsonString = readString(inputStream);

            }else{
                logger.info("数据提交失败");
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return jsonString;
    }

    public String readString(InputStream is){

        return new String(readBytes(is));
    }

    public byte[] readBytes(InputStream is){
        try {
            byte[] buffer = new byte[1024];
            int len = -1 ;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((len = is.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }
            baos.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }
}
