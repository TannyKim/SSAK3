package org.ssak3.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssak3.api.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
