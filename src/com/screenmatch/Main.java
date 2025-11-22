package com.screenmatch;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.screenmatch.exceptions.YearConversionException;
import com.screenmatch.models.OmdbTitle;
import com.screenmatch.models.Title;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        HttpClient client = HttpClient.newHttpClient();

        String search = "";
        List<Title> titles = new ArrayList<>();

        while (!search.equalsIgnoreCase("sair")) {
            System.out.print("Busque pelo nome do filme (ou digite \"Sair\" para encerrar): ");
            search = scanner.nextLine();

            if (search.equalsIgnoreCase("sair")) {
                break;
            }

            String uriString = "https://www.omdbapi.com/?t=%s&apikey=594fa5ec".formatted(search.replace(" ", "+"));

            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(uriString))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .create();

                OmdbTitle myOmdbTitle = gson.fromJson(response.body(), OmdbTitle.class);
                Title myTitle = new Title(myOmdbTitle);

                titles.add(myTitle);
                System.out.printf("%d Título: %s%n", titles.size(), myTitle);
            } catch (NumberFormatException exception) {
                System.out.println("Erro na transformação para número:");
                System.out.println(exception.getMessage());
            }  catch (IllegalArgumentException exception) {
                System.out.println("Erro na formação da uri, verifique o formato enviado:");
                System.out.println(exception.getMessage());
            } catch (NullPointerException | NegativeArraySizeException exception) {
                System.out.println("Tratando erro com multi-catch");
            } catch (YearConversionException exception) {
                System.out.println(exception.getMessage());
            }
            catch (Exception exception) {
                System.out.println("Aconteceu algo e eu não sei o que é, exceção mais genérica possível");
                System.out.println("Não é a boa prática, mas fiz isso mesmo, o certo é sabermos sobre a exceção que está ocorrendo");
            }
        }

        System.out.println("Salvando títulos: " + titles);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        FileWriter writer = new FileWriter("titles.json");
        writer.write(gson.toJson(titles));
        writer.close();
    }
}
