package com.example.demo.user;

import org.springframework.web.bind.annotation.*;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Date;


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

    @RequestMapping(value = "/downloadCSV", method = RequestMethod.GET) //download csv file with all user info
    public void downloadCSV(HttpServletResponse response) throws IOException {

        String csvFileName = "userlist.csv";

        List<User> users = userRepository.findAll();

        response.setContentType("text/csv");

        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] header = {"uid", "name", "age", "telephone", "email", "job", "password", "time"};

        csvWriter.writeHeader(header);

        for (User user : users) {
            csvWriter.write(user, header);
        }

        csvWriter.close();
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
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        user.setTime(timeStamp);
        return userRepository.save(user);
    }

    @DeleteMapping(value = "/{uid}")//delete
    public List<User> delUser(@PathVariable("uid") Integer uid
    ) {
        userRepository.deleteById(uid);
        return userRepository.findAll();
    }

}

