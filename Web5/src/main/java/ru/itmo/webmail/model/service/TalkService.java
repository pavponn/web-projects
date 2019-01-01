package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Talk;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.TalkRepository;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.TalkRepositoryImpl;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;

import java.util.List;

public class TalkService {
    private TalkRepository talkRepository = new TalkRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();
    public void validateTalk(Talk talk, String targetLogin) throws ValidationException {
        if (talk.getText().trim().length() == 0) {
            throw new ValidationException("Your message should contain at least one not whitespace character");
        }
        if (userRepository.findByLogin(targetLogin) == null) {
            throw new ValidationException("You can't send message to this user because this account doesn't exist");
        }
    }
    public List<Talk> findAll() {
        return talkRepository.findAll();
    }
    public List<Talk> findAllIncoming(long userId) {
        return talkRepository.findAllIncoming(userId);
    }
    public List<Talk> findAllOutcoming(long userId) {
        return talkRepository.findAllOutcoming(userId);
    }
    public void addTalk(Talk talk) {
        talkRepository.save(talk);

    }
}
