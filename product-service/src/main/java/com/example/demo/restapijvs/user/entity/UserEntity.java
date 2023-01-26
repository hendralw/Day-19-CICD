package com.example.demo.restapijvs.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "springboot_user_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private int id;
    @Column(name = "name")
    @Getter
    @Setter
    private String name;
    @Column(name = "email")
    @Getter
    @Setter
    private String email;
    @Column(name = "phone")
    @Getter
    @Setter
    private String phone;
    @Column(name = "password")
    @Getter
    @Setter
    private String password;
}
