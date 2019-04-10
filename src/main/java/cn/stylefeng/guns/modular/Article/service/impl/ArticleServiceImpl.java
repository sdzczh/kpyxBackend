package cn.stylefeng.guns.modular.Article.service.impl;

import cn.stylefeng.guns.modular.system.model.Article;
import cn.stylefeng.guns.modular.system.dao.ArticleMapper;
import cn.stylefeng.guns.modular.Article.service.IArticleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-09
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public List<Map<String, Object>> selectLists(String title, Integer type) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("title", title);
        if(type != null) {
            entityWrapper.eq("type", type);
        }
        return articleMapper.selectMaps(entityWrapper);
    }
}
