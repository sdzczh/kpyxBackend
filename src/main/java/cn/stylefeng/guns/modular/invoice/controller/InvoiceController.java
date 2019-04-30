package cn.stylefeng.guns.modular.invoice.controller;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.modular.system.warpper.InvoiceWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Invoice;
import cn.stylefeng.guns.modular.invoice.service.IInvoiceService;

import java.util.List;
import java.util.Map;

/**
 * 发票记录控制器
 *
 * @author fengshuonan
 * @Date 2019-04-10 09:58:59
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController extends BaseController {

    private String PREFIX = "/invoice/invoice/";

    @Autowired
    private IInvoiceService invoiceService;

    /**
     * 跳转到发票记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "invoice.html";
    }

    /**
     * 跳转到添加发票记录
     */
    @RequestMapping("/invoice_add")
    public String invoiceAdd() {
        return PREFIX + "invoice_add.html";
    }

    /**
     * 跳转到修改发票记录
     */
    @RequestMapping("/invoice_update/{invoiceId}")
    public String invoiceUpdate(@PathVariable Integer invoiceId, Model model) {
        Invoice invoice = invoiceService.selectById(invoiceId);
        model.addAttribute("item",invoice);
        LogObjectHolder.me().set(invoice);
        return PREFIX + "invoice_edit.html";
    }

    /**
     * 获取发票记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String phone, String invoiceId, String idCardNum, Integer state, String createDate, String createTime) {
        Page<Invoice> page = new PageFactory<Invoice>().defaultPage();
        List<Map<String, Object>> list = invoiceService.selectLists(page, phone, invoiceId, idCardNum, state, createDate, createTime);
        page.setRecords(new InvoiceWarpper(list).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 新增发票记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Invoice invoice) {
        invoiceService.insert(invoice);
        return SUCCESS_TIP;
    }

    /**
     * 删除发票记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String ids) {
        String[] ss = ids.split(",");
        for (String s : ss) {
            invoiceService.deleteById(Integer.valueOf(s));
        }
        return SUCCESS_TIP;
    }

    /**
     * 修改发票记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Invoice invoice) {
        invoiceService.updateById(invoice);
        return SUCCESS_TIP;
    }

    /**
     * 发票记录详情
     */
    @RequestMapping(value = "/detail/{invoiceId}")
    @ResponseBody
    public Object detail(@PathVariable("invoiceId") Integer invoiceId) {
        return invoiceService.selectById(invoiceId);
    }

    /**
     * 手动抽 奖
     */
    @PostMapping(value = "/draw")
    @ResponseBody
    public String draw(Integer amount) {
        return invoiceService.draw(amount);
    }
    /**
     * 结束本期抽奖
     */
    @PostMapping(value = "/end")
    @ResponseBody
    public String end() {
        return invoiceService.end();
    }
}
