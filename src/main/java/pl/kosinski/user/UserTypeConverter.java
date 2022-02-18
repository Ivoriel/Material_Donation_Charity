package pl.kosinski.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserTypeConverter implements Converter<String, UserType> {

    public UserType convert (String source) {
        for (UserType uT : UserType.values()) {
            if (uT.getLabel().equalsIgnoreCase(source)) {
                return uT;
            }
        }
        return null;
    }
}
