package com.medical.demo;

import com.medical.demo.model.Chat;
import com.medical.demo.model.Status;
import com.medical.demo.repository.*;
import com.medical.demo.security.AuthService;
import com.medical.demo.security.dto.SignupRequest;
import com.medical.demo.model.ERole;
import com.medical.demo.model.Role;
import com.medical.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final EntryRepository entryRepository;

    private final StatusRepository statusRepository;

    private final ChatRepository chatRepository;

    private final UserService userService;


    @Value("false")
    private Boolean bootstrap;



    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            entryRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            statusRepository.deleteAll();
            chatRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("tudor@gmail.com")
                    .username("tudor")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("tudor1@gmail.com")
                            .name("Tudor1")
                    .username("tudor1")
                    .password("WooHoo1!")
                            .cnp("1")
                    .roles(Set.of("CUSTOMER"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("tudor11@gmail.com")
                            .name("Tudor2")
                            .cnp("2")
                    .username("tudor11")
                    .password("WooHoo1!")
                    .roles(Set.of("CUSTOMER"))
                    .build());
            statusRepository.save(Status.builder().status("Test1")
                            .timestamp("Day 1 hr 1")
                            .userId(userRepository.findAll().get(2).getId())
                    .build());
            userService.addFamilyMember(userRepository.findUserByCnp("1").getId(),"2");
            chatRepository.save(new Chat(LocalDateTime.now(),"tudor","Test"));
        }
    }
}
