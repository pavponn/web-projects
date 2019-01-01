package ru.itmo.webmail.web.page;

import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class LogoutPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession(true);
        session.removeAttribute("user");
        throw new RedirectException("/index", "logoutDone");
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
