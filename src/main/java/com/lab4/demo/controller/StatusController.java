package com.lab4.demo.controller;

import com.lab4.demo.UrlMapping;
import com.lab4.demo.dtos.StatusDTO;
import com.lab4.demo.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.*;
@CrossOrigin
@RestController
@RequestMapping(UrlMapping.STATUS) // You will need to add a STATUS endpoint in the UrlMapping
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @GetMapping()
    public List<StatusDTO> getAllStatuses() {
        return statusService.findAll();
    }
    @GetMapping("/{userId}")
    public List<StatusDTO> getAllStatusesForUser(@PathVariable Long userId) {
        return statusService.getAllStatusesForUser(userId);
    }

    @PostMapping
    public StatusDTO createStatus(@RequestBody StatusDTO statusDTO) {
        return statusService.createStatus(statusDTO);
    }

    @PatchMapping(STATUS_ID_PART)
    public StatusDTO updateStatus(@PathVariable Long id, @RequestBody StatusDTO statusDTO) {
        return statusService.updateStatus(id, statusDTO);
    }

    @DeleteMapping(STATUS_ID_PART)
    public void deleteStatus(@PathVariable Long id) {
        statusService.deleteStatus(id);
    }
}