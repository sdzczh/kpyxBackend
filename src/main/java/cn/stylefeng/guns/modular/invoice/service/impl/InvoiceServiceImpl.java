package cn.stylefeng.guns.modular.invoice.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.SysparamKeys;
import cn.stylefeng.guns.core.util.DrawUtils;
import cn.stylefeng.guns.modular.invoice.service.ISelectionService;
import cn.stylefeng.guns.modular.syparam.service.ISysparamService;
import cn.stylefeng.guns.modular.system.model.Invoice;
import cn.stylefeng.guns.modular.system.dao.InvoiceMapper;
import cn.stylefeng.guns.modular.invoice.service.IInvoiceService;
import cn.stylefeng.guns.modular.system.model.Selection;
import cn.stylefeng.guns.modular.system.model.Sysparam;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class InvoiceServiceImpl extends ServiceImpl<InvoiceMapper, Invoice> implements IInvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private ISysparamService sysparamService;
    @Autowired
    private ISelectionService selectionService;
    @Override
    public List<Map<String, Object>> selectLists(Page<Invoice> page, String phone, String invoiceId, String idCardNum, Integer state, String createDate, String createTime) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("phone", phone);
        entityWrapper.like("invoice_id", invoiceId);
        entityWrapper.like("id_card_num", idCardNum);
        if(!StrUtil.isBlank(createDate)) {
            entityWrapper.ge("create_date", createDate);
        }
        if(!StrUtil.isBlank(createTime)) {
            entityWrapper.ge("create_time", createTime);
        }
        entityWrapper.orderBy("id", false);
        if(state != null) {
            entityWrapper.eq("state", state);
        }
        return invoiceMapper.selectMapsPage(page, entityWrapper);
    }

    @Override
    public String draw(Integer amount) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("state", 1);
        List<Invoice> list = invoiceMapper.selectList(entityWrapper);
        Map<String, Object> map = new HashMap<>();
        if(list.size() == 0){
            map.put("code", 20001);
            map.put("msg", "没有符合条件的抽奖人员");
            return JSONObject.toJSONString(map);
        }
        List<Integer> selectList = new LinkedList<>();
        for(Invoice invoice : list){
            selectList.add(invoice.getId());
        }
        //打乱list中元素顺序
        Collections.shuffle(list);
        //中奖名单id
        Set<Integer> set = DrawUtils.draw(amount, selectList);
        Selection selection;
        String key = SysparamKeys.DRAW_NUMBER;
        Sysparam sysparam = sysparamService.queryByKey(key);
        int number = Integer.valueOf(sysparam.getKeyValue());
        for(Integer index : set){
            //插入选中表
            selection = new Selection();
            selection.setInvoiceId(index);
            selection.setNumber(number);
            selection.setState(Const.STATE_ON);
            selectionService.insertSelective(selection);

            //修改状态
            Invoice invoice = invoiceMapper.selectById(index);
            invoice.setState(0);
            invoiceMapper.updateById(invoice);
        }
        map.put("code", 10000);
        map.put("msg", "抽奖完成，请到入围名单中查看！");
        return JSONObject.toJSONString(map);
    }

    @Override
    public String end() {
        invoiceMapper.changeState();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 10000);
        map.put("msg", "本期抽奖已结束！");
        return JSONObject.toJSONString(map);
    }
}
