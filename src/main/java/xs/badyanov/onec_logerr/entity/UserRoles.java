package xs.badyanov.onec_logerr.entity;

public enum UserRoles {

    ROLE_ADMINISTRATOR("Администратор"),
    ROLE_USER("Пользователь"),
    ROLE_READONLY("Только просмотр");

    private final String displayName;

    UserRoles(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
