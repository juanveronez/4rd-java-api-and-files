package com.screenmatch;

import com.google.gson.Gson;
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

        String uriString = "https://www.omdbapi.com/?t=%s&apikey=594fa5ec".formatted(search);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriString))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        Gson gson = new Gson();
        // gson.fromJson accept a class to be the response class
        Title myTitle = gson.fromJson(response.body(), Title.class);
        System.out.println(myTitle.getName());
    }
}
