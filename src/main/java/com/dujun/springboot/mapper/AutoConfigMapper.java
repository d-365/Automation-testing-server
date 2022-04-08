package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.AutoConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.openqa.selenium.By;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-04-06
 */
public interface AutoConfigMapper extends BaseMapper<AutoConfig> {

    /**
     * @param name 配置名称
     * @return 配置值
     */
    @Select("SELECT value FROM auto_config WHERE name = #{name}")
    String ValueByNAme(@Param("name") String name);

}
