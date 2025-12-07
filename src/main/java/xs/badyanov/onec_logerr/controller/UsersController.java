package xs.badyanov.onec_logerr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xs.badyanov.onec_logerr.dto.UserDto;
import xs.badyanov.onec_logerr.exceptions.AppServiceException;
import xs.badyanov.onec_logerr.service.UsersService;

import java.util.List;

@Controller
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public String getUsers(@RequestParam(value = "keyword", required = false) String keyword,
                           RedirectAttributes redirectAttributes,
                           Model model) {

        logger.debug("Обработка запроса GET /users с отбором: {}", keyword);

        try {
            List<UserDto> users;
            if (keyword == null || keyword.isBlank()) {
                users = usersService.findAll();
                logger.debug("Отбор не задан, получены все {} пользователи", users.size());
            } else {
                users = usersService.search(keyword);
                model.addAttribute("keyword", keyword);
                logger.debug("Получено {} пользователей по отбору '{}'", users.size(), keyword);
            }
            model.addAttribute("users", users);

            logger.debug("Успешно выполнен запрос GET /users");
            return "admin/users";

        } catch (AppServiceException serviceException) {
            logger.error("При выполнении запроса GET /users получены некорректные данные", serviceException);
            String errorMessage = "Ошибка при получении списка пользователей: %s.".formatted(serviceException.getMessage());
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/users";
        } catch (Exception unhandledException) {
            logger.error("При выполнении запроса GET /users произошла непредвиденная ошибка", unhandledException);
            throw unhandledException;
        }
    }

}
