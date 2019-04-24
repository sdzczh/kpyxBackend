package cn.stylefeng.guns.modular.syparam.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Sysparam;
import cn.stylefeng.guns.modular.syparam.service.ISysparamService;

/**
 * 系统参数配置控制器
 *
 * @author fengshuonan
 * @Date 2019-04-24 16:28:34
 */
@Controller
@RequestMapping("/sysparam")
public class SysparamController extends BaseController {

    private String PREFIX = "/syparam/sysparam/";

    @Autowired
    private ISysparamService sysparamService;

    /**
     * 跳转到系统参数配置首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sysparam.html";
    }

    /**
     * 跳转到添加系统参数配置
     */
    @RequestMapping("/sysparam_add")
    public String sysparamAdd() {
        return PREFIX + "sysparam_add.html";
    }

    /**
     * 跳转到修改系统参数配置
     */
    @RequestMapping("/sysparam_update/{sysparamId}")
    public String sysparamUpdate(@PathVariable Integer sysparamId, Model model) {
        Sysparam sysparam = sysparamService.selectById(sysparamId);
        model.addAttribute("item",sysparam);
        LogObjectHolder.me().set(sysparam);
        return PREFIX + "sysparam_edit.html";
    }

    /**
     * 获取系统参数配置列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return sysparamService.selectList(null);
    }

    /**
     * 新增系统参数配置
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Sysparam sysparam) {
        sysparamService.insert(sysparam);
        return SUCCESS_TIP;
    }

    /**
     * 删除系统参数配置
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer sysparamId) {
        sysparamService.deleteById(sysparamId);
        return SUCCESS_TIP;
    }

    /**
     * 修改系统参数配置
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Sysparam sysparam) {
        sysparamService.updateById(sysparam);
        return SUCCESS_TIP;
    }

    /**
     * 系统参数配置详情
     */
    @RequestMapping(value = "/detail/{sysparamId}")
    @ResponseBody
    public Object detail(@PathVariable("sysparamId") Integer sysparamId) {
        return sysparamService.selectById(sysparamId);
    }
}
