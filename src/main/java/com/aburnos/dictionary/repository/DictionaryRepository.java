package com.aburnos.dictionary.repository;

import com.aburnos.dictionary.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
}
