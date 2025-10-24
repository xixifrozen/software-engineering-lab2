package com.lab.exp2.backend.user;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repo;

    // 健康检查（不查库）
    @GetMapping("/ping")
    public String ping() { return "ok"; }

    // R：列表
    @GetMapping("")
    public List<User> list() { return repo.findAll(); }

    // R：按 id 查询
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // C：新增
    @PostMapping("")
    public User create(@RequestBody @Valid User u) {
        return repo.save(u);
    }

    // U：修改
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody @Valid User u) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        u.setId(id);
        return ResponseEntity.ok(repo.save(u));
    }

    // D：删除
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
