/*
  author     : dujun
  date       : 2021/12/6 18:05
  description: 告诉大家我是干啥的
 */

package com.dujun.springboot.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.common.selenium.SeleniumUtils;
import com.dujun.springboot.entity.*;
import com.dujun.springboot.entity.sonEntity.ApiConsole;
import com.dujun.springboot.mapper.ActionMapper;
import com.dujun.springboot.mapper.DbConfigMapper;
import com.dujun.springboot.mapper.PlanRoundMapper;
import com.dujun.springboot.mapper.PrtDomainMapper;
import com.dujun.springboot.utils.BeanContext;
import com.dujun.springboot.utils.request;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  接口请求通用方法封装
 *  1： 接口返回值断言 （responseCode / resHeader / resJson）
 *  2: 接口返回值提取 （responseCode / resHeader / resJson）
 */
@Slf4j
public  class ApiCommon {

    static SeleniumUtils seleniumUtils = BeanContext.getApplicationContext().getBean(SeleniumUtils.class);
    static ActionMapper actionMapper = BeanContext.getApplicationContext().getBean(ActionMapper.class);
    static DbConfigMapper dbConfigMapper = BeanContext.getApplicationContext().getBean(DbConfigMapper.class);
    static PrtDomainMapper domainMapper = BeanContext.getApplicationContext().getBean(PrtDomainMapper.class);
    static PlanRoundMapper planRoundMapper = BeanContext.getApplicationContext().getBean(PlanRoundMapper.class);

    // 参数提取变量列表
    static HashMap<String,String> apiGlobalParams = new HashMap<>();

    static request request = new request();

