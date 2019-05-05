package cn.stylefeng.guns.modular.Banner.controller;

import cn.stylefeng.guns.core.common.constant.SysparamKeys;
import cn.stylefeng.guns.modular.syparam.service.ISysparamService;
import cn.stylefeng.guns.modular.system.model.Sysparam;
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
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Autowired
    private ISysparamService sysparamService;

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
        File targetFile;
        String fileName = img_url.getOriginalFilename();
        if(fileName!=null&&fileName!="") {
            Sysparam sysPath = sysparamService.queryByKey(SysparamKeys.SYSTEM_URL);
            String systemPath;
            if(sysPath != null){
                systemPath = sysPath.getKeyValue();
            }else{
                return "获取系统路径失败";
            }
            String filePath = "/home/installPackage/imgs/";
//            String filePath = "I:/img";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fileAdd = sdf.format(new Date());
            //获取文件夹路径
            File dest = new File(filePath + "/" + fileAdd + "/");
            //如果文件夹不存在则创建
            if (!dest.exists() && !dest.isDirectory()) {
                dest.mkdir();
            }
            targetFile = new File(dest, fileName);
            try {
                img_url.transferTo(targetFile);
                banner.setImgUrl(systemPath + "/file/showImg?imgUrl=" + fileAdd + "/" + fileName);
                bannerService.insert(banner);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return SUCCESS_TIP;
        }
        return "上传失败，请选择文件";
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
