package com.medical.demo.service;

import com.medical.demo.dtos.EntryDTO;
import com.medical.demo.model.Entry;
import com.medical.demo.model.mapper.EntryMapper;
import com.medical.demo.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntryService {
    private final UserService userService;
    private final EntryRepository entryRepository;
    private final EntryMapper entryMapper;


    public Entry findById(Long id) {
        return entryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + id));
    }

    public List<EntryDTO> findAll() {
        return entryRepository.findAll().stream()
                .map(entryMapper::toDto)
                .collect(Collectors.toList());
    }

    public EntryDTO create(EntryDTO item) {
        return entryMapper.toDto(entryRepository.save(
                entryMapper.fromDto(item)
        ));
    }
    public void delete(Long id){
        Entry entry = findById(id);
        entryRepository.delete(entry);
    }
    public EntryDTO edit(Long id, EntryDTO item) {
        Entry actEntry = findById(id);
        if(userService.existsById(item.getUserId())) {
            actEntry.setTitle(item.getTitle());
            actEntry.setDescription(item.getDescription());
            actEntry.setDepartment(item.getDepartment());
            actEntry.setEntryDate(item.getEntryDate());
            actEntry.setUserId(item.getUserId());
        }
        return entryMapper.toDto(
                entryRepository.save(actEntry)
        );
    }
    public List <EntryDTO> getMedicalRecordsForUser(Long userId)
    {
        return entryRepository.findAllByUserIdEquals(userId).stream()
                .map(entryMapper::toDto)
                .collect(Collectors.toList());
    }


    }
