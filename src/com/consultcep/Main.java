package com.consultcep;

import com.consultcep.exceptions.BadCepException;
import com.consultcep.models.FileLoader;
import com.consultcep.models.Locale;
import com.consultcep.models.ViaCep;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o cep desejado: ");
        String cep = scanner.nextLine();

        try {
            String cepJSON = ViaCep.findCep(cep);
            Locale locale = ViaCep.convertData(cepJSON);

            System.out.println("Local encontrado: " + locale);

            FileLoader fileLoader = new FileLoader("cep.json");
            fileLoader.saveJson(locale);
        } catch (BadCepException e) {
            System.out.println("Erro no formato do CEP (EX: 01001000)");
            System.out.println(e.getMessage());
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro na busca do CEP: " + cep);
            System.out.println(e.getMessage());
        }
    }
}
