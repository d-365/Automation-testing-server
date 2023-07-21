/*
  author     : dujun
  date       : 2021/12/2 13:58
  description: 告诉大家我是干啥的
 */

package com.dujun.springboot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class request {

    //创建HttpClient对象
     CloseableHttpClient httpClient = HttpClientBuilder.create().build();
     CloseableHttpResponse response = null;

     RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(10000)
            .setConnectionRequestTimeout(10000)
            .setSocketTimeout(10000).build();

    //get不带参数传递
    public  CloseableHttpResponse get(String url){
        //创建get请求
        HttpGet request = new HttpGet(url);
        //执行get请求
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    // get 传递参数 带请求头信息
    public CloseableHttpResponse get(String url, HashMap<String, String> headers, HashMap<String, String> params) {
        try {
            // 添加get请求参数
            URIBuilder uriBuilder = new URIBuilder(url);
            for (Map.Entry<String, String> entry_params : params.entrySet()) {
                if (entry_params.getKey() != null && entry_params.getValue() != null) {
                    uriBuilder.addParameter(entry_params.getKey(), entry_params.getValue());
                }
            }

            // 创建get请求
            HttpGet request = new HttpGet(uriBuilder.toString());
            // 装载header信息
            for (Map.Entry<String,String> entry: headers.entrySet()){
                if(entry.getKey() != null && entry.getValue() !=null){
                    request.setHeader(entry.getKey(),entry.getValue());
                }
            }
            // 发送get请求
            response = httpClient.execute(request);

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    // get 传递参数 带请求头信息
    public CloseableHttpResponse get(String url, HashMap<String, String> headers) {
        try {
            // 创建get请求
            HttpGet request = new HttpGet(url);
            // 装载header信息
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    request.setHeader(entry.getKey(), entry.getValue());
                }
            }
            // 发送get请求
            response = httpClient.execute(request);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    // post（传递json类型数据)
    public CloseableHttpResponse post(String url, HashMap<String, String> headers, String payload) {
        HttpPost request = new HttpPost(url);
        request.setConfig(requestConfig);
        // 加载请求头
        for (String key : headers.keySet()) {
            if (key != null) {
                request.addHeader(key, headers.get(key));
            }
        }
        try {
            // 加载payload
            request.setEntity(new StringEntity(payload, StandardCharsets.UTF_8));
            response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }

    // post（自动将map转换成json)
    public <T, K> CloseableHttpResponse post(String url, HashMap<String, String> headers, HashMap<T, K> payload) {

        HttpPost request = new HttpPost(url);
        // 加载请求头
        for (String entry : headers.keySet()) {
            if (entry != null) {
                request.addHeader(entry, headers.get(entry));
            }
        }

        try {
            // 加载payload
            String payloads = JSON.toJSONString(payload);
            request.setEntity(new StringEntity(payloads, StandardCharsets.UTF_8));
            response = httpClient.execute(request);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }

    // post(url传参)
    public   CloseableHttpResponse post_url(String url , HashMap<String,String> headers, HashMap<String,String> payload){

        try {
            // 添加get请求参数
            URIBuilder uriBuilder  = new URIBuilder(url);
            for(Map.Entry<String,String> entry_params : payload.entrySet()){
                if(entry_params.getKey() != null && entry_params.getValue()!=null ){
                    uriBuilder.addParameter(entry_params.getKey(),entry_params.getValue());
                }
            }

            // 创建get请求
            HttpPost request = new HttpPost(uriBuilder.toString());
            // 装载header信息
            for (Map.Entry<String,String> entry: headers.entrySet()){
                if(entry.getKey() != null && entry.getValue() !=null){
                    request.setHeader(entry.getKey(),entry.getValue());
                }
            }
            // 发送get请求
            response= httpClient.execute(request);

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    //delete请求
    public  CloseableHttpResponse delete(String url,HashMap<String, String> headers){
        HttpDelete request = new HttpDelete(url);
        // 加载请求头
        for(String key : headers.keySet()){
            if(key!=null){
                request.addHeader(key,headers.get(key));
            }
        }

        try {
            response = httpClient.execute(request);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return response;

    }

    public  void close(){
        try {
            if(response!=null){
                response.close();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     *
     * @param response HttpResponse 对象
     * @return http状态码
     */
    public  int getStatusCode(CloseableHttpResponse response){
        return response.getStatusLine().getStatusCode();
    }

    /**
     * @param response HttpResponse 对象
     * @return 返回Json格式的HTTP响应体
     */
    public  JSONObject getResponseJson(CloseableHttpResponse response){
        String responseStr = null;
        try {
            responseStr = EntityUtils.toString(response.getEntity());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return JSON.parseObject(responseStr);
    }

    // 获取响应头信息
    public  ArrayList<HashMap<String,String>> getResHeaders(CloseableHttpResponse response){
        ArrayList<HashMap<String,String>> headerList = new ArrayList<>();
        Header[] headers =response.getAllHeaders();
        HashMap<String,String> hashMap = new HashMap<>();
        for(Header header: headers){
            String[] strings = header.toString().split(":");
            hashMap.put(strings[0],strings[1]);

        }
        headerList.add(hashMap);
        return headerList;
    }

}
