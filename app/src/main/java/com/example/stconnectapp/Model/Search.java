package com.example.stconnectapp.Model;

import java.util.ArrayList;

public class Search {
    String name, email, experience, education;
    ArrayList<Skill> skill;

    public Search(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public ArrayList<Skill> getSkill() {
        return skill;
    }

    public void setSkill(ArrayList<Skill> skill) {
        this.skill = skill;
    }
}

