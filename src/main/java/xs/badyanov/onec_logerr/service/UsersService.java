package xs.badyanov.onec_logerr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xs.badyanov.onec_logerr.converters.UserConverter;
import xs.badyanov.onec_logerr.dto.UserDto;
import xs.badyanov.onec_logerr.entity.AppUser;
import xs.badyanov.onec_logerr.repository.UserRepository;

import java.util.List;

@Service
public class UsersService {

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UsersService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        logger.debug("Поиск всех пользователей в базе данных, сортировка по полю ID");
        List<AppUser> users = userRepository.findAll(Sort.by("id").ascending());
        logger.debug("Найдено {} пользователей", users.size());
        return users.stream().map(UserConverter::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<UserDto> search(String keyword) {
        logger.debug("Поиск пользователей по отбору: '{}'", keyword);
        List<AppUser> users = userRepository.search(keyword, Sort.by("id").ascending());
        logger.debug("Найдено {} пользователей по отбору '{}'", users.size(), keyword);
        return users.stream().map(UserConverter::toDto).toList();
    }

}
