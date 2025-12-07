package xs.badyanov.onec_logerr.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import xs.badyanov.onec_logerr.entity.UserRoles;

import java.time.Instant;
import java.util.Objects;

public class UserDto {

    private Long id;
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String username;
    private String password;
    @Email(message = "Адрес e-mail имеет некорректный формат")
    private String email;
    private String telegram;
    private UserRoles role;
    private Instant createdAt;
    private Instant updatedAt;

    // Конструкторы
    public UserDto() {
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    // Equals & HashCode

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof UserDto userDto)) return false;

        return Objects.equals(id, userDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    
}
