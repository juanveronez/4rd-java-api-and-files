package com.screenmatch;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.screenmatch.models.OmdbTitle;
import com.screenmatch.models.Title;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Busque pelo nome do filme: ");
        String search = scanner.nextLine();

        try {
            String uriString = "https://www.omdbapi.com/?t=%s&apikey=594fa5ec".formatted(search);

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uriString))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();
            // gson.fromJson accept a class to be the response class
            // Title myTitle = gson.fromJson(response.body(), Title.class);
            OmdbTitle myOmdbTitle = gson.fromJson(response.body(), OmdbTitle.class);
            System.out.println(myOmdbTitle);

            Title myTitle = new Title(myOmdbTitle);

            System.out.println(myTitle);
        } catch (NumberFormatException exception) {
            System.out.println("Erro na transformação para número:");
            System.out.println(exception.getMessage());
        }  catch (IllegalArgumentException exception) {
            System.out.println("Erro na formação da uri, verifique o formato enviado:");
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println("Aconteceu algo e eu não sei o que é, exceção mais genérica possível");
            System.out.println("Não é a boa prática, mas fiz isso mesmo, o certo é sabermos sobre a exceção que está ocorrendo");
        }
    }
}
