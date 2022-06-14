//package com.dujun.springboot.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.dujun.springboot.VO.Result;
//import com.dujun.springboot.entity.sonEntity.ApiDomain;
//import com.dujun.springboot.mapper.EnvMapper;
//import com.dujun.springboot.service.EnvService;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
///**
// * <p>
// *  服务实现类
// * </p>
// *
// * @author dujun
// * @since 2021-12-10
// */
//@Service
//public class EnvServiceImpl extends ServiceImpl<EnvMapper, Env> implements EnvService {
//
//    @Resource
//    private EnvMapper envMapper;
//
//    @Override
//    // 环境列表
//    public Result<List<Env>> envList() {
//        List<Env> envList =envMapper.selectList(null);
//        // 填充一个空的域名
//        ArrayList<ApiDomain> arrayList = new ArrayList<>();
//        ApiDomain apiDomain = new ApiDomain();
//        arrayList.add(apiDomain);
//
//        for (Env env : envList) {
//            if(env.getApiDomain() == null){
//                env.setApiDomain(arrayList);
//            }
//
//        }
//        return Result.success(envList);
//    }
//
//    //更新环境信息
//    public Result<?> envUpdate(ArrayList<Env> envList) {
//        for (Env env : envList) {
//            Integer envId = env.getId();
//            // 新增
//            if(envId == null && !Objects.equals(env.getEnvName(), "")){
//                envMapper.insert(env);
//            }else if (envId != null){
//                //修改
//                envMapper.updateById(env);
//            }
//        }
//        return Result.success();
//
//    }
//
//    // 查看单个环境详情
//    public Result<Env> envDetail(int id){
//        Env env = envMapper.selectOne(new QueryWrapper<Env>().eq("id",id));
//        return Result.success(env);
//    }
//
//    //删除环境信息
//    public Result<?> delEnv(int id){
//        try {
//            envMapper.deleteById(id);
//        }catch (Exception e){
//            return Result.error("该环境存在关联的数据无法删除");
//        }
//        return Result.success();
//    }
//
//}
