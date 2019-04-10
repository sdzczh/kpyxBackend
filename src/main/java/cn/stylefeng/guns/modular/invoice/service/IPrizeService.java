package cn.stylefeng.guns.modular.invoice.service;

import cn.stylefeng.guns.modular.system.model.Prize;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-10
 */
public interface IPrizeService extends IService<Prize> {

    List<Map<String, Object>> selectLists(Page<Prize> page, String phone, String invoiceId, String idCardNum, Integer type);
}
