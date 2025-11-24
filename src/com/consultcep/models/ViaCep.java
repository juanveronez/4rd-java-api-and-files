package com.consultcep.models;

import com.consultcep.exceptions.BadCepException;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCep {
    public static Locale findCep(String cep) throws IOException, InterruptedException {
        if (cep.length() != 8) {
            throw new BadCepException("O formato do CEP informado é inválido");
        };

        HttpResponse<String> response = makeCepRequest(cep);

        if (response.statusCode() == 400) {
            throw new BadCepException("Formato do CEP está incorreto");
        }

        return convertData(response.body());
    }

    private static HttpResponse<String> makeCepRequest(String cep) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String url = "https://viacep.com.br/ws/%s/json/".formatted(cep);

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static Locale convertData(String json) {
        Gson gson = new Gson();
        Locale locale = gson.fromJson(json, Locale.class);

        if (locale.getHasError()) {
            throw new BadCepException("O CEP informado é inválido");
        }

        return locale;
    }
}
