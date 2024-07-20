package org.example.srboard_server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    private String userid;

    private String name;
    private String pwd;
    private String email;
    private String phone;
}
