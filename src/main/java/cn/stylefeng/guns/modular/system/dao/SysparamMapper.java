package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.Sysparam;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-24
 */
public interface SysparamMapper extends BaseMapper<Sysparam> {

    List<Sysparam> selectAll(Map<Object, Object> map);
}
