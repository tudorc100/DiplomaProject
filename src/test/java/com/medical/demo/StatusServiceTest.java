package com.medical.demo;

import com.medical.demo.dtos.StatusDTO;
import com.medical.demo.model.Status;
import com.medical.demo.model.mapper.StatusMapper;
import com.medical.demo.repository.StatusRepository;
import com.medical.demo.service.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatusServiceTest {

    @InjectMocks
    private StatusService statusService;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private StatusMapper statusMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByIdSuccess() {
        Status status = new Status();
        status.setId(1L);
        when(statusRepository.findById(1L)).thenReturn(Optional.of(status));

        Status foundStatus = statusService.findById(1L);
        assertNotNull(foundStatus);
        assertEquals(1L, foundStatus.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(statusRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> statusService.findById(1L));
    }

    @Test
    void testGetAllStatusesForUser() {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setUserId(1L);

        Status status = new Status();
        status.setUserId(1L);

        when(statusRepository.findAllByUserIdEquals(1L)).thenReturn(Arrays.asList(status));
        when(statusMapper.toDto(status)).thenReturn(statusDTO);

        List<StatusDTO> statuses = statusService.getAllStatusesForUser(1L);

        assertNotNull(statuses);
        assertFalse(statuses.isEmpty());
        assertEquals(1L, statuses.get(0).getUserId());
    }


    @Test
    void testFindAll() {
        Status status = new Status();
        StatusDTO statusDTO = new StatusDTO();

        when(statusRepository.findAll()).thenReturn(Arrays.asList(status));
        when(statusMapper.toDto(status)).thenReturn(statusDTO);

        List<StatusDTO> statuses = statusService.findAll();

        assertNotNull(statuses);
        assertFalse(statuses.isEmpty());
        assertEquals(1, statuses.size());
    }

    @Test
    void testCreateStatus() {
        StatusDTO statusDTO = new StatusDTO();
        Status status = new Status();

        when(statusMapper.fromDto(statusDTO)).thenReturn(status);
        when(statusRepository.save(status)).thenReturn(status);
        when(statusMapper.toDto(status)).thenReturn(statusDTO);

        StatusDTO createdStatusDTO = statusService.createStatus(statusDTO);

        assertNotNull(createdStatusDTO);
    }

    @Test
    void testUpdateStatus() {
        Status existingStatus = new Status();
        existingStatus.setId(1L);

        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setTimestamp(new String());
        statusDTO.setStatus("Updated Status");

        when(statusRepository.findById(1L)).thenReturn(Optional.of(existingStatus));
        when(statusRepository.save(existingStatus)).thenReturn(existingStatus);
        when(statusMapper.toDto(existingStatus)).thenReturn(statusDTO);

        StatusDTO updatedStatusDTO = statusService.updateStatus(1L, statusDTO);

        assertNotNull(updatedStatusDTO);
        assertEquals("Updated Status", updatedStatusDTO.getStatus());
        assertEquals(statusDTO.getTimestamp(), updatedStatusDTO.getTimestamp());
    }

    @Test
    void testDeleteStatus() {
        Status status = new Status();
        status.setId(1L);

        when(statusRepository.findById(1L)).thenReturn(Optional.of(status));

        statusService.deleteStatus(1L);

        verify(statusRepository, times(1)).delete(status);
    }
}