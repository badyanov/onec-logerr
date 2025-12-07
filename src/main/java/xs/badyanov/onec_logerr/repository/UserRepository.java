package xs.badyanov.onec_logerr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xs.badyanov.onec_logerr.entity.AppUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsernameIgnoreCase(String username);
    
}
