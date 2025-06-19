package com.example.literalura.service;

import com.example.literalura.client.GutendexClient;
import com.example.literalura.model.Libro;
import com.example.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogoService {
  private final GutendexClient client;
  private final LibroRepository libroRepo;

  public CatalogoService(GutendexClient client, LibroRepository libroRepo) {
    this.client = client;
    this.libroRepo = libroRepo;
  }

  public Optional<Libro> buscarYGuardar(String titulo) {
    Optional<Libro> opt = client.buscarPorTitulo(titulo);
    opt.ifPresent(libroRepo::save);
    return opt;
  }

  public List<Libro> listarTodos() {
    return libroRepo.findAll();
  }

  public List<Libro> listarPorIdioma(String idioma) {
    return libroRepo.findByLanguage(idioma);
  }
}