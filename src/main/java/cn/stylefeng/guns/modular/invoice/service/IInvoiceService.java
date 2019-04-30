package cn.stylefeng.guns.modular.invoice.service;

import cn.stylefeng.guns.modular.system.model.Invoice;
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
public interface IInvoiceService extends IService<Invoice> {

    List<Map<String, Object>> selectLists(Page<Invoice> page, String phone, String invoiceId, String idCardNum, Integer state, String createDate, String createTime);

    String draw(Integer amount);

    String end();
}
