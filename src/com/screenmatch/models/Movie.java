package com.screenmatch.models;

import com.screenmatch.metrics.Classifiable;

public class Movie extends Title implements Classifiable {
    private String director;

    public Movie(String name, int releaseYear) {
        super(name, releaseYear);
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public int getClassification() {
        return (int) getMeanOfReviews() / 2;
    }

    @Override
    public String toString() {
        return "%s (%d)".formatted(getName(), getReleaseYear());
    }
}
