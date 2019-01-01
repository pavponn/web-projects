package ru.itmo.webmail.model.service;

import com.google.common.hash.Hashing;
import ru.itmo.webmail.model.domain.EmailConfirmation;
import ru.itmo.webmail.model.domain.Event;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.EmailConfirmationRepository;
import ru.itmo.webmail.model.repository.EventRepository;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.EmailConfirmationRepositoryImpl;
import ru.itmo.webmail.model.repository.impl.EventRepositoryImpl;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

@SuppressWarnings("UnstableApiUsage")
public class EventService {

    private EventRepository eventRepository = new EventRepositoryImpl();
    public void markEvent(User user, Event action) {
        eventRepository.markEvent(user, action);
    }



}
