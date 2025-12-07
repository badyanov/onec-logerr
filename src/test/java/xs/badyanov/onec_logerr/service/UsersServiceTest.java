package xs.badyanov.onec_logerr.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import xs.badyanov.onec_logerr.converters.UserConverter;
import xs.badyanov.onec_logerr.dto.UserDto;
import xs.badyanov.onec_logerr.entity.AppUser;
import xs.badyanov.onec_logerr.entity.UserRoles;
import xs.badyanov.onec_logerr.repository.UsersRepository;

import java.util.List;

@SpringBootTest(classes = {UsersService.class})
class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @MockitoBean
    private UsersRepository userRepository;
    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Test
    void test_findAll_whenFound_thenReturnedListOfUsers() {
        // Подготовка тестовых данных
        AppUser user1 = new AppUser("Test User 1", UserRoles.ROLE_ADMINISTRATOR);
        user1.setId(1L);
        user1.setPassword("Test");
        UserDto userDto1 = UserConverter.toDto(user1);

        AppUser user2 = new AppUser("Test User 2", UserRoles.ROLE_USER);
        user2.setId(2L);
        user2.setPassword("Test");
        UserDto userDto2 = UserConverter.toDto(user1);

        Mockito.when(userRepository.findAll(Mockito.any(Sort.class)))
                .thenReturn(List.of(user1, user2));

        // Вызов тестируемого метода
        List<UserDto> users = usersService.findAll();

        // Проверки
        Assertions.assertEquals(2, users.size());
        Assertions.assertTrue(users.contains(userDto1));
        Assertions.assertTrue(users.contains(userDto2));

        Mockito.verify(userRepository, Mockito.times(1))
                .findAll(Mockito.any(Sort.class));
    }

    @Test
    void test_search_whenFound_thenReturnedListOfUsers() {
        // Подготовка тестовых данных
        String keyword = "test";

        AppUser user1 = new AppUser("Test User 1", UserRoles.ROLE_ADMINISTRATOR);
        user1.setId(1L);
        user1.setPassword("Test");
        UserDto userDto1 = UserConverter.toDto(user1);

        AppUser user2 = new AppUser("Test User 2", UserRoles.ROLE_USER);
        user2.setId(2L);
        user2.setPassword("Test");
        UserDto userDto2 = UserConverter.toDto(user1);

        Mockito.when(userRepository.search(Mockito.eq(keyword), Mockito.any(Sort.class)))
                .thenReturn(List.of(user1, user2));

        // Вызов тестируемого метода
        List<UserDto> users = usersService.search(keyword);

        // Проверки
        Assertions.assertEquals(2, users.size());
        Assertions.assertTrue(users.contains(userDto1));
        Assertions.assertTrue(users.contains(userDto2));

        Mockito.verify(userRepository, Mockito.times(1))
                .search(Mockito.eq(keyword), Mockito.any(Sort.class));
    }

}