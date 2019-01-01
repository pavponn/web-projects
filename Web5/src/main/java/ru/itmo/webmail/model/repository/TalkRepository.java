package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.Talk;

import java.util.List;

public interface TalkRepository {
//    Talk find(long userId);


    List<Talk> findAll();
    void save(Talk talk);
    List<Talk> findAllIncoming(long userId);
    List<Talk> findAllOutcoming(long userId);
}
