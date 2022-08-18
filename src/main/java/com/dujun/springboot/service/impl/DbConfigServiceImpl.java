package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.entity.DbConfig;
import com.dujun.springboot.mapper.DbConfigMapper;
import com.dujun.springboot.service.DbConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 自动化数据库配置表 服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-08-18
 */
@Service
public class DbConfigServiceImpl extends ServiceImpl<DbConfigMapper, DbConfig> implements DbConfigService {

}
