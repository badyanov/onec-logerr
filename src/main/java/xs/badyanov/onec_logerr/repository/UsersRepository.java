package xs.badyanov.onec_logerr.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xs.badyanov.onec_logerr.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsernameIgnoreCase(String username);

    @Query("""
            SELECT u FROM AppUser u
            WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%'))
                OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
                OR LOWER(u.telegram) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    List<AppUser> search(String keyword, Sort sort);

}
