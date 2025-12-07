package xs.badyanov.onec_logerr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xs.badyanov.onec_logerr.entity.AppUser;
import xs.badyanov.onec_logerr.repository.UsersRepository;

@Service
public class AppUserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public AppUserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser foundUser = usersRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by username: %s".formatted(username)));
        return new AppUserDetails(foundUser);
    }

}
