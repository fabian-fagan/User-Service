package fabianfagan.userservice.model;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class automatically loads the database with two entities on startup 
 * for testing.
 * @author Fabian Fagan
 */
@Configuration
class LoadDatabase {

  
  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {

    return args -> {
      //Add two users.
      repository.save(new User("some.one@email.com", "s0m3s3cr3tp455w0rd", "Some", "One"));
      repository.save(new User("someone.else@email.com", "12345", "John", "Johnson"));
    };
  }
}
