package com.screenmatch.metrics;

public class RecommendationsFilter {
    public void filter(Classifiable classifiable) {
        int classification = classifiable.getClassification();

        if (classification >= 4) {
            System.out.println("Está entre os favoritos");
        } else if (classification >= 2) {
            System.out.println("Muito bem avaliado");
        } else {
            System.out.println("Coloque na sua lista para assistir depois");
        }
    }
}
