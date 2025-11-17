package com.screenmatch.models;

import com.screenmatch.metrics.Classifiable;

public class Episode implements Classifiable {
    private int number;
    private String name;
    private Series series;

    private int visualizations;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public int getVisualizations() {
        return visualizations;
    }

    public void setVisualizations(int visualizations) {
        this.visualizations = visualizations;
    }

    @Override
    public int getClassification() {
        if (visualizations > 100) {
            return 4;
        }

        return 2;
    }
}
