package com.lab4.demo;

import com.lab4.demo.model.Entry;
import com.lab4.demo.model.Status;
import com.lab4.demo.repository.EntryRepository;
import com.lab4.demo.repository.StatusRepository;
import com.lab4.demo.service.EntryService;
import com.lab4.demo.security.AuthService;
import com.lab4.demo.security.dto.SignupRequest;
import com.lab4.demo.repository.RoleRepository;
import com.lab4.demo.repository.UserRepository;
import com.lab4.demo.model.ERole;
import com.lab4.demo.model.Role;
import com.lab4.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//import java.net.http.HttpResponse;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final EntryRepository entryRepository;

    private final StatusRepository statusRepository;

    private final UserService userService;


    @Value("true")
    private Boolean bootstrap;

    @Value("false")
    private Boolean read;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            entryRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            statusRepository.deleteAll();
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


//            entryRepository.deleteAll();
//            entryRepository.save(Entry.builder().id(10L).description("Tilivizor")
//                    .address("Marinescu")
//                    .consumption(700)
//                    .userId(userRepository.findAll().get(1).getId())
//                    .build());
//            entryRepository.save(Entry.builder().id(11L).description("Tilivizor Doi")
//                    .address("Marinescu Doi")
//                    .consumption(700)
//                    .userId(userRepository.findAll().get(2).getId())
//                    .build());
        }
//        if (read)
//        {
//            QueueConsumer q=new QueueConsumer(entryService);
//            try {
//                q.readMessages();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
