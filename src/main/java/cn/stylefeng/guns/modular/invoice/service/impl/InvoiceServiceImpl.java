package cn.stylefeng.guns.modular.invoice.service.impl;

import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.SysparamKeys;
import cn.stylefeng.guns.core.util.DrawUtils;
import cn.stylefeng.guns.modular.invoice.service.ISelectionService;
import cn.stylefeng.guns.modular.syparam.service.ISysparamService;
import cn.stylefeng.guns.modular.system.dao.SysparamMapper;
import cn.stylefeng.guns.modular.system.model.Invoice;
import cn.stylefeng.guns.modular.system.dao.InvoiceMapper;
import cn.stylefeng.guns.modular.invoice.service.IInvoiceService;
import cn.stylefeng.guns.modular.system.model.Selection;
import cn.stylefeng.guns.modular.system.model.Sysparam;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-10
 */
@Slf4j
@Service
public class InvoiceServiceImpl extends ServiceImpl<InvoiceMapper, Invoice> implements IInvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private ISysparamService sysparamService;
    @Autowired
    private ISelectionService selectionService;
    @Override
    public List<Map<String, Object>> selectLists(Page<Invoice> page, String phone, String invoiceId, String idCardNum, Integer state) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("phone", phone);
        entityWrapper.like("invoice_id", invoiceId);
        entityWrapper.like("id_card_num", idCardNum);
        entityWrapper.orderBy("id", false);
        if(state != null) {
            entityWrapper.eq("state", state);
        }
        return invoiceMapper.selectMapsPage(page, entityWrapper);
    }

    @Override
    public void draw(Integer amount) {
        String key = SysparamKeys.DRAW_START_ID;
        Sysparam sysparam = sysparamService.queryByKey(key);
        String startId = sysparam.getKeyValue();
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.gt("id", startId);
        entityWrapper.eq("state", 1);
        List<Invoice> list = invoiceMapper.selectList(entityWrapper);
        List<Integer> selectList = new LinkedList<>();
        for(Invoice invoice : list){
            selectList.add(invoice.getId());
        }
        //打乱list中元素顺序
        Collections.shuffle(list);
        //中奖名单id
        Set<Integer> set = DrawUtils.draw(amount, selectList);
        Map<String, Object> map = new HashMap<>();
        List<Selection> selectionList = selectionService.selectAll(map);
        Selection selection = selectionList.get(0);
        int number;
        if(selection == null){
            number = 1;
        }else{
            number = selection.getNumber() + 1;
        }
        for(Integer index : set){
            //插入选中表
            selection = new Selection();
            selection.setInvoiceId(index);
            selection.setNumber(number);
            selection.setState(Const.STATE_ON);
            selectionService.insertSelective(selection);
        }
    }
}
