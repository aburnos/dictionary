package com.aburnos.dictionary.repository;

import com.aburnos.dictionary.model.Dictionary;
import com.aburnos.dictionary.model.DictionaryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DictionaryRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Test
    public void contextLoads() {
        assertNotNull(testEntityManager);
    }

    private Dictionary dictionary;

    @BeforeEach
    public void setup() {
        dictionary = new Dictionary();
        dictionary.setDescription("description");
        List<DictionaryItem> items = new ArrayList<>() {{
            add(DictionaryItem.builder().key("key").value("value").dictionary(dictionary).build());
        }};
        dictionary.setItems(items);
    }


    @Test
    public void createDictionaryTest() {
        assertNull(dictionary.getId());
        Dictionary savedDictionary = dictionaryRepository.save(dictionary);
        assertNotNull(savedDictionary.getId());
    }

    @Test
    public void getDictionaryTest() {
        assertNull(dictionary.getId());
        dictionaryRepository.save(dictionary);
        assertNotNull(dictionary.getId());

        Dictionary expected = dictionaryRepository.findById(dictionary.getId()).orElse(null);
        assertNotNull(expected.getId());
        assertEquals(expected.getId(), dictionary.getId());
    }

    @Test
    public void updateDictionaryTest() {
        assertNull(dictionary.getId());
        dictionaryRepository.save(dictionary);
        assertNotNull(dictionary.getId());

        Dictionary saved = dictionaryRepository.findById(dictionary.getId()).orElse(null);
        assertNotNull(saved.getId());
        assertEquals(saved.getId(), dictionary.getId());

        saved.setDescription("updated_description");
        Dictionary updateDictionary = dictionaryRepository.save(saved);
        assertThat(updateDictionary.getDescription()).isEqualTo("updated_description");
    }

    @Test
    public void deleteDictionaryTest() {
        assertNull(dictionary.getId());
        dictionaryRepository.save(dictionary);
        assertNotNull(dictionary.getId());

        dictionaryRepository.deleteById(dictionary.getId());
        Optional<Dictionary> dictionaryOptional = dictionaryRepository.findById(dictionary.getId());
        assertThat(dictionaryOptional).isEmpty();
    }

    @Test
    public void getAllDictionaries() {
        assertNull(dictionary.getId());
        dictionaryRepository.save(dictionary);
        assertNotNull(dictionary.getId());

        Dictionary dictionary2 = new Dictionary();
        dictionary2.setDescription("description");
        List<DictionaryItem> items = new ArrayList<>() {{
            add(DictionaryItem.builder().key("key").value("value").dictionary(dictionary).build());
        }};
        dictionary2.setItems(items);

        assertNull(dictionary2.getId());
        dictionaryRepository.save(dictionary2);
        assertNotNull(dictionary2.getId());

        List<Dictionary> dictionaryList = dictionaryRepository.findAll();

        assertThat(dictionaryList).isNotNull();
        assertThat(dictionaryList.size()).isGreaterThanOrEqualTo(2);
    }
}