package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.Prize;
import cn.stylefeng.guns.modular.system.model.Selection;
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
 * @since 2019-04-17
 */
public interface SelectionMapper extends BaseMapper<Selection> {

    List<Map<String, Object>> selectLists(@Param("page") Page<Selection> page, @Param("phone") String phone, @Param("number") Integer number, @Param("invoiceId") String invoiceId, @Param("idCardNum") String idCardNum);
}
