package fabianfagan.userservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fabianfagan.userservice.error.Error;
import fabianfagan.userservice.model.User;
import fabianfagan.userservice.model.UserRepository;


/**
 * The controller for the user CRUD operations.
 * @author Fabian Fagan
 */
@RestController
public class UserController {

  private final UserRepository repository;

  UserController(UserRepository repository) {
    this.repository = repository;
  }

  /*
   *  Create new user.
   */ 

  @PostMapping("/user")
  ResponseEntity<String> newUser(@RequestBody User newUser) {
    // Check validity
    if (newUser.getEmail() == null || newUser.getPassword() == null) {
      throw new Error(); 
    } 
    else {
      // Add user and return 201 success message
      repository.save(newUser);
      return ResponseEntity.status(HttpStatus.CREATED).body("The user was created successfully.");
    }
  }

  /*
   *  Update user. 
   */ 

  @PutMapping("/user/{email}")
  ResponseEntity<String> updateUser(@RequestBody User newUser, @PathVariable String email) {
    // throw error if not found
    repository.findById(email).orElseThrow(() -> new Error());

    // check validity of new details
    if (newUser.getEmail() == null || newUser.getPassword() == null) {
      throw new Error(); 
    }
     
    // update user and return 204 success message
    if (email != newUser.getEmail()) {
      //If email is changing, create a new entry
      repository.deleteById(email);
      repository.save(newUser);
    }
    else {
      repository.findById(email).map(user -> {
      user.setPassword(newUser.getPassword());
      user.setFirstName(newUser.getFirstName());
      user.setLastName(newUser.getLastName());
      return repository.save(user);
      });
    }     
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The user was updated successfully.");
  }

  /*
   *  Retrieve by email. 
   */ 

  @GetMapping("/user/{email}")
  ResponseEntity<User> retrieveUser(@PathVariable String email) {
    // find user, throw error if not found
    User user = repository.findById(email).orElseThrow(() -> new Error());

    // return 200 status and User
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  /*
   *  Delete user.
   */ 

  @DeleteMapping("/user/{email}")
  ResponseEntity<String> deleteUser(@PathVariable String email) {
    // throw error if not found
    repository.findById(email).orElseThrow(() -> new Error());

    // delete user and return 204 success message
    repository.deleteById(email);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The user was deleted successfully.");
  }
}
