package com.screenmatch.models;

import com.google.gson.annotations.SerializedName;

public class Title implements Comparable<Title> {
    // @SerializedName("Title")
    private String name;
    // @SerializedName("Year")
    private int releaseYear;
    // @SerializedName("imdbRating")
    private double imdbReview;

    private int durationInMinutes;
    private boolean free;
    private double reviewsSum;
    private int reviewsQuantity;

    public Title(String name, int releaseYear) {
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public Title(OmdbTitle omdbTitle) {
        this.name = omdbTitle.title();
        this.releaseYear = Integer.parseInt(omdbTitle.year());

        int spaceIndex = omdbTitle.runtime().indexOf(" ");
        this.durationInMinutes = Integer.parseInt(omdbTitle.runtime().substring(0,spaceIndex));
    }

    public void displaySpecs() {
        String specs = """
                        -------- Fixa Técnica do Filme --------
                        %s
                        Nota no IMDB:           %.2f
                        Ano de lançamento:      %d
                        Duração (em minutos):   %d
                        """.formatted(name, imdbReview, releaseYear, durationInMinutes);

        System.out.println(specs);
    }

    public void addReview(double reviewScore) {
        reviewsSum += reviewScore;
        reviewsQuantity++;
    }

    public double getMeanOfReviews() {
        return reviewsSum / reviewsQuantity;
    }

    public int getReviewsQuantity() {
        return reviewsQuantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setImdbReview(double imdbReview) {
        this.imdbReview = imdbReview;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public boolean isFree() {
        return free;
    }

    public double getImdbReview() {
        return imdbReview;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    @Override
    public int compareTo(Title otherTitle) {
        return getName().compareTo(otherTitle.getName());
    }

    @Override
    public String toString() {
        return "Title{" +
                "name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", imdbReview=" + imdbReview +
                ", durationInMinutes=" + durationInMinutes +
                '}';
    }
}
