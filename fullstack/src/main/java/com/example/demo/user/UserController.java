package com.example.demo.user;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
@RequestMapping("/api/users")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping//list all the info out
    public List<User> getList() {
        return userRepository.findAll();
    }
    @PostMapping("/{email}/{password}")
    public boolean checkUser(@PathVariable("email") String email, @PathVariable("password") String password){
        List<User> userlist = userRepository.findAll();
        List<User> result1 = userlist.stream().filter(users->email.equals(users.getEmail())).collect(Collectors.toList());
        User result = result1.stream().filter(users->password.equals(users.getPassword())).findFirst().orElse(null);
        return result!=null;
    }

    @PostMapping()//modify or create
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping(value = "/{uid}")//delete
    public List<User> delUser(@PathVariable("uid") Integer uid
    ) {
        userRepository.deleteById(uid);
        return userRepository.findAll();
    }

}

