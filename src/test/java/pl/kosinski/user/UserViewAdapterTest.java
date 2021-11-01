package pl.kosinski.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserViewAdapterTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveUser() {
        //To do
    }

    @Test
    public void findUser() {
        //To do
    }

    @Test
    public void findAllUsers() {
        //To do
    }

    @Test
    public void deleteUser() {
        //To do
    }
}