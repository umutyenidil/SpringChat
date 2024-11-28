package com.umutyenidil.SpringChat.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void saveUser(User user) {
        user.setStatus(UserStatus.ONLINE);
        repository.save(user);

    }

    public void disconnectUser(User user) {
        var storedUser = repository.findById(user.getNickname())
                .orElse(null);

        if (storedUser != null) {
            storedUser.setStatus(UserStatus.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(UserStatus.ONLINE);
    }
}
