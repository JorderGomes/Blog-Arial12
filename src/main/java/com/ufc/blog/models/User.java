package com.ufc.blog.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    
    private String name;
    private String password;
    private String email;
    private String bio;
    private float rate;

}
