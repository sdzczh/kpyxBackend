package cn.stylefeng.guns.modular.syparam.service.impl;

import cn.stylefeng.guns.modular.system.model.Sysparam;
import cn.stylefeng.guns.modular.system.dao.SysparamMapper;
import cn.stylefeng.guns.modular.syparam.service.ISysparamService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-24
 */
@Service
public class SysparamServiceImpl extends ServiceImpl<SysparamMapper, Sysparam> implements ISysparamService {

    @Autowired
    private SysparamMapper sysparamMapper;
    @Override
    public Sysparam queryByKey(String key) {
        Map<Object, Object> map = new HashMap<>();
        map.put("keyName", key);
        List<Sysparam> sysparamList = this.sysparamMapper.selectAll(map);
        if (sysparamList.size() != 0) {
            return sysparamList.get(0);
        }
        return null;
    }
}
