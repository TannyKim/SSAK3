package org.ssak3.api.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssak3.api.user.entity.User;
import org.ssak3.api.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
    }
}
