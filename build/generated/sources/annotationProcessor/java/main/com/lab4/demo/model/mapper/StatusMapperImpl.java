package com.lab4.demo.model.mapper;

import com.lab4.demo.dtos.StatusDTO;
import com.lab4.demo.model.Status;
import com.lab4.demo.model.Status.StatusBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-22T22:41:35+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 15.0.9 (Azul Systems, Inc.)"
)
@Component
public class StatusMapperImpl implements StatusMapper {

    @Override
    public StatusDTO toDto(Status status) {
        if ( status == null ) {
            return null;
        }

        StatusDTO statusDTO = new StatusDTO();

        statusDTO.setId( status.getId() );
        statusDTO.setTimestamp( status.getTimestamp() );
        statusDTO.setStatus( status.getStatus() );
        statusDTO.setUserId( status.getUserId() );

        return statusDTO;
    }

    @Override
    public Status fromDto(StatusDTO status) {
        if ( status == null ) {
            return null;
        }

        StatusBuilder status1 = Status.builder();

        status1.id( status.getId() );
        status1.timestamp( status.getTimestamp() );
        status1.status( status.getStatus() );
        status1.userId( status.getUserId() );

        return status1.build();
    }
}
