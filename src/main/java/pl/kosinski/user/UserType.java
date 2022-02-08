package pl.kosinski.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserType {

    DONOR("Donor"),
    ADMIN("Admin");

    private final String label;

    public static UserType fromLabel(String label) {
        for (UserType uT : UserType.values()) {
            if (uT.label.equalsIgnoreCase(label)) {
                return uT;
            }
        }
        return null;
    }
}
