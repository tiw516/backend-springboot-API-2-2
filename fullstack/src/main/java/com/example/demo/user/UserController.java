package com.example.demo.user;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
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
/*    @PostMapping("/login")//login
    public boolean checkUser(@RequestParam("email") String email, @RequestParam("password") String password){
        List<User> userlist = userRepository.findAll();
        List<User> result1 = userlist.stream().filter(users->email.equals(users.getEmail())).collect(Collectors.toList());
        User result = result1.stream().filter(users->password.equals(users.getPassword())).findFirst().orElse(null);
        return result!=null;
    }
*/

    @PostMapping("/login")//login
    public User checkUser(@RequestBody Login login){
        String email = login.getEmail();
        String password = login.getPassword();
        List<User> userlist = userRepository.findAll();
        List<User> result1 = userlist.stream().filter(users->email.equals(users.getEmail())).collect(Collectors.toList());
        User result = result1.stream().filter(users->password.equals(users.getPassword())).findFirst().orElse(null);
        if (result!=null){
            return result;
        }else{
            return null;
        }
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

