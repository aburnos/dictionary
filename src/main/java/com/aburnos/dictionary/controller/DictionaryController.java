package com.aburnos.dictionary.controller;

import com.aburnos.dictionary.model.Dictionary;
import com.aburnos.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/dictionary")
    public List<Dictionary> getAllDictionaries() {
        return dictionaryService.getAllDictionaries();
    }

    @PostMapping("/dictionary")
    public Dictionary createDictionary(@RequestBody Dictionary dictionary) {
        return dictionaryService.createDictionary(dictionary);
    }

    @GetMapping("/dictionary/{id}")
    public ResponseEntity<Dictionary> getDictionaryById(@PathVariable Long id) throws ResponseStatusException {
        Dictionary dictionary = dictionaryService.getDictionaryById(id);
        return ResponseEntity.ok(dictionary);
    }

    @PutMapping("/dictionary/{id}")
    public ResponseEntity<Dictionary> updateDictionary(@PathVariable Long id, @RequestBody Dictionary dictionary) {
        Dictionary updated = dictionaryService.updateDictionary(id, dictionary);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/dictionary/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDictionary(@PathVariable Long id) {
        Boolean status = dictionaryService.deleteDictionary(id);
        return ResponseEntity.ok(Map.of("deleted", status));
    }
}