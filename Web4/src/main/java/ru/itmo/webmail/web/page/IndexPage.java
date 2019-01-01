package ru.itmo.webmail.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class IndexPage extends Page {
    private void action() {
        // No operations.
    }

    private void registrationDone(Map<String, Object> view) {
        view.put("message", "You have been registered");
    }

    private void enterDone(Map<String, Object> view) {
        view.put("message", "You have been logged in!");
    }

    private void logoutDone(Map<String, Object> view) {
        view.put("message", "You have been logged out!");
    }

    private void notLoggedIn(Map<String, Object> view) {
        view.put("message", "You should be authorized to perform this action.");
    }

    private void newsAdded(Map<String, Object> view) {
        view.put("message", "Your news were successfully added!");
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
