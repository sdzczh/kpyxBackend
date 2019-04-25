package cn.stylefeng.guns.modular.invoice.service.impl;

import cn.stylefeng.guns.modular.system.model.Selection;
import cn.stylefeng.guns.modular.system.dao.SelectionMapper;
import cn.stylefeng.guns.modular.invoice.service.ISelectionService;
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
 * @since 2019-04-17
 */
@Service
public class SelectionServiceImpl extends ServiceImpl<SelectionMapper, Selection> implements ISelectionService {

    @Autowired
    private SelectionMapper selectionMapper;
    @Override
    public List<Map<String, Object>> selectLists(Page<Selection> page, String phone, Integer number, String invoiceId, String idCardNum) {
        return selectionMapper.selectLists(page, phone, number, invoiceId, idCardNum);
    }

    @Override
    public List<Selection> selectAll(Map<String, Object> map) {
        EntityWrapper entityWrapper = new EntityWrapper();
        return selectionMapper.selectList(entityWrapper);
    }

    @Override
    public void insertSelective(Selection selection) {
        selectionMapper.insertAllColumn(selection);
    }
}
