package ru.itmo.webmail.model.service;

import com.google.common.hash.Hashing;
import ru.itmo.webmail.model.domain.EmailConfirmation;
import ru.itmo.webmail.model.domain.Event;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.EmailConfirmationRepository;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.EmailConfirmationRepositoryImpl;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

@SuppressWarnings("UnstableApiUsage")
public class UserService {
    private static final String USER_PASSWORD_SALT = "dc3475f2b301851b";

    private UserRepository userRepository = new UserRepositoryImpl();
    private EmailConfirmationRepository emailConfirmationRepository = new EmailConfirmationRepositoryImpl();
    public void validateRegistration(User user, String password) throws ValidationException {
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new ValidationException("Login is required");
        }
        if (!user.getLogin().matches("[a-z]+")) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (user.getLogin().length() > 8) {
            throw new ValidationException("Login can't be longer than 8");
        }

        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login is already in use");
        }
        if (!user.getEmail().contains("@")) {
            throw new ValidationException("Not valid email");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ValidationException("Email is already in use");
        }

        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4");
        }
        if (password.length() > 32) {
            throw new ValidationException("Password can't be longer than 32");
        }
    }

    public void register(User user, String password) {
        String passwordSha = getPasswordSha(password);
//        byte[] array = new byte[10];
////        new Random().nextBytes(array);
////        String generatedSecret = new String(array, Charset.forName("UTF-8"));
////        generatedSecret = generatedSecret + user.getLogin();
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        buffer.append(user.getLogin());
        String generatedSecret = buffer.toString();
        userRepository.save(user, passwordSha);
        EmailConfirmation emailConfirmation = new EmailConfirmation();
        emailConfirmation.setSecret(generatedSecret);
        emailConfirmation.setUserId(user.getId());
        emailConfirmationRepository.save(emailConfirmation);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void validateEnter(String login, String password) throws ValidationException {
        if (login == null || login.isEmpty()) {
            throw new ValidationException("Login is required");
        }
        if (!login.matches("[a-z]+") && !login.contains("@")) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (login.length() > 25) {
            throw new ValidationException("Login/Email can't be longer than 16");
        }

        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4");
        }
        if (password.length() > 32) {
            throw new ValidationException("Password can't be longer than 32");
        }


        if (userRepository.findByLoginOrEmailAndPasswordSha(login, getPasswordSha(password)) == null) {
            throw new ValidationException("Invalid login or password");
        }
        if (!userRepository.findByLoginOrEmailAndPasswordSha(login, getPasswordSha(password)).getConfirmed()) {
            throw new ValidationException("This account hasn't not been confirmed");
        }

    }

    private String getPasswordSha(String password) {
        return Hashing.sha256().hashString(USER_PASSWORD_SALT + password,
                StandardCharsets.UTF_8).toString();
    }

    public User authorize(String login, String password) {
        return userRepository.findByLoginOrEmailAndPasswordSha(login, getPasswordSha(password));
    }


    public User find(long userId) {
        return userRepository.find(userId);
    }

    public boolean confirm(String secret) {
        if (emailConfirmationRepository.findBySecret(secret) != null) {
            return userRepository.updateConfirmation(emailConfirmationRepository.findBySecret(secret).getUserId());
        }
        return false;
    }
}
