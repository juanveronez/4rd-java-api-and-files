package modules.module4;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            exercise1();
        } catch (IOException e) {
            System.out.println("Exceção no exercício 1: " + e.toString());;
        }
    }

    /**
     * Crie um programa em Java que escreva a seguinte mensagem em um arquivo chamado "arquivo.txt": "Conteúdo a ser gravado no arquivo." Utilize as classes do pacote java.io.
     */
    private static void exercise1() throws IOException {
        FileWriter writer = new FileWriter("file.txt");
        writer.write("Utilize as classes do pacote java.io");
        writer.close();

        File file = new File("file.txt");
        FileReader reader = new FileReader(file);

        String text = "";

        int data = reader.read();
        while (data != -1) {
            text += (char) data;
            data = reader.read();
        }

        reader.close();
        System.out.println("Texto salvo: " + text);
    }
}
