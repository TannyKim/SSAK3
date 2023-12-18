package org.ssak3.api.user.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.ssak3.api.user.entity.User;
import org.ssak3.api.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
    }

    public User insertUser() {
        String random = RandomStringUtils.random(20, true, true);
        int age = (int) ((Math.random() * 40) + 20);
        long income = (long) ((Math.random() * 400000) + 100000);

        User newUser = new User(random, "국민지", age, income);
        return userRepository.save(newUser);
    }
}
