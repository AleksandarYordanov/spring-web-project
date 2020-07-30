package project.spring.project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.spring.project.user.model.UserEntity;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findOneByEmail(String email);
}
