package com.consultcep.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class FileLoader {
    private final String filename;

    public FileLoader (String filename) {
        this.filename = filename;
    }

    private String toJson(Locale locale) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(locale);
    }

    public void saveJson(Locale locale) throws IOException {
        String json = this.toJson(locale);

        FileWriter writer = new FileWriter(this.filename);
        writer.write(json);
        writer.close();
    }
}
