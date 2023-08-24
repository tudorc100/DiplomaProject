package com.lab4.demo.model.mapper;

import com.lab4.demo.dtos.StatusDTO;
import com.lab4.demo.model.Status;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    StatusDTO toDto(Status status);

    Status fromDto(StatusDTO status);

}