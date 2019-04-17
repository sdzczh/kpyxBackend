package cn.stylefeng.guns.modular.invoice.service;

import cn.stylefeng.guns.modular.system.model.Selection;
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
 * @since 2019-04-17
 */
public interface ISelectionService extends IService<Selection> {

    List<Map<String, Object>> selectLists(Page<Selection> page, String phone, Integer number, String invoiceId, String idCardNum);
}
