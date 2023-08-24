package com.lab4.demo.controller;

import com.lab4.demo.dtos.EntryDTO;
import com.lab4.demo.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@CrossOrigin
@RestController
@RequestMapping(ITEMS)
@RequiredArgsConstructor
public class EntryController {

    private final EntryService entryService;

    @GetMapping
    public List<EntryDTO> allItems() {
        return entryService.findAll();
    }
    @PostMapping
    public EntryDTO create(@RequestBody EntryDTO item) {
        return entryService.create(item);
    }
    @DeleteMapping(ITEMS_ID_PART)
    public void delete(@PathVariable Long id){
        entryService.delete(id);
    }
    @PatchMapping(ITEMS_ID_PART)
    public EntryDTO edit(@PathVariable Long id, @RequestBody EntryDTO item) {
        return entryService.edit(id, item);
    }
    @GetMapping(ITEMS_ID_PART)
    public List<EntryDTO> allItemsForUser(@PathVariable Long id) {
        return entryService.getMedicalRecordsForUser(id);
    }



}
