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
    public void checkIfUserIsSavedAndRetrievedWhenCalled() {
        // Tests both save and read methods
        // given Category not present in db
        var userViewAdapter = new UserViewAdapter(userRepository);
        var user = generateUser();
        // when User is saved to db
        userViewAdapter.saveUser(user);
        user.setId(1L);
        // then User should be retrievable when called by id
        assertEquals(user, userViewAdapter.findUser(1));
    }

    @Test
    public void checkIfListOfUsersPresentInDbCanBeRetrieved() {
        // given Users are present in db
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
        // when call to find all Users is executed
        // then all Users should be retrieved
        assertEquals(userList, userViewAdapter.findAllUsers());
    }

    @Test
    public void checkIfUserPresentInDbIsDeleted() {
        // given User is present in db
        var userViewAdapter = new UserViewAdapter(userRepository);
        var user = generateUser();
        userViewAdapter.saveUser(user);
        user.setId(1L);
        assertEquals(user, userViewAdapter.findUser(1));
        // when User is deleted from db
        userViewAdapter.deleteUser(1);
        // then User should not be retrievable
        assertThrows(JpaObjectRetrievalFailureException.class, () -> userViewAdapter.findUser(1));
    }

    @Test
    public void checkIfUserIsRetrievedWhenCalledByEmail() {
        // given User is present in db
        var userViewAdapter = new UserViewAdapter(userRepository);
        var user = generateUser();
        userViewAdapter.saveUser(user);
        user.setId(1L);
        user.setPassword(userViewAdapter.findUser(1).getPassword());
        // when call to find User by email is executed
        // then Institution of specific email should be retrieved
        assertEquals(user, userViewAdapter.findUserByEmail(user.getEmail()));
    }

    @Test
    public void checkIfEmailIsIdentifiedAsExistingInDb() {
        // given email is registered in db
        var userViewAdapter = new UserViewAdapter(userRepository);
        var user = generateUser();
        userViewAdapter.saveUser(user);
        user.setId(1L);
        // when call to find if email exists in db
        // then method should return true
        assertTrue(userViewAdapter.emailExistsInDb("test@email.com"));
    }

    @Test
    public void checkIfEmailIsIdentifiedAsNotExistingInDb() {
        // given email is not registered in db
        var userViewAdapter = new UserViewAdapter(userRepository);
        var user = generateUser();
        userViewAdapter.saveUser(user);
        user.setId(1L);
        // when call to find if email exists in db
        // then method should return false
        assertFalse(userViewAdapter.emailExistsInDb("test2@email.com"));
    }

    @Test
    public void checkUserPasswordWhenCalled() {
        //given User is present in db
        var userViewAdapter = new UserViewAdapter(userRepository);
        var user = generateUser();
        userViewAdapter.saveUser(user);
        user.setId(1L);
        var savedUser = userViewAdapter.findUser(1);
        // when call to check specific User's password is executed
        // then true or false should be returned
        assertEquals(true, userViewAdapter.verifyPassword(user.getPassword(), savedUser));
    }

    private UserDto generateUser() {
        var user = new UserDto();
        user.setEmail("test@email.com");
        user.setPassword("test_pswd");
        user.setUserType(UserType.DONOR);
        return user;
    }
}