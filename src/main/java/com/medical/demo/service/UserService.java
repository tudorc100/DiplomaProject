package com.medical.demo.service;

import com.medical.demo.repository.RoleRepository;
import com.medical.demo.repository.UserRepository;
import com.medical.demo.security.AuthService;
import com.medical.demo.security.dto.SignupRequest;
import com.medical.demo.dtos.UserDTO;
import com.medical.demo.model.mapper.UserMapper;
import com.medical.demo.model.ERole;
import com.medical.demo.model.Role;
import com.medical.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthService authService;

    private final RoleRepository roleRepository;


    public List<UserDTO> findAll() {
        Optional<Role> defaultRole = roleRepository.findByName(ERole.CUSTOMER);
        return userRepository.findAllByRolesContaining(defaultRole).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public UserDTO create(UserDTO userDTO){

        authService.register(SignupRequest.builder()
                .email(userDTO.getEmail())
                        .name(userDTO.getName())
                        .cnp(userDTO.getCnp())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .roles(Set.of("ADMIN"))
                .build());

        return userDTO;
    }
    public void delete(Long id){
        User user = findById(id);
        userRepository.delete(user);
    }
    public UserDTO edit(Long id, UserDTO userDTO) {
        User actUser = findById(id);
        actUser.setUsername(userDTO.getUsername());
        actUser.setName(userDTO.getName());
        actUser.setEmail(userDTO.getEmail());
        actUser.setCnp(userDTO.getCnp());
        userRepository.save(actUser);
        return userMapper.toDto(actUser);
    }

    public Boolean existsById(Long id)
    {
        return userRepository.existsById(id);
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found" + username));
    }

    public void addFamilyMember(Long id1, String cnp)
    {
        User userToAddFamilyMember =findById(id1);
        User familyMember=userRepository.findUserByCnp(cnp);
        userToAddFamilyMember.getFamilyMembers().add(familyMember);
        userRepository.save(userToAddFamilyMember);
    }

    public List<UserDTO> findFamilyMembers(String username)
    {
        return userRepository.findUserByUsernameEquals(username).getFamilyMembers().stream()
            .map(userMapper::toDto)
            .collect(Collectors.toList());
    }

}
