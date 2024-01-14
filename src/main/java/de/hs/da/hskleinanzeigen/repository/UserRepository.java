package de.hs.da.hskleinanzeigen.repository;

import de.hs.da.hskleinanzeigen.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByEmail(String email);
}