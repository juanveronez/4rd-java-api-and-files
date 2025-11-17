package com.screenmatch.metrics;

import com.screenmatch.models.Title;

public class TimeCalculator {
    private int totalMinutes;

    public int getTotalMinutes() {
        return totalMinutes;
    }

    /**
     O Java aceita overload de métodos, ou seja, termos o mesmo método podendo ser rescrito com base nos parâmetros.
     Além disso, conseguirmos passar tanto uma série como um filme como um título acontece por conta do polimorfismo.

     public void includeTitle(Series series) {
        totalMinutes += series.getDurationInMinutes();
     }

     public void includeTitle(Movie movie) {
        totalMinutes += movie.getDurationInMinutes();
     }
     */
    public void includeTitle(Title title) {
        totalMinutes += title.getDurationInMinutes();
    }
}
