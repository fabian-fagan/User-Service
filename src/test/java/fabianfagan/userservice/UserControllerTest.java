package fabianfagan.userservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import fabianfagan.userservice.model.User;

/**
 * Test cases for the UserController. Tests CRUD operations and invalid requests. 
 * @author Fabian Fagan
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
    
	@Test
	public void testRetrieveUserInfoGoodRequest() throws Exception {       
        //Attempt to retrieve (good request)
        this.mockMvc.perform(get("/user/hello@world.com")).andExpect(status().isOk());
				
	}

    @Test
	public void testRetrieveUserInfoBadRequest() throws Exception {       
        //Attempt to retrieve (bad request)
		this.mockMvc.perform(get("/user/doesNotExist@gmail.com")).andDo(print()).andExpect(status().is5xxServerError());
				
	}

    @Test
	public void testPostNewValidUser() throws Exception {                 
        //Attempt to post (valid user)
        User user = new User("some.one@email.com", "s0m3s3cr3tp455w0rd", "Some", "One");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        this.mockMvc.perform(post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andDo(print())
            .andExpect(status().is2xxSuccessful());				
	}

    @Test
	public void testPostNewInvalidUser() throws Exception {                 
        //Attempt to post (invalid user)
        this.mockMvc.perform(post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("not a user"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
				
	}

    @Test
	public void testPutValidUser() throws Exception {                 
        //Attempt to update (valid update)
        User user = new User("some.one@email.com", "aNewPassword", "Updated", "Person");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        this.mockMvc.perform(put("/user/some.one@email.com")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andDo(print())
            .andExpect(status().is2xxSuccessful());
				
	}

    @Test
	public void testPutInvalidUser() throws Exception {                 
        //Attempt to update (invalid update)
        this.mockMvc.perform(put("/user/some.one@email.com")
            .contentType(MediaType.APPLICATION_JSON)
            .content("not a user"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
				
	}

    @Test
	public void testDeleteValidUser() throws Exception {                 
        //Attempt to delete (valid)
        this.mockMvc.perform(delete("/user/some.one@email.com")).andDo(print()).andExpect(status().is2xxSuccessful());				
	}

    @Test
	public void testDeleteInvalidUser() throws Exception {                 
        //Attempt to delete (invalid)
        this.mockMvc.perform(delete("/user/doesntExist@notreal.com")).andDo(print()).andExpect(status().is5xxServerError());
				
	}
   
}