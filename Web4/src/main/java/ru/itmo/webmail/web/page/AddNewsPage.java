package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.service.NewsService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class AddNewsPage extends Page {
    private NewsService newsService = new NewsService();

    private void addNews(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String newsText = request.getParameter("textArea");
        try {
            newsService.validateNews(newsText);
        } catch (ValidationException e) {
            view.put("error", e.getMessage());
            return;
        }
        User user = (User) request.getSession(true).getAttribute("user");
        if (user == null) {
            throw new RedirectException("index", "notLoggedIn");
        }

        News news = new News(user.getId(), newsText);
        newsService.addNews(news);
        throw new RedirectException("/index", "newsAdded");
    }

    private void action(Map<String, Object> view) {
        //no actions
    }

}
