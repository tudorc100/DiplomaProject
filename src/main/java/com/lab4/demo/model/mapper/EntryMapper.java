package com.lab4.demo.model.mapper;

import com.lab4.demo.dtos.EntryDTO;
import com.lab4.demo.model.Entry;
import org.mapstruct.*;


    @Mapper(componentModel = "spring")
    public interface EntryMapper {

        EntryDTO toDto(Entry entry);

        Entry fromDto(EntryDTO item);

}
