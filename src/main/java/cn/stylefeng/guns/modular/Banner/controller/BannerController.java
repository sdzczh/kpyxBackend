package cn.stylefeng.guns.modular.Banner.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Banner;
import cn.stylefeng.guns.modular.Banner.service.IBannerService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Banner管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-10 09:54:46
 */
@Controller
@RequestMapping("/banner")
public class BannerController extends BaseController {

    private String PREFIX = "/Banner/banner/";

    @Autowired
    private IBannerService bannerService;

    /**
     * 跳转到Banner管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "banner.html";
    }

    /**
     * 跳转到添加Banner管理
     */
    @RequestMapping("/banner_add")
    public String bannerAdd() {
        return PREFIX + "banner_add.html";
    }

    /**
     * 跳转到修改Banner管理
     */
    @RequestMapping("/banner_update/{bannerId}")
    public String bannerUpdate(@PathVariable Integer bannerId, Model model) {
        Banner banner = bannerService.selectById(bannerId);
        model.addAttribute("item",banner);
        LogObjectHolder.me().set(banner);
        return PREFIX + "banner_edit.html";
    }

    /**
     * 获取Banner管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bannerService.selectList(null);
    }

    /**
     * 新增Banner管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Banner banner, @RequestParam("img_url") MultipartFile img_url) {
        if (img_url.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = img_url.getOriginalFilename();
        String filePath = "i:/img/";
        File dest = new File(filePath + fileName);
        try {
            img_url.transferTo(dest);
            banner.setImgUrl(filePath + fileName);
            bannerService.insert(banner);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除Banner管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bannerId) {
        bannerService.deleteById(bannerId);
        return SUCCESS_TIP;
    }

    /**
     * 修改Banner管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Banner banner) {
        bannerService.updateById(banner);
        return SUCCESS_TIP;
    }

    /**
     * Banner管理详情
     */
    @RequestMapping(value = "/detail/{bannerId}")
    @ResponseBody
    public Object detail(@PathVariable("bannerId") Integer bannerId) {
        return bannerService.selectById(bannerId);
    }
}
