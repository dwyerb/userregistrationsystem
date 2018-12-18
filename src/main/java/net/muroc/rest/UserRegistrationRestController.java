package net.muroc.rest;

/**
 * Created by Brendan on 12/15/2018.
 */

import net.muroc.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.muroc.repository.UserJPARepository;
import net.muroc.dto.UserDTO;
import net.muroc.Exception.CustomErrorType;
import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController
{
    public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);
    private UserJPARepository userJPARepository;

    @Autowired
    public void setUserJPARepository(UserJPARepository userJPARepository)
    {
        this.userJPARepository = userJPARepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> listAllUsers()
    {
        List<UserDTO> users = userJPARepository.findAll();
        if (users.isEmpty())
        {
            return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
    }
    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody final UserDTO user)
    {
        if (userJPARepository.findByName(user.getName()) != null)
        {
            return new ResponseEntity<UserDTO>(new CustomErrorType("Unable to create new user. A User with name "
                    + user.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        userJPARepository.save(user);
        return new ResponseEntity<UserDTO>(user,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Long id) {
        UserDTO user = userJPARepository.findById(id).orElse(null);
        if (user== null)
        {
            return new ResponseEntity<UserDTO>(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final Long id, @RequestBody UserDTO user)
    {
        //fetch user based on id and set it to currentUser object of type UserDTO
        UserDTO currentUser = userJPARepository.findById(id).orElse(null);
        if (user== null)
        {
            return new ResponseEntity<UserDTO>(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        //update currentUser object data with user object data
        currentUser.setName(user.getName());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());

        //save currentUser object
        userJPARepository.saveAndFlush(currentUser);

        return new ResponseEntity<UserDTO>(currentUser, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final Long id) {
        UserDTO user = userJPARepository.findById(id).orElse(null);
        if (user== null)
        {
            return new ResponseEntity<UserDTO>(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        userJPARepository.deleteById(id);
        return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
    }

}
