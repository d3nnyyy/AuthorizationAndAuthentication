package ua.tsebulia.AuthorizationAndAuthentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.tsebulia.AuthorizationAndAuthentication.entity.UserInfo;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);
}
