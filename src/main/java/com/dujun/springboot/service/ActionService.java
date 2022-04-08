package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.Action;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dujun
 * @since 2022-04-06
 */
public interface ActionService extends IService<Action> {

    Result<List<Action>> actionList(HashMap<String,String> action);

    Result<?> delAction(Integer id);

    Result<?> updateAction(Action action);

}
