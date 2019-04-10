package cn.stylefeng.guns.modular.invoice.service.impl;

import cn.stylefeng.guns.modular.system.model.Prize;
import cn.stylefeng.guns.modular.system.dao.PrizeMapper;
import cn.stylefeng.guns.modular.invoice.service.IPrizeService;
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
public class PrizeServiceImpl extends ServiceImpl<PrizeMapper, Prize> implements IPrizeService {

    @Autowired
    private PrizeMapper prizeMapper;
    @Override
    public List<Map<String, Object>> selectLists(Page<Prize> page, String phone, String invoiceId, String idCardNum, Integer type) {
        return prizeMapper.selectLists(page, phone, invoiceId, idCardNum, type);
    }
}
