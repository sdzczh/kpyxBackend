package cn.stylefeng.guns.modular.report.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Report;
import cn.stylefeng.guns.modular.report.service.IReportService;

/**
 * 涉税举报控制器
 *
 * @author fengshuonan
 * @Date 2019-04-10 11:39:09
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {

    private String PREFIX = "/report/report/";

    @Autowired
    private IReportService reportService;

    /**
     * 跳转到涉税举报首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "report.html";
    }

    /**
     * 跳转到添加涉税举报
     */
    @RequestMapping("/report_add")
    public String reportAdd() {
        return PREFIX + "report_add.html";
    }

    /**
     * 跳转到修改涉税举报
     */
    @RequestMapping("/report_update/{reportId}")
    public String reportUpdate(@PathVariable Integer reportId, Model model) {
        Report report = reportService.selectById(reportId);
        model.addAttribute("item",report);
        LogObjectHolder.me().set(report);
        return PREFIX + "report_edit.html";
    }

    /**
     * 获取涉税举报列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String name, String phone) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("name", name);
        entityWrapper.like("phone", phone);
        return reportService.selectList(entityWrapper);
    }

    /**
     * 新增涉税举报
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Report report) {
        reportService.insert(report);
        return SUCCESS_TIP;
    }

    /**
     * 删除涉税举报
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer reportId) {
        reportService.deleteById(reportId);
        return SUCCESS_TIP;
    }

    /**
     * 修改涉税举报
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Report report) {
        reportService.updateById(report);
        return SUCCESS_TIP;
    }

    /**
     * 涉税举报详情
     */
    @RequestMapping(value = "/detail/{reportId}")
    @ResponseBody
    public Object detail(@PathVariable("reportId") Integer reportId) {
        return reportService.selectById(reportId);
    }
}
