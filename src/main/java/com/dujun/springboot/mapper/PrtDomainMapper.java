package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.PrtDomain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 项目域名关联表 Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-05-09
 */
public interface PrtDomainMapper extends BaseMapper<PrtDomain> {

    /*通过环境ID和 项目ID 获取对应环境下域名信息*/
    @Select("SELECT domain FROM prt_domain WHERE env_id = #{envId} and prt_id = #{proId};")
    String getDomainByEnvId(@Param("envId") Integer envId, @Param("proId") Integer proId);

}
