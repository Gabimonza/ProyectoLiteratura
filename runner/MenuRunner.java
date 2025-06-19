package com.example.literalura.runner;

import com.example.literalura.model.Libro;
import com.example.literalura.service.CatalogoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MenuRunner implements CommandLineRunner {
  private final CatalogoService service;

  public MenuRunner(CatalogoService service) {
    this.service = service;
  }

  @Override
  public void run(String... args) {
    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.println("1. Buscar libro por título");
      System.out.println("2. Listar todos los libros");
      System.out.println("3. Listar por idioma");
      System.out.println("0. Salir");
      int opt = Integer.parseInt(sc.nextLine());
      switch (opt) {
        case 1:
          System.out.print("Título: ");
          Optional<Libro> lib = service.buscarYGuardar(sc.nextLine());
          System.out.println(lib.map(Libro::toString).orElse("No encontrado"));
          break;
        case 2:
          List<Libro> todos = service.listarTodos();
          todos.forEach(System.out::println);
          break;
        case 3:
          System.out.print("Idioma: ");
          service.listarPorIdioma(sc.nextLine()).forEach(System.out::println);
          break;
        case 0:
          System.exit(0);
      }
    }
  }
}