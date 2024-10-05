package com.sumit.user.service.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@Data
public class User {

    @Id
    @Column(name = "UID")
    private String uid;

    private String name;

    private String email;

    private String about;

    @Transient
    private List<Rating> ratings =new ArrayList<>();


}
