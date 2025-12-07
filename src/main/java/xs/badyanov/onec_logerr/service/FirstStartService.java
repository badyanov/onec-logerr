package xs.badyanov.onec_logerr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xs.badyanov.onec_logerr.entity.AppUser;
import xs.badyanov.onec_logerr.entity.UserRoles;
import xs.badyanov.onec_logerr.repository.UserRepository;

@Service
public class FirstStartService {

    private static final Logger logger = LoggerFactory.getLogger(FirstStartService.class);
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "password";

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public FirstStartService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public boolean isFirstStart() {
        logger.debug("Проверка наличия пользователей в базе данных...");
        return userRepository.count() == 0;
    }

    @Transactional
    public void generateData() {
        logger.info("Создание пользователя по-умолчанию при первом запуске приложения...");

        try {
            AppUser firstAdminUser = new AppUser("admin", UserRoles.ROLE_ADMINISTRATOR);
            firstAdminUser.setPassword(userService.encryptPassword(DEFAULT_PASSWORD));
            userRepository.saveAndFlush(firstAdminUser);

            logger.info("Создан пользователь: {}; Пароль: {}. Рекомендуется сменить пароль после входа в систему!",
                    DEFAULT_USERNAME, DEFAULT_PASSWORD);

        } catch (Exception ex) {
            logger.error("Ошибка при попытке создать пользователя по-умолчанию при первом запуске приложения", ex);
            throw ex;
        }
    }

}
