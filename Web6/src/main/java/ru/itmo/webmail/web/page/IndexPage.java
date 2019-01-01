package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class IndexPage extends Page {
    class ArticleWithLogin {
        Article article;
        String login;
        ArticleWithLogin (Article article) {
            this.article = article;
            login = article.getUserLogin();
        }
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
    private List<ArticleWithLogin> findArticles(HttpServletRequest request, Map<String, Object> view) {
        List articles = getArticleService().findAll();
        List<ArticleWithLogin> articlesWithLogins = new ArrayList<>();
        for (Object article : articles) {
            articlesWithLogins.add(new ArticleWithLogin((Article) article));
        }
        return articlesWithLogins;
    }
    private void registrationDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have been registered");
    }
}
