package com.dujun.springboot.service.impl;

import com.dujun.springboot.entity.WebPage;
import com.dujun.springboot.mapper.WebPageMapper;
import com.dujun.springboot.service.WebPageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-03-28
 */
@Service
public class WebPageServiceImpl extends ServiceImpl<WebPageMapper, WebPage> implements WebPageService {

}
