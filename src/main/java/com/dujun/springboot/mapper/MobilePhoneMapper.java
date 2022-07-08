package com.dujun.springboot.mapper;

import com.dujun.springboot.VO.option.AppConfigOp;
import com.dujun.springboot.entity.MobilePhone;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * APP-执行机 Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-06-23
 */
public interface MobilePhoneMapper extends BaseMapper<MobilePhone> {

    @Select("SELECT id,name,`status` FROM tester_plat.mobile_phone WHERE del_flag = 0;")
    List<AppConfigOp> PHONE_OP();

    @Select("SELECT id,`name`,plat_form,plat_version,ip,`status` FROM mobile_phone WHERE `status` = 0 AND del_flag = 0;")
    List<MobilePhone> onlinePhone();
}
