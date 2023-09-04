package com.medical.demo.model.mapper;

import com.medical.demo.dtos.StatusDTO;
import com.medical.demo.model.Status;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    StatusDTO toDto(Status status);

    Status fromDto(StatusDTO status);

}