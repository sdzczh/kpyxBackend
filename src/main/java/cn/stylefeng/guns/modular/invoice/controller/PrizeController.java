package cn.stylefeng.guns.modular.invoice.controller;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.modular.system.model.Invoice;
import cn.stylefeng.guns.modular.system.warpper.InvoiceWarpper;
import cn.stylefeng.guns.modular.system.warpper.PrizeWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Prize;
import cn.stylefeng.guns.modular.invoice.service.IPrizeService;

import java.util.List;
import java.util.Map;

/**
 * 中奖记录控制器
 *
 * @author fengshuonan
 * @Date 2019-04-10 10:41:33
 */
@Controller
@RequestMapping("/prize")
public class PrizeController extends BaseController {

    private String PREFIX = "/invoice/prize/";

    @Autowired
    private IPrizeService prizeService;

    /**
     * 跳转到中奖记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "prize.html";
    }

    /**
     * 跳转到添加中奖记录
     */
    @RequestMapping("/prize_add")
    public String prizeAdd() {
        return PREFIX + "prize_add.html";
    }

    /**
     * 跳转到修改中奖记录
     */
    @RequestMapping("/prize_update/{prizeId}")
    public String prizeUpdate(@PathVariable Integer prizeId, Model model) {
        Prize prize = prizeService.selectById(prizeId);
        model.addAttribute("item",prize);
        LogObjectHolder.me().set(prize);
        return PREFIX + "prize_edit.html";
    }

    /**
     * 获取中奖记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String phone, String invoiceId, String idCardNum, Integer type) {
        Page<Prize> page = new PageFactory<Prize>().defaultPage();
        List<Map<String, Object>> list = prizeService.selectLists(page, phone, invoiceId, idCardNum, type);
        page.setRecords(new PrizeWarpper(list).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 新增中奖记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Prize prize) {
        prizeService.insert(prize);
        return SUCCESS_TIP;
    }

    /**
     * 删除中奖记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer prizeId) {
        prizeService.deleteById(prizeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改中奖记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Prize prize) {
        prizeService.updateById(prize);
        return SUCCESS_TIP;
    }

    /**
     * 中奖记录详情
     */
    @RequestMapping(value = "/detail/{prizeId}")
    @ResponseBody
    public Object detail(@PathVariable("prizeId") Integer prizeId) {
        return prizeService.selectById(prizeId);
    }
}
