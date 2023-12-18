package org.ssak3.api.ledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssak3.api.ledger.entity.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
