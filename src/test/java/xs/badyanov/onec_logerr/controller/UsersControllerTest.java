package xs.badyanov.onec_logerr.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import xs.badyanov.onec_logerr.dto.UserDto;
import xs.badyanov.onec_logerr.entity.UserRoles;
import xs.badyanov.onec_logerr.service.UsersService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsersController.class)
class UsersControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UsersService usersService;

    @Test
    @WithMockUser
    void test_getUsers_whenGetUsers_thenReturnUsersView() throws Exception {
        // Подготовка тестовых данных
        UserDto user1 = new UserDto();
        user1.setId(1L);
        user1.setUsername("Test User 1");
        user1.setRole(UserRoles.ROLE_USER);

        UserDto user2 = new UserDto();
        user2.setId(2L);
        user2.setUsername("Test User 2");
        user2.setRole(UserRoles.ROLE_READONLY);

        List<UserDto> expectedUsers = List.of(user1, user2);

        Mockito.when(usersService.findAll()).thenReturn(expectedUsers);

        // Вызов тестируемого метода и проверки
        mvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", expectedUsers));

    }

    @Test
    @WithMockUser
    void test_getUsers_whenGetUsersWithKeyword_thenReturnUsersView() throws Exception {
        // Preparing test data
        String keyword = "test";

        UserDto user1 = new UserDto();
        user1.setId(1L);
        user1.setUsername("Test User 1");
        user1.setRole(UserRoles.ROLE_USER);

        List<UserDto> expectedUsers = List.of(user1);

        Mockito.when(usersService.search(keyword)).thenReturn(expectedUsers);

        // Call under test and assertions
        mvc.perform(MockMvcRequestBuilders.get("/users").param("keyword", keyword))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("keyword"))
                .andExpect(model().attribute("users", hasSize(1)))
                .andExpect(model().attribute("users", expectedUsers))
                .andExpect(model().attribute("keyword", keyword));

    }

}