package com.ufc.blog.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    
    private String name;
    private String password;
    private String email;
    private String bio;
    private float rate;
    
    public String toStringCsv() {
        return name +","+ password +","+ email +","+ bio +","+ rate + "\n";
    }
}
