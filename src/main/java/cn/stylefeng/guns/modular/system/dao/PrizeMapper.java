package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.Prize;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-10
 */
public interface PrizeMapper extends BaseMapper<Prize> {

    List<Map<String, Object>> selectLists(@Param("page") Page<Prize> page, @Param("phone") String phone, @Param("invoiceId") String invoiceId, @Param("idCardNum") String idCardNum, @Param("type") Integer type);
}
