package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.MobilePhone;
import com.dujun.springboot.mapper.MobilePhoneMapper;
import com.dujun.springboot.service.MobilePhoneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * APP-执行机 服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-06-23
 */
@Service
public class MobilePhoneServiceImpl extends ServiceImpl<MobilePhoneMapper, MobilePhone> implements MobilePhoneService {

    @Resource
    private MobilePhoneMapper mobilePhoneMapper;

    @Override
    public Result<IPage<MobilePhone>> mobilePhoneList(Integer current,Integer size,Integer status) {
        IPage<MobilePhone> page = new Page<>(current, size);
        LambdaQueryWrapper<MobilePhone> phoneLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (status!=null){
            phoneLambdaQueryWrapper.eq(MobilePhone::getStatus,status);
        }
        phoneLambdaQueryWrapper.eq(MobilePhone::getDelFlag,0);
        IPage<MobilePhone> pageElements = mobilePhoneMapper.selectPage(page,phoneLambdaQueryWrapper);
        return Result.success(pageElements);
    }

}
