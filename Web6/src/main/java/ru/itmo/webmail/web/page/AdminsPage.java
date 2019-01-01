package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class AdminsPage extends Page{
    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        if (getUser() == null || !getUser().isAdmin()) {
            throw new RedirectException("/index");
        }
    }

    @Override
    public void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);

        if (getUser() == null || !getUser().isAdmin()) {
            throw new RedirectException("/index");
        }
    }

    private Map<String, Object> update(HttpServletRequest request, Map<String, Object> view) {
        long id = Long.parseLong(request.getParameter("id"));
        if (!getUser().isAdmin()){
            throw new RedirectException("/index");
        }
        long type = Long.parseLong(request.getParameter("type"));
        getUserService().update(id, type);
        if (id == getUser().getId()) {
          view.put("success", false);
        } else {
            view.put("success", true);
        }
        return view;
    }
    private List<User> find(HttpServletRequest request, Map<String, Object> view) {
        return getUserService().findAll();
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
}
