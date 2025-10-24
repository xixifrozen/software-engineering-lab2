package com.lab.exp2.backend.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTests {
    @Autowired UserRepository repo;

    @Test
    void createAndFind() {
        User u = User.builder().username("u_test").password("p").nickname("nick").build();
        User saved = repo.save(u);
        assertThat(saved.getId()).isNotNull();
        assertThat(repo.findById(saved.getId())).isPresent();
    }
}
