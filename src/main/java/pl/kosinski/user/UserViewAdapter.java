package pl.kosinski.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserViewAdapter implements UserService{

    @Override
    public UserDto saveUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto findUser(long id) {
        return null;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(long id) {
        //nein
    }
}
