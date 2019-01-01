package ru.itmo.webmail.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ConfirmPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
        String secret = request.getParameter("secret");
        if (secret != null) {
            if (getUserService().confirm(secret)) {
                view.put("message", "User confirmed");
            } else {
                view.put("message", "User can't be confirmed");
            }
        }
    }

    private void registrationDone(HttpServletRequest request, Map<String, Object> view) {
//        view.put("message", "You have been registered");
    }
    private void talkAdded(HttpServletRequest request, Map<String, Object> view) {
//        view.put("message", "You have added talk");
    }
}
