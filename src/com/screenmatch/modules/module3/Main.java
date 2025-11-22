package com.screenmatch.modules.module3;

import com.screenmatch.modules.module3.exceptions.BadFormatPasswordException;
import com.screenmatch.modules.module3.exceptions.GitHubNotFoundException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        exercise1();
        exercise2();
        exercise3();
    }

    /**
     * Crie um programa simples que solicita dois números ao usuário e realiza a divisão do primeiro pelo segundo. Utilize o bloco try/catch para tratar a exceção que pode ocorrer caso o usuário informe 0 como divisor.
     */
    private static void exercise1() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vamos dividir o primeiro pelo segundo valor?");

        System.out.print("Digite o primeiro valor: ");
        int a = scanner.nextInt();

        System.out.print("Digite o segundo valor: ");
        int b = scanner.nextInt();

        try {
            int result = a / b;
            System.out.printf("%d / %d = %d", a, b, result);
        } catch (ArithmeticException e) {
            System.out.println("A divisão por zero não é válida!");
        }
    }

    /**
     * Crie um programa que lê uma senha do usuário. Utilize o bloco try/catch para capturar a exceção SenhaInvalidaException, uma classe de exceção personalizada que deve ser lançada caso a senha não atenda a critérios específicos (por exemplo, ter pelo menos 8 caracteres).
     */
    private static void exercise2() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite sua senha: ");
        String password = scanner.nextLine();

        try {
            validatePassword(password);

            System.out.println("Senha salva com sucesso!");
        } catch (BadFormatPasswordException exception) {
            System.out.println("Exceção: " + exception.getMessage());
        }
    }

    private static void validatePassword(String password) {
        if (password.length() < 8) {
            throw new BadFormatPasswordException("Sua senha deve ter no mínimo 8 caracteres");
        }
    }

    /**
     * Desenvolva um programa em Java que permite aos usuários consultar informações sobre um usuário do GitHub (utilize a API pública do GitHub para obter os dados). Crie uma classe de exceção personalizada, ErroConsultaGitHubException, que estende RuntimeException. Lance essa exceção quando o nome de usuário não for encontrado. No bloco catch, trate de forma específica essa exceção, exibindo uma mensagem amigável.
     */
    private static void exercise3() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Buscar usuário no GitHub:");
        String username = scanner.nextLine();

        try {
            String userJson = findGitHubUsername(username);
            System.out.println("GitHub User: " + userJson);
        } catch (GitHubNotFoundException exception) {
            System.out.println("Exceção: " + exception.getMessage());
        }
    }

    private static String findGitHubUsername(String username) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String url = "https://api.github.com/users/"  + username;
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .header("Accept", "application/vnd.github.v3+json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            throw new GitHubNotFoundException("Usuário informado não encontrado!");
        }

        return response.body();
    }


}
