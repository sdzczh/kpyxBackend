package cn.stylefeng.guns.modular.invoice.controller;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.modular.system.model.Prize;
import cn.stylefeng.guns.modular.system.warpper.PrizeWarpper;
import cn.stylefeng.guns.modular.system.warpper.SelecttionWarpper;
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
import cn.stylefeng.guns.modular.system.model.Selection;
import cn.stylefeng.guns.modular.invoice.service.ISelectionService;

import java.util.List;
import java.util.Map;

/**
 * 入围名单控制器
 *
 * @author fengshuonan
 * @Date 2019-04-17 15:29:55
 */
@Controller
@RequestMapping("/selection")
public class SelectionController extends BaseController {

    private String PREFIX = "/invoice/selection/";

    @Autowired
    private ISelectionService selectionService;

    /**
     * 跳转到入围名单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "selection.html";
    }

    /**
     * 跳转到添加入围名单
     */
    @RequestMapping("/selection_add")
    public String selectionAdd() {
        return PREFIX + "selection_add.html";
    }

    /**
     * 跳转到修改入围名单
     */
    @RequestMapping("/selection_update/{selectionId}")
    public String selectionUpdate(@PathVariable Integer selectionId, Model model) {
        Selection selection = selectionService.selectById(selectionId);
        model.addAttribute("item",selection);
        LogObjectHolder.me().set(selection);
        return PREFIX + "selection_edit.html";
    }

    /**
     * 获取入围名单列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String phone, String invoiceId, String idCardNum, Integer number) {
        Page<Selection> page = new PageFactory<Selection>().defaultPage();
        List<Map<String, Object>> list = selectionService.selectLists(page, phone, number, invoiceId, idCardNum);
        page.setRecords(new SelecttionWarpper(list).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 新增入围名单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Selection selection) {
        selectionService.insert(selection);
        return SUCCESS_TIP;
    }

    /**
     * 删除入围名单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer selectionId) {
        selectionService.deleteById(selectionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改入围名单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Selection selection) {
        selectionService.updateById(selection);
        return SUCCESS_TIP;
    }

    /**
     * 入围名单详情
     */
    @RequestMapping(value = "/detail/{selectionId}")
    @ResponseBody
    public Object detail(@PathVariable("selectionId") Integer selectionId) {
        return selectionService.selectById(selectionId);
    }
}
