package com.example.demo.user;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    private String name;
    private String age;
    private String telephone;
    private String email;
    private String job;
    private String password;

    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
/*
    public static final String CSV_SEPARATOR = ",";
    public static void writeToCSV(List<User> users)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("userlist.csv"), "UTF-8"));
            for (User user : users)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(user.getUid() <=0 ? "" : user.getUid());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(user.getName() == null? "" : user.getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(user.getEmail() == null ? "" : user.getEmail());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(user.getTelephone() == null ? "" : user.getTelephone());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(user.getJob() == null ? "" : user.getJob());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(user.getAge() == null ? "" : user.getTelephone());
                oneLine.append(CSV_SEPARATOR);
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
*/
    public User(){
    }
}
