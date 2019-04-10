package cn.stylefeng.guns.modular.Article.service;

import cn.stylefeng.guns.modular.system.model.Article;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-09
 */
public interface IArticleService extends IService<Article> {

    List<Map<String, Object>> selectLists(String title, Integer type);
}