    // 单接口请求封装(数据库获取数据)
    public static ApiInfo apiDebugDb(Integer envId,ApiInfo apiInfo) {

        // 控制台输出
        ArrayList<ApiConsole> console = new ArrayList<>();

        CloseableHttpResponse response = null;
        // 请求方法
        String method = apiInfo.getMethod();
        // 获取接口中项目信息
        Integer projectId = apiInfo.getProjectId();

        // 获取域名信息
        String domain = domainMapper.getDomainByEnvId(envId,projectId);
        // 请求url
        String url = domain+apiInfo.getPath();

        //解析请求体类型
        String reqBodyType = apiInfo.getReqBodyType();

        //解析后的请求体
        HashMap<Object, Object> entity = parseEntityDb(apiInfo.getReqBodyData());

        //解析后的Headers
        HashMap<String,String> headers = parseHeadersDb(apiInfo.getReqHeader());

        //text格式请求体
        String textBody = "";
        long startTime = new Date().getTime();
        long endTime = 0;
        JSONObject rspJson = null;

        // 执行前置动作
        List<PlanRound> beforeExec = apiInfo.getBeforeExec();
        beforeExec = beforeExec==null?new ArrayList<>():beforeExec;
        if (beforeExec.size()>0){
            ArrayList<ApiConsole> beforeExecResult  = ApiCommon.exec(beforeExec);
            console.addAll(beforeExecResult);
        }

        //执行接口请求
        switch (method.toUpperCase()){
            case "DELETE":
                response = request.delete(url,headers);
                break;
            case "PUT":
                break;
            case "GET":
                //params
                ArrayList<HashMap> paramsRaw = apiInfo.getReqParams();
                //解析后的param
                HashMap<String,String> params = parseParamDb(paramsRaw);

                try {
                    response = request.get(url,headers,params);
                    endTime = new Date().getTime();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "POST":
                //发送post请求: form-data
                if(Objects.equals(reqBodyType, "1")){
                    response  = request.post(url,headers,entity);
                }//发送post请求: json
                else if(Objects.equals(reqBodyType, "2")){
                    String reqBodyJson = apiInfo.getReqBodyJson();
                    response = request.post(url,headers,reqBodyJson);
                }//发送post请求: 无请求体
                else {
                    response = request.post(url,headers,entity);
                }
                endTime = new Date().getTime();
                break;
        }

        // 执行后置动作
        List<PlanRound> tearDown = apiInfo.getAfterExec();
        System.out.println(tearDown);
        tearDown = tearDown==null?new ArrayList<>():tearDown;
        if (tearDown.size()>0){
            ArrayList<ApiConsole> tearDownResult  = ApiCommon.exec(tearDown);
            console.addAll(tearDownResult);
        }

        // 将接口数据返回至前端
        try {
            //响应体
            rspJson = request.getResponseJson(response);
            apiInfo.setRspBodyJson(rspJson);
            apiInfo.setRspBodySize(rspJson.size());
        } catch (JSONException jsonException){
            // text格式响应体
            try {
                apiInfo.setTextBody(EntityUtils.toString(response.getEntity()));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            apiInfo.setRspBodySize(textBody.length());
        }
        //控制台
        apiInfo.setLog(console);
        //响应时间
        apiInfo.setRspTime(endTime-startTime);
        //状态码
        String statusCode = String.valueOf(request.getStatusCode(response));
        apiInfo.setRspStatusCode(statusCode);

        ArrayList<HashMap<String, String>> rspHeader = request.getResHeaders(response);
        apiInfo.setRspHeaders(rspHeader);
        String rspCode = String.valueOf(request.getStatusCode(response));   //响应头

        //断言
        ArrayList<ApiConsole> AssertConsole = ApiCommon.apiAssertDb(apiInfo.getReqAssert(),rspJson,rspCode,rspHeader);
        ArrayList rspAsserts = apiInfo.getReqAssert();

        apiInfo.setRspAsserts(rspAsserts);
        console.addAll(AssertConsole);

        //接口请求结果
        if(apiInfo.getReqAssert()!=null){
            apiInfo.setResult(code_resultDb(statusCode,apiInfo.getReqAssert()));
        }else {
            apiInfo.setResult(code_resultDb(statusCode));
        }

        //提取参数
        ArrayList reqExtract =  apiInfo.getReqExtract();
        JSONObject reqExtractOne  = (JSONObject) reqExtract.get(0);
        if (reqExtract.size() == 1 && Objects.equals(reqExtractOne.getString("dataSource"), "")){
            log.info("不需要提取参数");
        }else {
            ApiCommon.apiExtractDb(reqExtract,rspJson,rspCode,rspHeader);
            apiInfo.setRspExtract(apiInfo.getReqExtract());
        }

        return apiInfo;
    }

    // 单接口请求封装
    public static Result<?> apiDebug(Integer envId,ApiInfo apiInfo) {
        // 控制台输出
        ArrayList<ApiConsole> console = new ArrayList<>();
        CloseableHttpResponse response = null;
        // 请求方法
        String method = apiInfo.getMethod();
        // 获取接口中项目信息
        Integer projectId = apiInfo.getProjectId();

        // 获取域名信息
        String domain = domainMapper.getDomainByEnvId(envId,projectId);

        // 请求url
        String url = domain+apiInfo.getPath();
        if(Objects.equals(method, "") || Objects.equals(url, "")){
            return Result.error("请求方式或url不能为空");
        }

        //解析请求体类型
        String reqBodyType = apiInfo.getReqBodyType();

        //解析后的请求体
        HashMap entity = parseEntity(apiInfo.getReqBodyData());

        //解析后的Headers
        HashMap<String,String> headers = parseHeaders(apiInfo.getReqHeader());

        //text格式请求体
        String textBody = "";
        long startTime = new Date().getTime();
        long endTime = 0;
        JSONObject rspJson = null;

        // 执行前置动作
        List<PlanRound> beforeExec = apiInfo.getBeforeExec();
        if (beforeExec.size()>0){
            ArrayList<ApiConsole> beforeExecResult  = ApiCommon.exec(beforeExec);
            console.addAll(beforeExecResult);
        }

        //执行接口请求
        switch (method.toUpperCase()){
            case "DELETE":
                response = request.delete(url,headers);
                break;
            case "PUT":
                System.out.println(method);
                break;
            case "GET":
                //params
                ArrayList<HashMap> paramsRaw = apiInfo.getReqParams();
                //解析后的param
                HashMap<String,String> params = new HashMap<>();
                for (HashMap param : paramsRaw) {
                    String key = (String) param.get("paramKey");
                    String value = (String) param.get("paramValue");
                    if(!Objects.equals(key, "") & !Objects.equals(value, "") ){
                        params.put(key,value);
                    }
                }
                try {
                    response = request.get(url,headers,params);
                    endTime = new Date().getTime();
                } catch (IllegalArgumentException illegalArgumentException){
                    return Result.error("Name may not be null");
                }
                break;
            case "POST":
                //发送post请求: form-data
                if(Objects.equals(reqBodyType, "1")){
                    response  = request.post(url,headers,entity);
                }//发送post请求: json
                else if(Objects.equals(reqBodyType, "2")){
                    String reqBodyJson = apiInfo.getReqBodyJson();
                    response = request.post(url,headers,reqBodyJson);
                }//发送post请求: 无请求体
                else {
                    response = request.post(url,headers,entity);
                }
                endTime = new Date().getTime();
                break;
        }

        // 执行后置动作
        List<PlanRound> tearDown = apiInfo.getAfterExec();
        if (tearDown.size()>0){
            ArrayList<ApiConsole> tearDownResult  = ApiCommon.exec(tearDown);
            console.addAll(tearDownResult);
        }

        // 将接口数据返回至前端
        try {
            //响应体
            assert response != null;
            rspJson = request.getResponseJson(response);
            apiInfo.setRspBodyJson(rspJson);
            apiInfo.setRspBodySize(rspJson.size());
        } catch (JSONException jsonException){
            // text格式响应体
            try {
                apiInfo.setTextBody(EntityUtils.toString(response.getEntity()));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            apiInfo.setRspBodySize(textBody.length());
        }
        //控制台
        apiInfo.setLog(console);
        //响应时间
        apiInfo.setRspTime(endTime-startTime);
        //状态码
        String statusCode = String.valueOf(request.getStatusCode(response));
        apiInfo.setRspStatusCode(statusCode);

        //响应头
        ArrayList<HashMap<String, String>> rspHeader = request.getResHeaders(response);
        apiInfo.setRspHeaders(rspHeader);
        String rspCode = String.valueOf(request.getStatusCode(response));

        //断言
        ArrayList<ApiConsole> AssertConsole = ApiCommon.apiAssert(apiInfo.getReqAssert(),rspJson,rspCode,rspHeader);
        ArrayList<RspAsserts> rspAsserts = apiInfo.getReqAssert();
        if(rspAsserts.size() ==1  ){
            if(Objects.equals(rspAsserts.get(0).getDataSource(), "")){
                rspAsserts = new ArrayList<>();
            }
        }
        apiInfo.setRspAsserts(rspAsserts);
        console.addAll(AssertConsole);

        //接口请求结果
        apiInfo.setResult(code_result(statusCode,apiInfo.getReqAssert()));

        //提取参数
        ArrayList<RspExtract> reqExtract =  apiInfo.getReqExtract();
        if (reqExtract.size() == 1 && Objects.equals(reqExtract.get(0).getDataSource(), "")){
            log.info("不需要提取参数");
        }else {
            ApiCommon.apiExtract(reqExtract,rspJson,rspCode,rspHeader);
            apiInfo.setRspExtract(apiInfo.getReqExtract());
        }

        return Result.success(apiInfo);
    }

    /**
     * 处理用户前置后置信息
     * @param apiInfo 接口详情
     * @return apiInfo
     */
    public static ApiInfo setUpTearDownDispose(ApiInfo apiInfo){
        // 处理前置后置信息
        List<Integer> setUpIds = apiInfo.getSetUpIds();
        List<Integer> tearDownIds = apiInfo.getTearDownIds();
        List<PlanRound> setUp = new ArrayList<>();
        List<PlanRound> tearDown = new ArrayList<>();
        if (setUpIds.size()>0){
            for (Integer setUpId : setUpIds) {
                setUp.add(planRoundMapper.selectById(setUpId));
            }
        }
        if (tearDownIds.size()>0){
            for (Integer tearDownId : tearDownIds) {
                tearDown.add(planRoundMapper.selectById(tearDownId));
            }
        }
        apiInfo.setBeforeExec(setUp);
        apiInfo.setAfterExec(tearDown);
        return apiInfo;
    }

    /**
     * @param reqAsserts 需要断言list
     * @param rspJson json格式响应体
     * @param rspCode 状态码
     * @param rspHeader 响应头
     * @return String
     */
    public static ArrayList<ApiConsole> apiAssert(ArrayList<RspAsserts> reqAsserts, JSONObject rspJson, String rspCode,ArrayList<HashMap<String,String>> rspHeader){
        ArrayList<ApiConsole> consoleMsg = new ArrayList<>();
        for(RspAsserts rspAsserts : reqAsserts){
            if(rspAsserts.getDataSource() !=null && rspAsserts.getExpectRelation()!=null && rspAsserts.getExtractExpress()!=null){
                //数据源
                String  dataSource = rspAsserts.getDataSource().trim();
                // 未解析表达式内容
                String  extractExpress = rspAsserts.getExtractExpress().trim();
                //期望值
                String  expectValue = rspAsserts.getExpectValue().trim();
                //期望关系
                String  expectRelation = rspAsserts.getExpectRelation().trim();
                //断言结果
                boolean result;
                // 实际值
                String realValue = "";
                if(Objects.equals(dataSource, "")|| Objects.equals(expectRelation, "") || Objects.equals(expectValue, "")){
                    rspAsserts.setAssertResult(true);
                    consoleMsg.add(new ApiConsole(true,"接口断言数据不完整",extractExpress));
                    break;
                }

                switch (dataSource){
                    case "code":
                        result = ApiCommon.assert_final(expectValue,rspCode,expectRelation);
                        rspAsserts.setRealValue(rspCode);
                        if(!result){
                            consoleMsg .add(new ApiConsole(false,"断言失败",extractExpress));
                        }else {
                            consoleMsg .add(new ApiConsole(true,"断言成功",extractExpress));
                        }
                        rspAsserts.setAssertResult(result);
                        break;
                    case "header":
                        realValue = ApiCommon.parseHeader(rspHeader,extractExpress);
                        result = ApiCommon.assert_final(expectValue,realValue,expectRelation);
                        rspAsserts.setRealValue(realValue);
                        rspAsserts.setAssertResult(result);
                        if(!result){
                            consoleMsg .add(new ApiConsole(false,"断言失败",extractExpress));
                        }else {
                            consoleMsg .add(new ApiConsole(true,"断言成功",extractExpress));
                        }
                        break;
                    case "bodyJson":
                        // 解析 提取表达式
                        try{
                            realValue = ApiCommon.parseJson(rspJson,extractExpress);
                            System.out.println("解析出的值是: "+realValue);
                        }catch (NullPointerException nullPointerException){
                            consoleMsg .add(new ApiConsole(false,"表达式有误",extractExpress));
                        }
                        //进行断言
                        result = ApiCommon.assert_final(expectValue,realValue,expectRelation);
                        rspAsserts.setRealValue(realValue);
                        rspAsserts.setAssertResult(result);
                        if(!result){
                            consoleMsg .add(new ApiConsole(false,"断言失败",extractExpress));
                        }else {
                            consoleMsg .add(new ApiConsole(true,"断言成功",extractExpress));
                        }
                        break;
                    default:
                        consoleMsg.add(new ApiConsole(true,"断言数据源不合法",extractExpress));
                        break;
                }
            }
        }
        return consoleMsg;

    }
    /**
     * @param expectValue  期望值
     * @param realityValue   实际值
     * @param expectRelation 期望关系
     * @return boolean
     */
    public static boolean assert_final(String expectValue,String realityValue,String expectRelation){
        if(Objects.equals(expectValue, "")|| Objects.equals(realityValue, "")|| Objects.equals(expectRelation, "")){
            return false;
        }
        if(expectValue == null||realityValue == null || expectRelation==null){
            return false;
        }
        switch (expectRelation){
            case "等于":
                return expectValue.equals(realityValue);
            case "大于":
                return expectValue.hashCode() > realityValue.hashCode();
            case "大于等于":
                return expectValue.hashCode() >= realityValue.hashCode();
            case "小于":
                return expectValue.hashCode() < realityValue.hashCode();
            case "小于等于":
                return expectValue.hashCode() <= realityValue.hashCode();
            case "包含":
                return expectValue.contains(realityValue);
            case "不包含":
                boolean results = expectValue.contains(realityValue);
                return !results;
            case "长度等于":
                return expectValue.length() == realityValue.length();
            case "长度大于":
                return expectValue.length() > realityValue.length();
            case "长度大于等于":
                return expectValue.length() >= realityValue.length();
            case "长度小于":
                return expectValue.length() < realityValue.length();
            case "长度小于等于":
                return expectValue.length() <= realityValue.length();
            default:
                System.out.println("输入的期望关系不合法");
                return false;
        }
    }

    /**
     * @param jsonObject 源json串
     * @param extractExpress  解析表达式
     */
    public static String  parseJson(JSONObject jsonObject,String extractExpress ){
        String finalValue = "";
        if(Objects.equals(extractExpress, "")){
            return "解析表达式不能为空"; }
        JSONObject ParseJsonObject = jsonObject;
        JSONArray parseJsonArray = null;
        if (extractExpress.contains(".")){
            String[] strings = extractExpress.split("\\.");
            for (int i = 0; i < strings.length; i++) {
                if (i<strings.length-1){
                    if(strings[i].contains("[")){
                        String[] parseArrayList =  strings[i].split("\\[");
                        for (String s : parseArrayList) {
                            if (s.endsWith("]")) {
                                s = s.substring(0, s.length() - 1);
                            }
                            if (s.matches("[0-9]+")) {
                                assert parseJsonArray != null;
                                ParseJsonObject = (JSONObject) parseJsonArray.get(Integer.parseInt(s));
                            } else {
                                try {
                                    parseJsonArray = ParseJsonObject.getJSONArray(s);
                                } catch (ClassCastException castException) {
                                    return "无法解析表达式值" + s;
                                }

                            }
                        }
                    }else {
                        try{
                            ParseJsonObject = JSONObject.parseObject((ParseJsonObject.getString(strings[i])));
                        }catch (JSONException jsonException){
                            return "无法解析表达式值:"+ strings[i];
                        }
                    }
                }
                else {
                    if(strings[i].contains("[")){
                        String[] parseArrayList =  strings[i].split("\\[");
                        for (String s : parseArrayList) {
                            if (s.endsWith("]")) {
                                s = s.substring(0, s.length() - 1);
                            }
                            if (s.matches("[0-9]+")) {
                                assert parseJsonArray != null;
                                finalValue = (String) parseJsonArray.get(Integer.parseInt(s));
                            } else {
                                try {
                                    parseJsonArray = ParseJsonObject.getJSONArray(s);
                                } catch (ClassCastException castException) {
                                    castException.printStackTrace();
                                    return "无法解析表达式值" + s;
                                }

                            }
                        }
                    }else {
                        finalValue=ParseJsonObject.getString(strings[i]);
                    }
                }
            }
        }else {
            if(extractExpress.contains("[")){
                String[] parseArrayList =  extractExpress.split("\\[");
                for (String s : parseArrayList) {
                    //
                    if (s.endsWith("]")) {
                        s = s.substring(0, s.length() - 1);
                    }
                    if (s.matches("[0-9]+")) {
                        assert parseJsonArray != null;
                        finalValue =  (String) parseJsonArray.get(Integer.parseInt(s));
                    } else {
                        try {
                            parseJsonArray = ParseJsonObject.getJSONArray(s);
                        } catch (ClassCastException castException) {
                            castException.printStackTrace();
                            return "无法解析表达式值" + s;
                        }

                    }
                }
            }else {
                finalValue = jsonObject.getString(extractExpress);
            }
        }
        if (finalValue == null){
            return "无法解析表达式值";
        }
        return finalValue;
    }
    //参数表达式解析(响应头)
    public static  String parseHeader(ArrayList<HashMap<String,String>> rspHeader ,String express){
        String finalValue = "";
        for (HashMap<String, String> hashMap : rspHeader) {
            finalValue = hashMap.get(express);
        }
        return finalValue;
    }

    /**
     * @param reqExtract 参数提取原始值
     * @param rspJson 响应体（Json）
     * @param rspCode 响应码
     * @param rspHeader 响应体
     */
    public static void apiExtract(ArrayList<RspExtract> reqExtract, JSONObject rspJson, String rspCode, ArrayList<HashMap<String,String>> rspHeader){

        for(RspExtract extract : reqExtract){
            if(extract.getDataSource()!=null&& extract.getExtractExpress()!=null){
                // 数据源
                String  dataSource = extract.getDataSource().trim();
                // 未解析表达式内容
                String  extractExpress = extract.getExtractExpress().trim();
                // 变量名称
                String varName = extract.getVarName();
                // 变量值
                String realValue = "";
                // 变量数据类型
                switch (dataSource){
                    case "code":
                        realValue = rspCode;
                        extract.setRealValue(rspCode);
                        break;
                    case "header":
                        // 解析 提取表达式
                        realValue = ApiCommon.parseHeader(rspHeader,extractExpress);
                        extract.setRealValue(realValue);
                        break;
                    case "bodyJson":
                        // 解析 提取表达式
                        realValue = ApiCommon.parseJson(rspJson,extractExpress);
                        extract.setRealValue(realValue);
                        break;
                    default:
                        System.out.println("数据源不合法");
                        break;
                }
                apiGlobalParams.put(varName,realValue);
            }

        }

    }

    // 获取用例执行后的接口参数
    public static Map<String, String> getGlobalParams(){
        return apiGlobalParams;
    }


    // 解析请求头
    public static HashMap<String,String> parseHeaders(ArrayList<HashMap> headerList){
        HashMap<String,String> headers = new HashMap<>();
        for (HashMap header : headerList) {
            String key = (String) header.get("HeadersKey");
            String value = (String) header.get("HeadersValue");

            if(!Objects.equals(key, "") && !Objects.equals(value, "") && key!=null && value != null){

                List keyResult = matchApiParams(key);
                List valueResult = matchApiParams(value);

                // 将用例中变量赋值给对应的参数值
                if((Boolean) keyResult.get(0)){
                    key = findApiParams((String) keyResult.get(1));
                }
                if((Boolean) valueResult.get(0)){
                    value= findApiParams((String) valueResult.get(1));
                }

                headers.put(key,value);

            }
        }
        return headers;
    }

    // 解析请求头
    public static HashMap<String,String> parseParam(ArrayList<HashMap> paramList){
        HashMap<String,String> paramFinal = new HashMap<>();
        for (HashMap header : paramList) {
            String key = (String) header.get("paramKey");
            String value = (String) header.get("paramValue");

            if(!Objects.equals(key, "") && !Objects.equals(value, "") && key!=null && value != null){

                List keyResult = matchApiParams(key);
                List valueResult = matchApiParams(value);

                // 将用例中变量赋值给对应的参数值
                if((Boolean) keyResult.get(0)){
                    key = findApiParams((String) keyResult.get(1));
                }
                if((Boolean) valueResult.get(0)){
                    value= findApiParams((String) valueResult.get(1));
                }

                paramFinal.put(key,value);

            }
        }
        return paramFinal;
    }

    // 解析请求头
    public static HashMap<String,String> parseHeadersDb(ArrayList headerList){
        HashMap<String,String> headers = new HashMap<>();
        if(headerList.size()==0){return headers;}
        for (int i = 0; i < headerList.size(); i++) {
            JSONObject header = (JSONObject) headerList.get(i);
            String key =  header.getString("HeadersKey");
            String value = header.getString("HeadersValue");

            if(!Objects.equals(key, "") && !Objects.equals(value, "") && key!=null && value != null){

                List keyResult = matchApiParams(key);
                List valueResult = matchApiParams(value);

                // 将用例中变量赋值给对应的参数值
                if((Boolean) keyResult.get(0)){
                    key = findApiParams((String) keyResult.get(1));
                }
                if((Boolean) valueResult.get(0)){
                    value= findApiParams((String) valueResult.get(1));
                }

                headers.put(key,value);

            }
        }

        return headers;
    }

    // 解析请求体(控制台传递数据)
    public static HashMap parseEntity(ArrayList<HashMap> payloadsRaw){
        HashMap entity = new HashMap<>();
        if(payloadsRaw.size()==0){return entity;}
        for (HashMap hashMap : payloadsRaw) {
            String key = (String) hashMap.get("dataKey");
            String value = (String) hashMap.get("dataValue");
            if(!Objects.equals(key, "") && !Objects.equals(value, "") && key!=null && value != null){
                List keyResult = ApiCommon.matchApiParams(key);
                List valueResult = ApiCommon.matchApiParams(value);
                // 将用例中变量赋值给对应的参数值
                if((Boolean) keyResult.get(0)){
                    key = findApiParams((String) keyResult.get(1));
                }
                if((Boolean) valueResult.get(0)){
                    value= findApiParams((String) valueResult.get(1));
                }
                entity.put(key,value);

            }
        }
        return entity;
    }

    // 解析请求体(数据库查询数据)
    public static HashMap parseEntityDb(ArrayList payloadsRaw){
        HashMap entity = new HashMap<>();
        if(payloadsRaw.size()==0){return entity;}
        for (int i = 0; i < payloadsRaw.size(); i++) {
            JSONObject payload = (JSONObject) payloadsRaw.get(i);
            String key =  payload.getString("dataKey");
            String value =  payload.getString("dataValue");
            if(!Objects.equals(key, "") && !Objects.equals(value, "") && key!=null && value != null){
                List keyResult = ApiCommon.matchApiParams(key);
                List valueResult = ApiCommon.matchApiParams(value);
                // 将用例中变量赋值给对应的参数值
                if((Boolean) keyResult.get(0)){
                    key = findApiParams((String) keyResult.get(1));
                }
                if((Boolean) valueResult.get(0)){
                    value= findApiParams((String) valueResult.get(1));
                }
                entity.put(key,value);

            }
        }

        return entity;
    }

    // 解析请求参数(数据库查询数据)
    public static HashMap parseParamDb(ArrayList params){
        HashMap<String,String> paramFinal = new HashMap<>();
        if(params.size()==0){return paramFinal;}
        for (int i = 0; i < params.size(); i++) {
            JSONObject header = (JSONObject) params.get(i);
            String key =  header.getString("paramKey");
            String value = header.getString("paramValue");
            if(!Objects.equals(key, "") && !Objects.equals(value, "") && key!=null && value != null){
                List keyResult = matchApiParams(key);
                List valueResult = matchApiParams(value);
                // 将用例中变量赋值给对应的参数值
                if((Boolean) keyResult.get(0)){
                    key = findApiParams((String) keyResult.get(1));
                }
                if((Boolean) valueResult.get(0)){
                    value= findApiParams((String) valueResult.get(1));
                }
                paramFinal.put(key,value);

            }
        }
        return paramFinal;
    }

    // 执行前置-后置动作
    public static ArrayList<ApiConsole> exec(List<PlanRound> exec){
        ArrayList<ApiConsole> consoleMsg = new ArrayList<>();
        for (PlanRound planRound : exec) {
            ApiConsole apiConsole = new ApiConsole();
            Integer actionId = planRound.getActionId();
            String actionValue = planRound.getParams();
            String operationData = planRound.getOperateData();
            // 获取Action类型
            Action action = actionMapper.selectById(actionId);
            actionEnum AcType = actionEnum.valueOf(action.getActionKey());
            if (AcType==actionEnum.QUERYSQL||AcType==actionEnum.UPDATESQL){
                DbConfig dbConfig = dbConfigMapper.selectById(operationData);
                UIConsole  uiConsole =  seleniumUtils.runAction(action.getActionKey(), dbConfig,actionValue);
                String msg = uiConsole.getMsg();
                Integer code = uiConsole.getCode();
                apiConsole.setMsg(msg);
                apiConsole.setSuccess(code == 0);
            }
            consoleMsg.add(apiConsole);
        }
        return  consoleMsg;
    }

    //根据接口返回状态码 写入接口运行结果
    public static Boolean code_result(String statusCode,ArrayList<RspAsserts> rspAsserts){
        if(statusCode.startsWith("4") || statusCode.startsWith("5")){
            return false;
        }
        for (RspAsserts rspAssert : rspAsserts) {

            if(!rspAssert.getAssertResult()){
                return false;
            }
        }
        return true;
    }

    //根据接口返回状态码 写入接口运行结果(jsonObject转javaBean)
    public static Boolean code_resultDb(String statusCode,ArrayList rspAsserts){
        if(statusCode.startsWith("4") || statusCode.startsWith("5")){
            return false;
        }
        for (int i = 0; i < rspAsserts.size(); i++) {
            JSONObject rspAssertRaw = (JSONObject) rspAsserts.get(i);
            RspAsserts rspAssert = JSON.parseObject(String.valueOf(rspAssertRaw), RspAsserts.class);
            if(rspAssert.getAssertResult()==null){
                return true;
            }else{
                return rspAssert.getAssertResult();
            }
        }

        return true;
    }

    //根据接口返回状态码 写入接口运行结果(jsonObject转javaBean)
    public static Boolean code_resultDb(String statusCode){
        if(statusCode.startsWith("4") || statusCode.startsWith("5")){
            return false;
        }
        return true;
    }

    // 正则表达式匹配用例中的参数${}
    public static List matchApiParams(String value){
        String expression = "(\\$\\{)(.*?)(})";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(value);

        List matcherResult = new ArrayList();
        Boolean find = matcher.find();
        matcherResult.add(find);
        if (find){
            System.out.println("matcher.group(2)"+matcher.group(2));
            matcherResult.add(matcher.group(2));
        }
        return matcherResult;
    }

    // 用例全局变量 赋值 给用例参数${}
    public static String findApiParams(String apiParam){
        if(apiGlobalParams.containsKey(apiParam)){
            return apiGlobalParams.get(apiParam);
        }
        return apiParam;
    }

    // 接口断言
    public static ArrayList<ApiConsole> apiAssertDb(ArrayList reqAsserts, JSONObject rspJson, String rspCode,ArrayList<HashMap<String,String>> rspHeader){
        ArrayList<ApiConsole> consoleMsg = new ArrayList<>();
        for (int i = 0; i < reqAsserts.size(); i++) {
            JSONObject rspAssert = (JSONObject) reqAsserts.get(i);
            RspAsserts rspAsserts = JSON.parseObject(String.valueOf(rspAssert), RspAsserts.class);
            if(rspAsserts.getDataSource() !=null && rspAsserts.getExpectRelation()!=null && rspAsserts.getExtractExpress()!=null){
                //数据源
                String  dataSource = rspAsserts.getDataSource().trim();
                // 未解析表达式内容
                String  extractExpress = rspAsserts.getExtractExpress().trim();
                //期望值
                String  expectValue = rspAsserts.getExpectValue().trim();
                //期望关系
                String  expectRelation = rspAsserts.getExpectRelation().trim();
                //断言结果
                boolean result;
                // 实际值
                String realValue = "";
                if(Objects.equals(dataSource, "")|| Objects.equals(extractExpress, "") || Objects.equals(expectRelation, "") || Objects.equals(expectValue, "")){
                    rspAsserts.setAssertResult(true);
                    consoleMsg.add(new ApiConsole(true,"接口断言数据不完整",extractExpress));
                    break;
                }

                switch (dataSource){
                    case "code":
                        result = ApiCommon.assert_final(expectValue,rspCode,expectRelation);
                        rspAsserts.setRealValue(rspCode);
                        if(!result){
                            consoleMsg .add(new ApiConsole(false,"断言失败",extractExpress));
                        }else {
                            consoleMsg .add(new ApiConsole(true,"断言成功",extractExpress));
                        }
                        rspAsserts.setAssertResult(result);
                        break;
                    case "header":
                        realValue = ApiCommon.parseHeader(rspHeader,extractExpress);
                        result = ApiCommon.assert_final(expectValue,realValue,expectRelation);
                        rspAsserts.setRealValue(realValue);
                        rspAsserts.setAssertResult(result);
                        if(!result){
                            consoleMsg .add(new ApiConsole(false,"断言失败",extractExpress));
                        }else {
                            consoleMsg .add(new ApiConsole(true,"断言成功",extractExpress));
                        }
                        break;
                    case "bodyJson":
                        // 解析 提取表达式
                        try{
                            realValue = ApiCommon.parseJson(rspJson,extractExpress);
                            System.out.println("解析出的值是: "+realValue);
                        }catch (NullPointerException nullPointerException){
                            consoleMsg .add(new ApiConsole(false,"表达式有误",extractExpress));
                        }
                        //进行断言
                        result = ApiCommon.assert_final(expectValue,realValue,expectRelation);
                        rspAsserts.setRealValue(realValue);
                        rspAsserts.setAssertResult(result);
                        if(!result){
                            consoleMsg .add(new ApiConsole(false,"断言失败",extractExpress));
                        }else {
                            consoleMsg .add(new ApiConsole(true,"断言成功",extractExpress));
                        }
                        break;
                    default:
                        consoleMsg.add(new ApiConsole(true,"断言数据源不合法",extractExpress));
                        break;
                }
            }

        }

        return consoleMsg;

    }

    //参数提取
    public static void apiExtractDb(ArrayList reqExtract, JSONObject rspJson, String rspCode, ArrayList<HashMap<String,String>> rspHeader){

        for (int i = 0; i < reqExtract.size(); i++) {
            JSONObject extractJson = (JSONObject) reqExtract.get(i);
            RspExtract  extract = JSON.parseObject(String.valueOf(extractJson), RspExtract.class);
            if(extract.getDataSource()!=null&& extract.getExtractExpress()!=null){
                // 数据源
                String  dataSource = extract.getDataSource().trim();
                // 未解析表达式内容
                String  extractExpress = extract.getExtractExpress().trim();
                // 变量名称
                String varName = extract.getVarName();
                // 变量值
                String realValue = "";
                // 变量数据类型
                switch (dataSource){
                    case "code":
                        realValue = rspCode;
                        extract.setRealValue(rspCode);
                        break;
                    case "header":
                        // 解析 提取表达式
                        realValue = ApiCommon.parseHeader(rspHeader,extractExpress);
                        extract.setRealValue(realValue);
                        break;
                    case "bodyJson":
                        // 解析 提取表达式
                        realValue = ApiCommon.parseJson(rspJson,extractExpress);
                        extract.setRealValue(realValue);
                        break;
                    default:
                        System.out.println("数据源不合法");
                        break;
                }
                apiGlobalParams.put(varName,realValue);
            }

        }

    }


}

