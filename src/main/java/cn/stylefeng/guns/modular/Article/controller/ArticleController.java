package cn.stylefeng.guns.modular.Article.controller;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.system.warpper.ArticleWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.velocity.shaded.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Article;
import cn.stylefeng.guns.modular.Article.service.IArticleService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-09 17:52:11
 */
@Slf4j
@Controller
    @RequestMapping("/article")
public class ArticleController extends BaseController {

    private String PREFIX = "/Article/article/";

    @Autowired
    private IArticleService articleService;

    /**
     * 跳转到文章管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "article.html";
    }

    /**
     * 跳转到添加文章管理
     */
    @RequestMapping("/article_add")
    public String articleAdd() {
        return PREFIX + "article_add.html";
    }

    /**
     * 跳转到修改文章管理
     */
    @RequestMapping("/article_update/{articleId}")
    public String articleUpdate(@PathVariable Integer articleId, Model model) {
        Article article = articleService.selectById(articleId);
        model.addAttribute("item",article);
        LogObjectHolder.me().set(article);
        return PREFIX + "article_edit.html";
    }

    /**
     * 获取文章管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String title, Integer type) {
        List<Map<String, Object>> list = articleService.selectLists(title, type);
        return new ArticleWarpper(list).wrap();
    }

    /**
     * 新增文章管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Article article) {
        String content = article.getContent();
        content = content.replaceAll("& ", "&");
        article.setContent(content);
        ShiroUser shiroUser = ShiroKit.getUser();
        String author = shiroUser.getAccount();
        article.setAuthor(author);
        article.setClinkNum(0);
        articleService.insert(article);
        return SUCCESS_TIP;
    }

    /**
     * 删除文章管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer articleId) {
        articleService.deleteById(articleId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Article article) {
        String content = article.getContent();
        content = content.replaceAll("& ", "&");
        article.setContent(content);
        articleService.updateById(article);
        return SUCCESS_TIP;
    }

    /**
     * 文章管理详情
     */
    @RequestMapping(value = "/detail/{articleId}")
    @ResponseBody
    public Object detail(@PathVariable("articleId") Integer articleId) {
        return articleService.selectById(articleId);
    }

    @ResponseBody
    @RequestMapping(value = "upload")
    public String upload(@RequestParam("myFile") MultipartFile cardFile, HttpServletRequest request){

        String path= Class.class.getClass().getResource("/").getPath();
        path= path+"static"+File.separator+"uploadfiles";


        Map<String, String> map = new HashMap<String, String>();
        String jo="";
        if(cardFile != null){
            String oldFileName = cardFile.getOriginalFilename();

            String prefix= FilenameUtils.getExtension(oldFileName);

            if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){
                String fileName = System.currentTimeMillis()+ RandomUtils.nextInt()+"_IDcard.jpg";
                File targetFile = new File(path, fileName);
                // 检测是否存在目录
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }

                try {

                    cardFile.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String url =request.getContextPath()+"/static/uploadfiles/"+fileName;
                map.put("data", url);
                jo = JSONArray.toJSONString(map);
                return jo;
            }else{
                return "2";
            }
        }

        return null;
    }
}
