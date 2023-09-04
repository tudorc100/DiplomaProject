package com.medical.demo.model.mapper;

import com.medical.demo.dtos.EntryDTO;
import com.medical.demo.model.Entry;
import org.mapstruct.*;


    @Mapper(componentModel = "spring")
    public interface EntryMapper {

        EntryDTO toDto(Entry entry);

        Entry fromDto(EntryDTO item);

}
