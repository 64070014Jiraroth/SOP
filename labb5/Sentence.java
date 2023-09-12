package com.example.labb5;
import java.io.Serializable;
import java.util.ArrayList;

public class Sentence implements Serializable {
    ArrayList<String> badSentences = new ArrayList<>();
    ArrayList<String> goodSentences = new ArrayList<>();
    public Sentence(ArrayList<String> badSentences, ArrayList<String> goodSentences) {
        this.badSentences = badSentences;
        this.goodSentences = goodSentences;
    }

    public Sentence() {

    }

    public void addBadSentences(String sentece) {
        this.badSentences.add(sentece);
    }

    public void addGoodSentences(String sentece) {
        this.goodSentences.add(sentece);
    }
}
