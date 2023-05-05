package com.lab4.demo.service;

import com.lab4.demo.dtos.EntryDTO;
import com.lab4.demo.model.Entry;
import com.lab4.demo.model.Status;
import com.lab4.demo.model.mapper.DeviceMapper;
import com.lab4.demo.repository.StatusRepository;
import com.lab4.demo.repository.EntryRepository;
import com.lab4.demo.websocket.WebSocketSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntryService {
    private final UserService userService;
    private final EntryRepository entryRepository;
    private final DeviceMapper deviceMapper;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private WebSocketSender webSocketSender;

    private Entry findById(Long id) {
        return entryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + id));
    }

    public List<EntryDTO> findAll() {
        return entryRepository.findAll().stream()
                .map(deviceMapper::toDto)
                .collect(Collectors.toList());
    }

    public EntryDTO create(EntryDTO item) {
        return deviceMapper.toDto(entryRepository.save(
                deviceMapper.fromDto(item)
        ));
    }
    public void delete(Long id){
        Entry entry = findById(id);
        entryRepository.delete(entry);
    }
    public EntryDTO edit(Long id, EntryDTO item) {
        Entry actEntry = findById(id);
        if(userService.existsById(item.getUserId())) {
            actEntry.setDescription(item.getDescription());
            actEntry.setDepartment(item.getDepartment());
            actEntry.setUserId(item.getUserId());
        }
        return deviceMapper.toDto(
                entryRepository.save(actEntry)
        );
    }
    public EntryDTO linkDeviceToUser(Long userId, Long deviceId)
    {
        Entry actEntry = findById(deviceId);
        if(userService.existsById(userId))
        {
            actEntry.setUserId(userId);}
        return deviceMapper.toDto(
                entryRepository.save(actEntry));
    }
    public List <EntryDTO> getDevicesForUser(Long userId)
    {
        return entryRepository.findAllByUserIdEquals(userId).stream()
                .map(deviceMapper::toDto)
                .collect(Collectors.toList());
    }


    }
