package org.example.srboard_server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    private String pass;
    private String userid;
    private String email;
    // @Column(name="subject")
    private String title;
    private String content;
    private int readcount;
    @CreationTimestamp
    private Date writedate;
    private String image;
    private String savefilename;
}
