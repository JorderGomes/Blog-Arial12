package com.ufc.blog.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    
    private String name;
    private String password;
    private String email;
    private String bio;
    private float rate;

    
    
    public String toStringCsv() {
        return name +","+ password +","+ email +","+ bio +","+ rate + "\n";
    }
}
