package org.ssak3.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssak3.api.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
