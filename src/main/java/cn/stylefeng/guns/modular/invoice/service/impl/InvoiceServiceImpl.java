package cn.stylefeng.guns.modular.invoice.service.impl;

import cn.stylefeng.guns.modular.system.model.Invoice;
import cn.stylefeng.guns.modular.system.dao.InvoiceMapper;
import cn.stylefeng.guns.modular.invoice.service.IInvoiceService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-10
 */
@Service
public class InvoiceServiceImpl extends ServiceImpl<InvoiceMapper, Invoice> implements IInvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;
    @Override
    public List<Map<String, Object>> selectLists(Page<Invoice> page, String phone, String invoiceId, String idCardNum, Integer state) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("phone", phone);
        entityWrapper.like("invoice_id", invoiceId);
        entityWrapper.like("id_card_num", idCardNum);
        if(state != null) {
            entityWrapper.eq("state", state);
        }
        return invoiceMapper.selectMapsPage(page, entityWrapper);
    }
}
