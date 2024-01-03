package com.projeto.java.microsservicos.userapi.controller;

import com.projeto.java.microsservicos.userapi.converter.UserDTO;
import com.projeto.java.microsservicos.userapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/{cpf}/cpf")
    public UserDTO findByCpf(@RequestParam(name="key", required=true) String key,
                             @PathVariable String cpf) {
        return userService.findByCpf(cpf, key);
    }

    @GetMapping("/search")
    public List<UserDTO> queryByName(@RequestParam(name="nome", required = true) String nome) {
        return userService.queryByName(nome);
    }

    @GetMapping("/pageable")
    public Page<UserDTO> getUsersPage(Pageable pageable) {
        return userService.getAllPage(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody @Valid UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        return userService.save(userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO editUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.editUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws UserPrincipalNotFoundException {
        userService.delete(id);
    }

}
