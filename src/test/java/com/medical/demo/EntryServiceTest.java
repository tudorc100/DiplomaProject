package com.medical.demo;

import com.medical.demo.dtos.EntryDTO;
import com.medical.demo.model.Entry;
import com.medical.demo.model.mapper.EntryMapper;
import com.medical.demo.repository.EntryRepository;
import com.medical.demo.service.EntryService;
import com.medical.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class EntryServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private EntryRepository entryRepository;

    @Mock
    private EntryMapper entryMapper;

    @InjectMocks
    private EntryService entryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByIdSuccess() {
        Entry entry = new Entry();
        entry.setId(1L);

        when(entryRepository.findById(1L)).thenReturn(Optional.of(entry));

        Entry result = entryService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(entryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> entryService.findById(1L));
    }

    @Test
    public void testFindAll() {
        Entry entry = new Entry();
        List<Entry> entryList = new ArrayList<>();
        entryList.add(entry);

        EntryDTO entryDTO = new EntryDTO();
        when(entryRepository.findAll()).thenReturn(entryList);
        when(entryMapper.toDto(entry)).thenReturn(entryDTO);

        List<EntryDTO> result = entryService.findAll();

        assertFalse(result.isEmpty());
        verify(entryMapper, times(1)).toDto(entry);
    }
    @Test
    public void testCreate() {
        EntryDTO entryDTO = new EntryDTO();
        Entry entry = new Entry();

        when(entryMapper.fromDto(entryDTO)).thenReturn(entry);
        when(entryRepository.save(entry)).thenReturn(entry);
        when(entryMapper.toDto(entry)).thenReturn(entryDTO);

        EntryDTO result = entryService.create(entryDTO);

        assertNotNull(result);
        verify(entryRepository, times(1)).save(entry);
    }

    @Test
    public void testDelete() {
        Entry entry = new Entry();
        entry.setId(1L);

        when(entryRepository.findById(1L)).thenReturn(Optional.of(entry));

        entryService.delete(1L);

        verify(entryRepository, times(1)).delete(entry);
    }

    @Test
    public void testEdit() {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setUserId(1L);
        Entry entry = new Entry();
        entry.setId(1L);

        when(userService.existsById(1L)).thenReturn(true);
        when(entryRepository.findById(1L)).thenReturn(Optional.of(entry));
        when(entryRepository.save(entry)).thenReturn(entry);
        when(entryMapper.toDto(entry)).thenReturn(entryDTO);

        EntryDTO result = entryService.edit(1L, entryDTO);

        assertNotNull(result);
        verify(entryRepository, times(1)).save(entry);
    }

    @Test
    public void testGetMedicalRecordsForUser() {
        Long userId = 1L;
        Entry entry = new Entry();
        List<Entry> entryList = new ArrayList<>();
        entryList.add(entry);

        EntryDTO entryDTO = new EntryDTO();
        when(entryRepository.findAllByUserIdEquals(userId)).thenReturn(entryList);
        when(entryMapper.toDto(entry)).thenReturn(entryDTO);

        List<EntryDTO> result = entryService.getMedicalRecordsForUser(userId);

        assertFalse(result.isEmpty());
        verify(entryMapper, times(1)).toDto(entry);
    }

}


