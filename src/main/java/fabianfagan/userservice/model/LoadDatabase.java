package fabianfagan.userservice.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
  
  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {

    return args -> {
      //Add two users and log their entry. 
      log.info("Saved " + repository.save(new User("some.one@email.com", "s0m3s3cr3tp455w0rd", "Some", "One")));
      log.info("Saved " + repository.save(new User("hello@world.com", "12345", "John", "Johnson")));
    };
  }
}
