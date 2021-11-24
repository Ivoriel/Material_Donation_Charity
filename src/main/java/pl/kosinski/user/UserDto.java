package pl.kosinski.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
