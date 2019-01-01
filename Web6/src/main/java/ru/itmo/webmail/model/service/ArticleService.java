package ru.itmo.webmail.model.service;

import com.google.common.hash.Hashing;
import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.ArticleRepository;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.ArticleRepositoryImpl;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class ArticleService {

    private ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateArticle(Article article) throws ValidationException {
        if (article.getTitle() == null || article.getTitle().isEmpty()) {
            throw new ValidationException("Title is required");
        }
        if (article.getTitle().trim().length() < 3) {
            throw new ValidationException("Title should contain at least 3 not whitespace characters");
        }
        if (article.getText() == null || article.getText().isEmpty()) {
            throw new ValidationException("Text is required");
        }
        if (article.getText().trim().length() < 5) {
            throw new ValidationException("Text should contain at least 5 not whitespace characters");
        }
    }

    public void addArticle(Article article) {
        articleRepository.save(article);
    }


    public List<Article> findAll() {
        return articleRepository.findAll();
    }
    public List<Article> findByUserId(long userId) {
        return articleRepository.findByUserId(userId);
    }

    public void update(long articleId, long type) {
        articleRepository.update(articleId, type);
    }

    public Article find(long articleId) {
        return articleRepository.find(articleId);
    }
}
