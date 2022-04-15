package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.Action;
import com.dujun.springboot.mapper.ActionMapper;
import com.dujun.springboot.service.ActionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.testng.collections.Lists;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        try {
            actionMapper.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("存在关联数据,无法删除");
        }
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

    @Override
    public Result<?> actionTree(Action action) {
        Integer type = action.getType();
        QueryWrapper<Action> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",type).eq("del_flag",0);
        List<Action> actionList = actionMapper.selectList(queryWrapper);
        List<Action> parentAction = actionList.stream().filter(item->item.getParentId() ==0).collect(Collectors.toList());
        for (Action action_parent : parentAction) {
            action_parent.setChildren(actionTreeDeep(action_parent.getId(),actionList));
        }
        return Result.success(parentAction);
    }
    // actionTree 递归查询子节点
    private List<Action> actionTreeDeep(Integer id,List<Action> actions){
        List<Action> childrenActions = Lists.newArrayList();
        for (Action action : actions) {
            if (Objects.equals(action.getParentId(), id)){
                childrenActions.add(action);
            }
        }

        for (Action childrenAction : childrenActions) {
            childrenAction.setChildren(actionTreeDeep(childrenAction.getId(),actions));
        }
        return childrenActions;
    }

}
