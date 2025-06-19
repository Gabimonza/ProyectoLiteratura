package com.example.literalura.client;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.literalura.model.Libro;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GutendexClient {
  private final HttpClient client;
  private final ObjectMapper mapper;

  public GutendexClient(HttpClient client, ObjectMapper mapper) {
    this.client = client;
    this.mapper = mapper;
  }

  public Optional<Libro> buscarPorTitulo(String titulo) {
    try {
      String url = "https://gutendex.com/books/?search=" + URLEncoder.encode(titulo);
      HttpRequest req = HttpRequest.newBuilder()
                                   .uri(URI.create(url))
                                   .header("Accept", "application/json")
                                   .build();
      HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
      JsonNode root = mapper.readTree(resp.body()).get("results").get(0);
      Libro libro = mapper.treeToValue(root, Libro.class);
      return Optional.of(libro);
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}