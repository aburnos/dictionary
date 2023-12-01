package com.aburnos.dictionary.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DictionaryControllerTest {
    @Autowired
    private DictionaryController dictionaryController;

    @Test
    void contextLoads() {
        assertThat(dictionaryController).isNotNull();
    }
}