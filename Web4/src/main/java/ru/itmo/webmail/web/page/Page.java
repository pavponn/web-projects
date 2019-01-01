package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.service.NewsService;
import ru.itmo.webmail.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {
    private UserService userService = new UserService();
    private NewsService newsService = new NewsService();

    void before(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession(true).getAttribute("user");
        if (user != null) {
            view.put("userLogin", user.getLogin());
        }
        view.put("newsList", newsService.findAll());
        view.put("userCount", userService.findCount());
    }

    void after(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession(true).getAttribute("user");
        if (user != null) {
            view.put("userLogin", user.getLogin());
        }
        view.put("newsList", newsService.findAll());
        view.put("userCount", userService.findCount());
    }
}
