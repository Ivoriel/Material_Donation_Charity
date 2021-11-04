package pl.kosinski.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserViewAdapterTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveUser() {
        var userViewAdapter = new UserViewAdapter(userRepository);
        var user = generateUser();
        userViewAdapter.saveUser(user);
        user.setId(1L);
        assertEquals(user, userViewAdapter.findUser(1));
    }

    @Test
    public void findAllUsers() {
        var userViewAdapter = new UserViewAdapter(userRepository);
        var userList = new ArrayList<UserDto>();
        var user1 = generateUser();
        userViewAdapter.saveUser(user1);
        user1.setId(1L);
        userList.add(user1);
        var user2 = generateUser();
        userViewAdapter.saveUser(user2);
        user2.setId(2L);
        userList.add(user2);
        assertEquals(userList, userViewAdapter.findAllUsers());
    }

    @Test
    public void deleteUser() {
        var userViewAdapter = new UserViewAdapter(userRepository);
        var user = generateUser();
        userViewAdapter.saveUser(user);
        user.setId(1L);
        assertEquals(user, userViewAdapter.findUser(1));
        userViewAdapter.deleteUser(1);
        assertThrows(JpaObjectRetrievalFailureException.class, () -> userViewAdapter.findUser(1));
    }

    private UserDto generateUser() {
        var user = new UserDto();
        user.setEmail("test@email.com");
        user.setPassword("test_pswd");
        return user;
    }
}