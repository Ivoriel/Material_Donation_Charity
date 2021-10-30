package pl.kosinski.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserViewAdapter implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto saveUser(UserDto userDto) {
        var user = new User();
        if (userDto.getId() != null) {
            user = getUser(userDto.getId());
            user.saveEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        } else {
            user.saveEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        }
        user = userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    private User getUser(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public UserDto findUser(long id) {
        return mapEntityToDto(getUser(id));
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<UserDto> dtoList = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (var u : userList) {
            dtoList.add(mapEntityToDto(u));
        }
        return dtoList;
    }

    @Override
    public void deleteUser(long id) {
        //nein
    }

    private UserDto mapEntityToDto(User user) {
        var userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
