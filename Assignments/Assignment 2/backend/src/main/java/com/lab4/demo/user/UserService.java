package com.lab4.demo.user;

import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import com.lab4.demo.user.dto.UserDTO;
import com.lab4.demo.user.dto.UserListDTO;
import com.lab4.demo.user.dto.UserMinimalDTO;
import com.lab4.demo.user.mapper.UserMapper;
import com.lab4.demo.user.model.ERole;
import com.lab4.demo.user.model.Role;
import com.lab4.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private Role findRolesFromStrings(String role){
        return
                        roleRepository.findByName(ERole.valueOf(role))
                                .orElseThrow(()-> new EntityNotFoundException("Invalid role"));

    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public UserDTO create(UserDTO userDTO) {
        User actUser = userMapper.fromDto(userDTO);

        Set<Role> roles = new HashSet<>();

        //getting the roles for this new user, else insert the default one: CUSTOMER
        if(userDTO.getRole()==null){
            Role role = roleRepository.findByName(ERole.EMPLOYEE).orElseThrow(() -> new RuntimeException("Role not found!"));
            roles.add(role);
        }
        else {
            Role role = roleRepository.findByName(ERole.valueOf(userDTO.getRole())).orElseThrow(()->new RuntimeException("User doesn't have an assigned role!"));
            roles.add(role);
        }

        actUser.setRoles(roles);
        if (userRepository.existsByUsername(actUser.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        if (userRepository.existsByEmail(actUser.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        actUser.setPassword(passwordEncoder.encode(actUser.getPassword()));
        return userMapper.toDto(userRepository.save(actUser));
    }

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    public UserDTO edit(Long id, UserDTO userDTO){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

//        Set<Role> roles = new HashSet<>();
//        Role role = roleRepository.findByName(ERole.valueOf(userDTO.getRole())).orElseThrow(()->new RuntimeException("User doesn't have an assigned role yet!"));
//        roles.add(role);
//        user.setRoles(roles);
        return userMapper.toDto(
                userRepository.save(user)
        );
    }
    public UserDTO changePassword(Long id, String newPassword) {
        User user = findById(id);
        user.setPassword(newPassword);
        return userMapper.toDto(userRepository.save(user));
    }

    public void delete(Long id){
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.get());
    }

    public UserDTO get(Long id) {
        return userMapper.toDto(findById(id));
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }

}
