package com.sumit.user.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@Data
@NoArgsConstructor    // Default constructor required by Hibernate
@AllArgsConstructor   // Constructor for all fields (optional if you need it)
@Builder
public class User {

    @Id
    @Column(name = "UID")
    private String uid;

    private String name;

    private String email;

    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
