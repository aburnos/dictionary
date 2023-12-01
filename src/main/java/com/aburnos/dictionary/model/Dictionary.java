package com.aburnos.dictionary.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dictionaries")
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dictionary", cascade = CascadeType.ALL)
    private List<DictionaryItem> items;

    @Override
    public String toString() {
        return "Dictionary{" + "id=" + id + ", description='" + description + "'}";
    }
}