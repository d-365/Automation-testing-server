package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.UserEnv;
import com.dujun.springboot.mapper.UserEnvMapper;
import com.dujun.springboot.service.UserEnvService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-01-07
 */
@Service
public class UserEnvServiceImpl extends ServiceImpl<UserEnvMapper, UserEnv> implements UserEnvService {

    @Resource
    private UserEnvMapper userEnvMapper;

    //用户环境关联
    public Result<?> user_env(UserEnv userEnv){

        UserEnv userEnv1 = userEnvMapper.selectOne(new QueryWrapper<UserEnv>().eq("user_id",userEnv.getUserId()));
        if (userEnv1==null){
            userEnvMapper.insert(userEnv);
        }else {
            userEnvMapper.updateByUserId(userEnv1.getUserId(),userEnv1.getEnvId());
        }
        return Result.success();
    }

}
