package com.screenmatch.modules.module2.models;

import com.google.gson.annotations.SerializedName;

public class Client {
    private String name;
    @SerializedName("city")
    private String bornCity;
    @SerializedName("age")
    private int currentAge;

    public Client(Person person) {
        this.name = person.name();
        this.bornCity = person.city();
        this.currentAge = person.age();
    }

    public String getName() {
        return name;
    }

    public String getBornCity() {
        return bornCity;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", bornCity='" + bornCity + '\'' +
                ", currentAge=" + currentAge +
                '}';
    }
}
