package com.dujun.springboot.service.impl;

import com.dujun.springboot.entity.UserToken;
import com.dujun.springboot.mapper.UserTokenMapper;
import com.dujun.springboot.service.UserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2021-12-10
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements UserTokenService {

}
