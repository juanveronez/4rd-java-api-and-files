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

        Locale locale = null;

        try {
            locale = ViaCep.findCep(cep);
            System.out.println("Local encontrado: " + locale);
        } catch (BadCepException e) {
            System.out.println("Erro no formato do CEP (EX: 01001000)");
            System.out.println(e.getMessage());
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro na busca do CEP: " + cep);
            System.out.println(e.getMessage());
        }

        if (locale != null) {
            FileLoader fileLoader = new FileLoader(locale.cep() + ".json");
            try {
                fileLoader.saveJson(locale);
                System.out.println("Endereço salvo no arquivo correspondente");
            } catch (IOException e) {
                System.out.println("Erro ao salvar arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Endereço não recuperado, nada será salvo");
        }
    }
}
