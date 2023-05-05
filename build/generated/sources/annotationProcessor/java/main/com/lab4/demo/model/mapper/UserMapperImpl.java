package com.lab4.demo.model.mapper;

import com.lab4.demo.dtos.UserDTO;
import com.lab4.demo.dtos.UserDTO.UserDTOBuilder;
import com.lab4.demo.model.User;
import com.lab4.demo.model.User.UserBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-29T19:07:16+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 15.0.9 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.username( user.getUsername() );
        userDTO.id( user.getId() );
        userDTO.email( user.getEmail() );
        userDTO.password( user.getPassword() );
        Set<User> set = user.getFamilyMembers();
        if ( set != null ) {
            userDTO.familyMembers( new ArrayList( set ) );
        }

        return userDTO.build();
    }

    @Override
    public User fromDto(UserDTO userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.username( userDto.getUsername() );
        user.id( userDto.getId() );
        user.email( userDto.getEmail() );
        user.password( userDto.getPassword() );
        List list = userDto.getFamilyMembers();
        if ( list != null ) {
            user.familyMembers( new HashSet<User>( list ) );
        }

        return user.build();
    }
}
