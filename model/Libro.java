package com.example.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String language;
  private int download_count;

  @Transient
  @JsonAlias("authors")
  private Autor[] authors;

  @ManyToOne(cascade = CascadeType.ALL)
  private Autor autor;

  // getters, setters, toString()
}