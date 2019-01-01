package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MyArticlesPage extends Page {
    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        if (getUser() == null) {
            throw new RedirectException("/index");
        }
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        view.put("myArticles", getArticleService().findByUserId(getUser().getId()));

    }
    private void update(HttpServletRequest request, Map<String, Object> view) {
        long id = Long.parseLong(request.getParameter("id"));
        long userId = Long.parseLong(request.getParameter("userId"));
        if (userId != getUser().getId()) {
            return;
        }
        long type = Long.parseLong(request.getParameter("type"));
        getArticleService().update(id, type);
    }

    private void registrationDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have been registered");
    }
}