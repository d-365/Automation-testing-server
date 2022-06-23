package com.dujun.springboot.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.dbcp2.Dbcp2Config;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.DbConfig;
import com.dujun.springboot.entity.EnvConfig;
import com.dujun.springboot.mapper.DbConfigMapper;
import com.dujun.springboot.mapper.EnvConfigMapper;
import com.dujun.springboot.mapper.PrtDomainMapper;
import com.dujun.springboot.service.EnvConfigService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 环境配置表 服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-05-09
 */
@Service
public class EnvConfigServiceImpl extends ServiceImpl<EnvConfigMapper, EnvConfig> implements EnvConfigService {

    @Resource
    private DbConfigMapper dbConfigMapper;
    @Resource
    private EnvConfigMapper envConfigMapper;
    @Resource
    private PrtDomainMapper domainMapper;

    @Override
    public Result<?> envList() {
        List<HashMap<String,Object>> resultList = new ArrayList<>();
        // 所有的环境信息
        LambdaQueryWrapper<EnvConfig> envWrapper = new LambdaQueryWrapper<>();
        envWrapper.eq(EnvConfig::getDelFlag,0);
        List<EnvConfig> envConfig = envConfigMapper.selectList(envWrapper);
        // 环境对应的DBConfig信息
        for (EnvConfig env : envConfig) {
            HashMap<String,Object> result = new HashMap<>();
            Set<Integer> dbIds = env.getDbId();
            result.put("envInfo",env);
            List<DbConfig> dbConfigList = new ArrayList<>();
            for (Integer dbId : dbIds) {
                DbConfig db = dbConfigMapper.selectById(dbId);
                dbConfigList.add(db);
            }
            result.put("dbInfo",dbConfigList);
            resultList.add(result);
        }
        return Result.success(resultList);
    }

    @Override
    public Result<?> updateAndSave(String payload) {
        if (payload!=null){
            JSONObject jsonObject = JSONObject.parseObject(payload);
            EnvConfig envConfig = jsonObject.getObject("envInfo",EnvConfig.class);
            JSONArray dbConfigList = jsonObject.getJSONArray("dbInfo");
            if (envConfig.getEnvName() ==null){
                return Result.error("环境名称不能为空");
            }

            // 新接收的DBId
            Set<Integer> dbIds = new HashSet<>();
            // 更新数据库配置信息
            if (dbConfigList.size()!=0){
                for (Object db : dbConfigList) {
                    DbConfig dbConfig = JSONObject.parseObject(db.toString(),DbConfig.class);
                    Result<?> result = updateDb(dbConfig);
                    if (Objects.equals(result.getCode(), "1")){
                        return result;
                    }
                    dbIds.add((Integer) result.getData());
                }
            }
            envConfig.setDbId(dbIds);
            // 更新环境配置信息
            if (envConfig.getId()!= null){
                EnvConfig oldEnv = envConfigMapper.selectById(envConfig.getId());
                Set<Integer> oldDbIds = oldEnv.getDbId();
                Set<Integer> newDbIds = envConfig.getDbId();
                // 判断当前的环境信息下的 数据库配置 不一致进行删除
                for (Integer oldDbId : oldDbIds) {
                    if (!newDbIds.contains(oldDbId)){
                        dbConfigMapper.deleteById(oldDbId);
                    }
                }
                envConfigMapper.updateById(envConfig);
            }else {
                envConfigMapper.insert(envConfig);
            }
        }
        return Result.success();
    }

    @Override
    public Result<?> delEnv(Integer id) {
        EnvConfig env = envConfigMapper.selectById(id);
        if (env!=null){
            // 删除环境关联数据
            Set<Integer> dbIds = env.getDbId();
            for (Integer dbId : dbIds) {
                // 删除环境对应的 DB信息
                dbConfigMapper.deleteById(dbId);
                // 删除环境关联的 项目域名信息
                domainMapper.deleteById(id);
            }
            // 删除环境信息
            envConfigMapper.deleteById(id);
        }
        return Result.success();
    }


    public Result<?> userBind(Integer userId, Integer envId) {
        envConfigMapper.userEnvBind(userId,envId);
        return Result.success();
    }

    @Override
    public Result<?> userProDomain(Integer envId, Integer proId) {
        String domain = domainMapper.getDomainByEnvId(envId,proId);
        return Result.success("操作成功",domain);
    }

    @Override
    public Result<?> dbOptions(Integer envId) {
        EnvConfig envConfigs = envConfigMapper.selectById(envId);
        Set<Integer> dbIds = envConfigs.getDbId();

        List<DbConfig> dbConfigList = null ;
        if (dbIds.size()>0){
            LambdaQueryWrapper<DbConfig> dbConfigLambdaQueryWrapper = new LambdaQueryWrapper<>();
            dbConfigLambdaQueryWrapper.in(DbConfig::getId,dbIds);
            dbConfigList = dbConfigMapper.selectList(dbConfigLambdaQueryWrapper);
        }
        return Result.success(dbConfigList);
    }

    public Result<?> updateDb(DbConfig dbConfig){
        if (dbConfig.getAccount()==null|| "".equals(dbConfig.getAccount())){
            return Result.error("");
        }
        if (dbConfig.getPwd()==null|| "".equals(dbConfig.getPwd())){
            return Result.error("密码不能为空");
        }
        if (dbConfig.getJdbcUrl()==null|| "".equals(dbConfig.getJdbcUrl())){
            return Result.error("JDBC不能为空");
        }
        if (dbConfig.getType()==null){
            return Result.error("数据库类型不能为空");
        }
        if (dbConfig.getId() != null){
            dbConfigMapper.updateById(dbConfig);
        }else {
            dbConfigMapper.insert(dbConfig);
        }
        return Result.success(dbConfig.getId());
    }

    public Result<List<EnvConfig>> envListsOp() {
        LambdaQueryWrapper<EnvConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EnvConfig::getDelFlag,0);
        List<EnvConfig> envConfigs = envConfigMapper.selectList(queryWrapper);
        return Result.success(envConfigs);
    }

}
