package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage extends Page {

    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        if (getUser() == null) {
            throw new RedirectException("/index");
        }
    }

    private Map<String, Object>  addArticle(HttpServletRequest request, Map<String, Object> view) {
        Article article = new Article();
        article.setUserId(getUser().getId());
        article.setTitle(request.getParameter("title"));
        article.setText(request.getParameter("articleText"));
        article.setHidden(false);

        try {
            getArticleService().validateArticle(article);
        } catch (ValidationException e) {
            view.put("success", false);
            view.put("error", e.getMessage());
            return view;
        }
        if (getUser() == null) {
            throw new RedirectException("/index");
        }
        getArticleService().addArticle(article);
        view.put("success", true);
        return view;
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
}
