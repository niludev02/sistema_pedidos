package br.edu.fatecpg.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import br.edu.fatecpg.model.RespostaCarrinhos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CarrinhoService {

    private static final String URL = "https://dummyjson.com/carts?limit=0";

    public Optional<RespostaCarrinhos> buscarCarrinhos() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Erro: a API respondeu com status " + response.statusCode());
                return Optional.empty();
            }

            ObjectMapper mapper = new ObjectMapper();
            RespostaCarrinhos resposta =
                    mapper.readValue(response.body(), RespostaCarrinhos.class);
            return Optional.of(resposta);

        } catch (JsonProcessingException e) {
            System.out.println("Erro: o JSON recebido é inválido. " + e.getOriginalMessage());
        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Requisição interrompida.");
        }
        return Optional.empty();
    }
}
