package pl.kosinski.user;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto findUser(long id);

    List<UserDto> findAllUsers();

    void deleteUser(long id);

    Boolean findUserByEmail(String userEmail);

    Boolean verifyPassword(String password, UserDto userDto);
}
