package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.NewsRepository;
import ru.itmo.webmail.model.repository.impl.NewsRepositoryImpl;

import java.util.List;

public class NewsService {
    private NewsRepository newsRepository = new NewsRepositoryImpl();

    public void validateNews(String text) throws ValidationException {
        if (text.trim().length() == 0) {
            throw new ValidationException("You can't create empty post");
        }
    }

    public void addNews(News news) {

        newsRepository.save(news);
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }
}
