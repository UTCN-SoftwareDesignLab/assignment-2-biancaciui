package com.lab4.demo.user;

import com.lab4.demo.book.model.dto.BookDTO;
import com.lab4.demo.user.dto.UserDTO;
import com.lab4.demo.user.dto.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.ENTITY;
import static com.lab4.demo.UrlMapping.USERS;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @GetMapping(ENTITY)
    public UserDTO getUser(@PathVariable Long id) {
        return userService.get(id);
    }

    @PostMapping
    public void create(@RequestBody UserDTO user){
        userService.create(user);
    }

    @PutMapping(ENTITY)
    public void edit(@PathVariable Long id, @RequestBody UserDTO user){
        userService.edit(id, user);
    }

    @PatchMapping(ENTITY)
    public UserDTO changePassword(@PathVariable Long id, @RequestBody String password) {
        return userService.changePassword(id, password);
    }


    @DeleteMapping(ENTITY) //delete by id
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
    @DeleteMapping()
    public void delete(){
        userService.deleteAll();
    }


}
