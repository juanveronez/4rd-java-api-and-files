package com.screenmatch.modules.module2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.screenmatch.modules.module2.models.Book;
import com.screenmatch.modules.module2.models.Person;

public class Main {
    public static void main(String[] args) {
        exercise3();
    }

    /**
     * Crie uma classe Pessoa usando o conceito de Record em Java, com atributos como nome,
     * idade e cidade. Em seguida, implemente um programa que utiliza a biblioteca Gson para
     * converter um JSON representando uma pessoa em um objeto do tipo Pessoa.
     */
    private static void exercise1() {
        String person = "{ \"name\": \"Juan\", \"age\": 25, \"city\": \"Campinas\" }";

        Gson gson = new Gson();
        Person juan = gson.fromJson(person, Person.class);
        System.out.printf("""
                Nome: %s
                Idade: %d
                Cidade: %s 
                """, juan.name(), juan.age(), juan.city());
    }

    /**
     * Modifique o programa do Exercício anterior para permitir a conversão de um JSON mesmo
     * se alguns campos estiverem ausentes ou se houver campos adicionais não representados
     * no objeto Pessoa. Consulte a documentação da biblioteca Gson para flexibilizar a conversão.
     */
    private static void exercise2() {
        String person = "{ \"name\": \"Juan\", \"age\": 25, \"city\": \"Campinas\", \"marital_status\": \"single\"  }";

        Gson gson = new GsonBuilder().setLenient().create();
        Person juan = gson.fromJson(person, Person.class);
        System.out.printf("""
                Nome: %s
                Idade: %d
                Cidade: %s,
                Ocupação: %s
                """, juan.name(), juan.age(), juan.city(), juan.occupation());
    }

    /**
     * Crie uma classe Livro que contenha atributos como título, autor e um objeto representando
     * a editora. Em seguida, implemente um programa que utiliza a biblioteca Gson para converter
     * um JSON aninhado representando um livro em um objeto do tipo Livro.
     */
    private static void exercise3() {
        String bookJson = """
                {
                    "title": "Use a cabeça Java",
                    "writers": ["Kathy Sierra", "Bert Bates", "Trisha Gee"],
                    "publisher": { "name": "Alta Books Editora", "email": "ouvidoria@altabooks.com.br" }
                }
                """;

        Gson gson = new Gson();
        Book book = gson.fromJson(bookJson, Book.class);

        System.out.println(book);
    }
}
