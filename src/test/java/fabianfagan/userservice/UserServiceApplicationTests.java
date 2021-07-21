package fabianfagan.userservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fabianfagan.userservice.controller.UserController;

@SpringBootTest
class UserServiceApplicationTests {
     
	@Autowired
	private UserController controller;

	@Test
	void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}

}
