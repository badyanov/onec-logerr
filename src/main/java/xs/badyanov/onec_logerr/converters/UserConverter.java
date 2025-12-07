package xs.badyanov.onec_logerr.converters;

import xs.badyanov.onec_logerr.dto.UserDto;
import xs.badyanov.onec_logerr.entity.AppUser;

public class UserConverter {

    private UserConverter() {
    }

    public static UserDto toDto(AppUser user) {
        if (user == null) {
            return null;
        }

        UserDto result = new UserDto();
        result.setId(user.getId());
        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        result.setRole(user.getRole());
        result.setEmail(user.getEmail());
        result.setTelegram(user.getTelegram());

        return result;
    }

}
