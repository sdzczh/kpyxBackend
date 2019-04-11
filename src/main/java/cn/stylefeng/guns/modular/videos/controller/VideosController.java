package cn.stylefeng.guns.modular.videos.controller;

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
import cn.stylefeng.guns.modular.system.model.Videos;
import cn.stylefeng.guns.modular.videos.service.IVideosService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 视频管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-10 11:52:15
 */
@Controller
@RequestMapping("/videos")
public class VideosController extends BaseController {

    private String PREFIX = "/videos/videos/";

    @Autowired
    private IVideosService videosService;

    /**
     * 跳转到视频管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "videos.html";
    }

    /**
     * 跳转到添加视频管理
     */
    @RequestMapping("/videos_add")
    public String videosAdd() {
        return PREFIX + "videos_add.html";
    }

    /**
     * 跳转到修改视频管理
     */
    @RequestMapping("/videos_update/{videosId}")
    public String videosUpdate(@PathVariable Integer videosId, Model model) {
        Videos videos = videosService.selectById(videosId);
        model.addAttribute("item",videos);
        LogObjectHolder.me().set(videos);
        return PREFIX + "videos_edit.html";
    }

    /**
     * 获取视频管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String title) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("title", title);
        return videosService.selectList(entityWrapper);
    }

    /**
     * 新增视频管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Videos videos, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "i:/img/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            videos.setVideoUrl(filePath + fileName);
            videosService.insert(videos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除视频管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer videosId) {
        videosService.deleteById(videosId);
        return SUCCESS_TIP;
    }

    /**
     * 修改视频管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Videos videos) {
        videosService.updateById(videos);
        return SUCCESS_TIP;
    }

    /**
     * 视频管理详情
     */
    @RequestMapping(value = "/detail/{videosId}")
    @ResponseBody
    public Object detail(@PathVariable("videosId") Integer videosId) {
        return videosService.selectById(videosId);
    }
}
