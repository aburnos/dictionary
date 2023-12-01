package com.aburnos.dictionary.service;

import com.aburnos.dictionary.model.Dictionary;
import com.aburnos.dictionary.repository.DictionaryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@Service
public class DictionaryService {

    @Autowired
    private DictionaryRepository dictionaryRepository;

    public List<Dictionary> getAllDictionaries() {
        return dictionaryRepository.findAll();
    }

    public Dictionary createDictionary(@RequestBody Dictionary dictionary) {
        return dictionaryRepository.save(dictionary);
    }

    public Dictionary getDictionaryById(@PathVariable Long id) {
        return dictionaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Dictionary not exist with id : %d", id)));
    }

    public Dictionary updateDictionary(@PathVariable Long id, @RequestBody Dictionary dictionary) {
        Dictionary update = getDictionaryById(id);

        update.setDescription(dictionary.getDescription());
        update.setItems(dictionary.getItems());

        Dictionary updated = dictionaryRepository.save(update);
        return updated;
    }

    public Boolean deleteDictionary(@PathVariable Long id) {
        Dictionary dictionary = getDictionaryById(id);
        dictionaryRepository.delete(dictionary);
        return Boolean.TRUE;
    }
}
