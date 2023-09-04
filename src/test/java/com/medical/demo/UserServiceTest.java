package com.medical.demo;

import com.medical.demo.model.*;
import com.medical.demo.repository.RoleRepository;
import com.medical.demo.repository.UserRepository;
import com.medical.demo.security.AuthService;
import com.medical.demo.dtos.UserDTO;
import com.medical.demo.model.mapper.UserMapper;
import com.medical.demo.security.dto.SignupRequest;
import com.medical.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AuthService authService;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        Role role = new Role();
        role.setName(ERole.CUSTOMER);
        Optional<Role> defaultRole = Optional.of(role);
        User user = new User();
        List<User> userList = Arrays.asList(user);

        when(roleRepository.findByName(ERole.CUSTOMER)).thenReturn(defaultRole);
        when(userRepository.findAllByRolesContaining(defaultRole)).thenReturn(userList);

        UserDTO userDTO = new UserDTO();
        when(userMapper.toDto(user)).thenReturn(userDTO);

        List<UserDTO> result = userService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userMapper, times(1)).toDto(user);
    }


    @Test
    public void testFindById() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreate() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setUsername("testUser");
        userDTO.setPassword("testPassword");
        userDTO.setName("Test");
        userDTO.setCnp("123456");

        SignupRequest signupRequest = SignupRequest.builder()
                .email("test@example.com")
                .name("Test")
                .cnp("123456")
                .username("testUser")
                .password("testPassword")
                .roles(Set.of("ADMIN"))
                .build();

        doNothing().when(authService).register(signupRequest);

        UserDTO result = userService.create(userDTO);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(authService, times(1)).register(signupRequest);
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.delete(1L);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testEdit() {
        User user = new User();
        user.setId(1L);
        user.setUsername("oldUser");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("newUser");
        userDTO.setName("New Name");
        userDTO.setEmail("new@example.com");
        userDTO.setCnp("123456");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO result = userService.edit(1L, userDTO);

        assertNotNull(result);
        assertEquals("newUser", result.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testExistsById() {
        when(userRepository.existsById(1L)).thenReturn(true);

        boolean result = userService.existsById(1L);

        assertTrue(result);
        verify(userRepository, times(1)).existsById(1L);
    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("testUser");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        User result = userService.findByUsername("testUser");

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        verify(userRepository, times(1)).findByUsername("testUser");
    }
}
