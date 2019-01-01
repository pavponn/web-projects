package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.service.UserService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class EnterPage extends Page {
    private UserService userService = new UserService();

    private void enter(HttpServletRequest request, Map<String, Object> view) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            userService.validateLogin(login, password);
        } catch (ValidationException e) {
            view.put("login", login);
            view.put("password", password);
            view.put("error", e.getMessage());
            return;
        }
        User user = userService.findByLogin(login);
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        throw new RedirectException("/index", "enterDone");
    }

    private void action() {
        // No operations.
    }

    @Override
    void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    @Override
    void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }
}
