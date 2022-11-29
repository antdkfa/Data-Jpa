package com.example.datajpa.repository;


import org.springframework.beans.factory.annotation.Value;

public interface UserNameOnly {
    @Value("#{target.userName + ' ' + target.age + ' ' + target.team.name}")
    String getUserName();
}
