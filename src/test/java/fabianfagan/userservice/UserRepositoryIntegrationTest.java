package fabianfagan.userservice;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import fabianfagan.userservice.model.User;
import fabianfagan.userservice.model.UserRepository;


/**
 * Test cases for the UserRepository database.
 * @author Fabian Fagan
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
   
    /**
     * Test a user can be saved and retrieved from the repository.
     */
    @Test
    public void testUserSaveAndRetrieve() {

    User user = new User("user@gmail.com", "password", "the", "user");
    entityManager.persist(user);
    entityManager.flush();

    User found = userRepository.findById(user.getEmail()).get();

    assertEquals(found.getEmail(), user.getEmail()); 
}

}