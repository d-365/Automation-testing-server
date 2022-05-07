package com.dujun.springboot.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.common.selenium.WebAssertType;
import com.dujun.springboot.entity.UiWebCase;
import com.dujun.springboot.service.impl.UiWebCaseServiceImpl;
import jdk.nashorn.internal.objects.NativeUint8Array;
import org.apache.commons.io.IOUtils;
import org.hibernate.bytecode.internal.bytebuddy.PassThroughInterceptor;
import org.springframework.beans.factory.BeanIsAbstractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.testng.collections.Lists;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dujun
 * @since 2022-04-11
 */
@RestController
@RequestMapping("/webCase")
public class UiWebCaseController {

    @Autowired
    private UiWebCaseServiceImpl webCaseService;

    @PostMapping("/caseList")
    public Result<?> caseList() {
        return webCaseService.caseList();
    }

    // 更新用例
    @PostMapping("/update")
    public Result<?> update(@RequestBody UiWebCase uiWebCase) {
        return webCaseService.update(uiWebCase);
    }

    // 删除用例
    @PostMapping("/delete")
    public Result<?> delWebCase(@RequestBody UiWebCase webCase) throws Exception {
        return webCaseService.delWebCase(webCase);
    }

    // 复制用例
    @PostMapping("/deepCopy")
    public Result<?> deepCopy(@RequestBody UiWebCase uiWebCase) {
        return webCaseService.deepCopy(uiWebCase);
    }

    //用例步骤列表
    @GetMapping("/steps/{caseId}")
    public Result<?> steps(@PathVariable("caseId") Integer caseId) {
        return webCaseService.steps(caseId);
    }

    @DeleteMapping("/caseStep/delete/{stepId}")
    public Result<?> delCaseStep(@PathVariable("stepId") Integer stepId) {
        return webCaseService.delCaseStep(stepId);
    }

    // web-case用例调试
    @PostMapping("/debug/{caseId}")
    public Result<?> debugCase(@RequestBody String payload, @PathVariable("caseId") Integer caseId, HttpServletRequest request) {
        return webCaseService.debugCase(caseId, payload, request);
    }

    // selenium-server文件下载
    @PostMapping("/seleniumServer")
    public String downloadServer(HttpServletResponse response) {
        // 定义输出文件流--字节码输出到httpResponse
        ServletOutputStream os = null;
        // 定义输入文件流--目标文件读取到内存中
        BufferedInputStream bufferedInputStream = null;

        try {
            //1 : 获取文件位置,new文件对象
            String filePath = ResourceUtils.getURL("classpath:").getPath();
            File file = new File(filePath,"static"+File.separator+"data"+File.separator+"selenium-server-4.1.3.jar");
            // 2: 获取HttpResponse输出流对象
            os = response.getOutputStream();
            response.reset();
            // 设置响应头为文件流
            response.setContentType("application/x-download;charset=GBK");
            // 设置文件别名
            response.setHeader("Content-Disposition","seleniumGrid.jar");
            // 3：将对象转换成 缓冲输入字节流
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            // 4: ServletOutputStream遍历写入字节数据
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            // 5: 刷新保存缓冲区
            os.flush();
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";

    }



    @PostMapping("/assertType")
    public Result<?> assertType(){
        List<HashMap<String,String>> types = Lists.newArrayList();
        for (WebAssertType assertType : WebAssertType.values()) {
            HashMap<String,String> type = new HashMap<>();
            type.put("name",assertType.getDescribe());
            type.put("value",assertType.toString());
            types.add(type);
        }

        return Result.success(types);
    }
}

