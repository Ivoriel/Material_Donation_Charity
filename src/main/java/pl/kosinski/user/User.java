package pl.kosinski.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String firstName;
    private String lastName;
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public void saveEmailAndPassword(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void saveNames(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
