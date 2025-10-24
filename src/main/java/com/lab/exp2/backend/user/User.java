package com.lab.exp2.backend.user;

import javax.persistence.*;                 // 注意是 javax
import javax.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "t_users")                    // ★ 指向你的表名
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                       // 对应 int auto_increment

    @NotBlank
    @Column(length = 50)
    private String username;

    @NotBlank
    @Column(length = 100)
    private String password;

    @Column(length = 50)
    private String nickname;

    @Column(length = 255)
    private String motto;

    @Column(length = 20)
    private String phone;

    @Column(length = 255)
    private String address;
}
