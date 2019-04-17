package cn.stylefeng.guns.modular.link.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.FriendshipLink;
import cn.stylefeng.guns.modular.link.service.IFriendshipLinkService;

/**
 * 友情链接管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-17 14:27:37
 */
@Controller
@RequestMapping("/friendshipLink")
public class FriendshipLinkController extends BaseController {

    private String PREFIX = "/link/friendshipLink/";

    @Autowired
    private IFriendshipLinkService friendshipLinkService;

    /**
     * 跳转到友情链接管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "friendshipLink.html";
    }

    /**
     * 跳转到添加友情链接管理
     */
    @RequestMapping("/friendshipLink_add")
    public String friendshipLinkAdd() {
        return PREFIX + "friendshipLink_add.html";
    }

    /**
     * 跳转到修改友情链接管理
     */
    @RequestMapping("/friendshipLink_update/{friendshipLinkId}")
    public String friendshipLinkUpdate(@PathVariable Integer friendshipLinkId, Model model) {
        FriendshipLink friendshipLink = friendshipLinkService.selectById(friendshipLinkId);
        model.addAttribute("item",friendshipLink);
        LogObjectHolder.me().set(friendshipLink);
        return PREFIX + "friendshipLink_edit.html";
    }

    /**
     * 获取友情链接管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return friendshipLinkService.selectList(null);
    }

    /**
     * 新增友情链接管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(FriendshipLink friendshipLink) {
        friendshipLinkService.insert(friendshipLink);
        return SUCCESS_TIP;
    }

    /**
     * 删除友情链接管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer friendshipLinkId) {
        friendshipLinkService.deleteById(friendshipLinkId);
        return SUCCESS_TIP;
    }

    /**
     * 修改友情链接管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(FriendshipLink friendshipLink) {
        friendshipLinkService.updateById(friendshipLink);
        return SUCCESS_TIP;
    }

    /**
     * 友情链接管理详情
     */
    @RequestMapping(value = "/detail/{friendshipLinkId}")
    @ResponseBody
    public Object detail(@PathVariable("friendshipLinkId") Integer friendshipLinkId) {
        return friendshipLinkService.selectById(friendshipLinkId);
    }
}
