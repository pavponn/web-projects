package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Talk;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.service.TalkService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class TalksPage extends Page {
    private TalkService talkService = new TalkService();

    TalkService getTalkService() {
        return talkService;
    }

    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        if (getUser() == null) {
            throw new RedirectException("/index");
        }
    }

    private void addTalk(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String talkText = request.getParameter("textArea");
        Talk talk = new Talk();
        talk.setText(talkText);
        String targetLogin = request.getParameter("targetLogin");
        try {
            talkService.validateTalk(talk, targetLogin);
        } catch (ValidationException e) {
            view.put("textArea", talkText);
            view.put("targetLogin", targetLogin);
            view.put("error", e.getMessage());
            before(request, view);
            action(request, view);
            return;
        }

        talk.setTargetUserId(getUserService().findByLogin(request.getParameter("targetLogin")).getId());
        talk.setSourceUserId(getUser().getId());
        getTalkService().addTalk(talk);
        throw new RedirectException("/index", "talkAdded");
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
//        view.put("talks", getTalkService().findAll());
        view.put("talksTo", getTalkService().findAllOutcoming(getUser().getId()));
        view.put("talksFrom", getTalkService().findAllIncoming(getUser().getId()));
    }
}
