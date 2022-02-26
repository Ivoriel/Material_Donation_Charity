package pl.kosinski.user;

import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserViewAdapter implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto saveUser(UserDto userDto) {
        var user = new User();
        if (userDto.getId() != null) {
            user = getUser(userDto.getId());
            user.saveEmailAndPassword(userDto.getEmail(), hashPassword(userDto.getPassword()));
            if (userDto.getUserType() == null) {
                user.setUserType(UserType.DONOR);
            } else user.setUserType(userDto.getUserType());
        } else {
            user.saveEmailAndPassword(userDto.getEmail(), hashPassword(userDto.getPassword()));
            if (userDto.getUserType() == null) {
                user.setUserType(UserType.DONOR);
            } else user.setUserType(userDto.getUserType());
        }
        user = userRepository.save(user);
        return mapEntityToDto(user);
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
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findUserByEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        return mapEntityToDto(user);
    }

    @Override
    public boolean emailExistsInDb(String userEmail) {
        if (userRepository.findByEmail(userEmail) != null) {
            return true;
        }
        return false;
    }

    public Boolean verifyPassword(String password, UserDto user) {
        if (BCrypt.checkpw(password, user.getPassword()))
            return true;
        else
            return false;
    }

    private UserDto mapEntityToDto(User user) {
        var userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUserType(user.getUserType());
        return userDto;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
