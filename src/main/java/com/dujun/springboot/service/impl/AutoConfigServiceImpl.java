package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.AutoConfig;
import com.dujun.springboot.mapper.AutoConfigMapper;
import com.dujun.springboot.service.AutoConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-04-06
 */
@Service
public class AutoConfigServiceImpl extends ServiceImpl<AutoConfigMapper, AutoConfig> implements AutoConfigService {

    @Resource
    private AutoConfigMapper configMapper;

    /**
     * selenium定位方式
     * @return
     */
    @Override
    public Result<?> locationWay() {
        String seleniumLocation = configMapper.ValueByNAme("selenium_location");
        String[] strings = seleniumLocation.split(",");
        List<HashMap<String,String>> locationWays = new ArrayList<>();
        for (String locationWay : strings) {
            locationWays.add(new HashMap<String, String>(){{ put("value",locationWay); }});
        }
        return Result.success(locationWays);
    }

    /**
     * selenium元素类型
     * @return
     */
    @Override
    public Result<?> elementType() {
        String seleniumLocation = configMapper.ValueByNAme("elementType");
        String[] strings = seleniumLocation.split(",");
        List<HashMap<String,String>> locationWays = new ArrayList<>();
        for (String locationWay : strings) {
            locationWays.add(new HashMap<String, String>(){{ put("value",locationWay.trim()); }});
        }
        return Result.success(locationWays);
    }

}
