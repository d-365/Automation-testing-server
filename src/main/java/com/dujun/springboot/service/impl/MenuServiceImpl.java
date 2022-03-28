package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.Menu;
import com.dujun.springboot.mapper.MenuMapper;
import com.dujun.springboot.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-03-14
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public Result<List<Menu>> menuList() {
        List<Menu> roleList = menuMapper.selectList(new QueryWrapper<Menu>().orderByAsc("number"));

        List<Menu> parent = roleList.stream().filter(menu -> menu.getParentId() == null).collect(Collectors.toList());
        List<Menu> finalMenu = new ArrayList<>();

        for (Menu menu : roleList) {
            if (menu.getParentId() == null){
                parent.add(menu);
                finalMenu.add(menu);
            }else {
                for (Menu parentMenu : parent) {
                    if (Objects.equals(parentMenu.getId(), menu.getParentId())){
                        parentMenu.getChildren().add(menu);
                        parent.add(menu);
                        break;
                    }
                }
            }
        }
        return Result.success(finalMenu);
    }

}
