package com.example.stconnectapp.Model;

import java.util.ArrayList;

public class User {

    String email, password, passwordConfirmation, name;
    ArrayList<Skill> skill;
    ArrayList<Education> education;
    ArrayList<Experience> experience;

    public User(){
    }

    public User(String email, String password, String passwordConfirmation) {
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public User(String name,ArrayList<Skill> skill,ArrayList<Education> education,ArrayList<Experience> experience){
      this.name = name;
      this.skill = skill;
      this.education = education;
      this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Skill> getSkills() {
        return skill;
    }

    public void setSkills(ArrayList<Skill> skill) {
        this.skill = skill;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
