package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.Action;
import com.dujun.springboot.mapper.ActionMapper;
import com.dujun.springboot.service.ActionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-04-06
 */
@Service
public class ActionServiceImpl extends ServiceImpl<ActionMapper, Action> implements ActionService {

    @Resource
    private ActionMapper actionMapper;

    @Override
    public Result<List<Action>> actionList(HashMap<String,String> action) {
        String actionType = action.get("type");
        String id = action.get("parentId");
        QueryWrapper<Action> queryWrapper = new QueryWrapper<>();

        if (!Objects.equals(actionType, "")&& actionType!=null){
            queryWrapper.eq("type",actionType);
        }
        if (!Objects.equals(id, "") && id !=null){
             queryWrapper.eq("parent_id",id);
        }

        List<Action> actions = actionMapper.selectList(queryWrapper);
        return Result.success(actions);
    }

    @Override
    public Result<?> delAction(Integer id) {
        actionMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result<?> updateAction(Action action) {
        if (action.getId() != null){
            actionMapper.updateById(action);
        }else {
            actionMapper.insert(action);
        }

        return Result.success();
    }
}
