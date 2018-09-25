package com.victor.Tree;

import java.util.ArrayList;

public class Task {


    public static void main(String[] args) {

        //Pride and Prejudice
        System.out.println("----------Pride and Prejudice----------");
        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\happy_yuxuan\\IdeaProjects\\algorithm_learning\\src\\com\\victor\\Tree\\pride-and-prejudice.txt", words1);
        System.out.println("Total words: " + words1.size());

        BSTSet<String> set1 = new BSTSet<>();
        for(String word: words1) {
            set1.add(word);
        }
        System.out.println("Total different words: " + set1.getSize());


        // A Tale Of Two Cities
        System.out.println("----------A Tale Of Two Cities----------");
        ArrayList<String> words2 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\happy_yuxuan\\IdeaProjects\\algorithm_learning\\src\\com\\victor\\Tree\\a-tale-of-two-cities.txt", words2);
        System.out.println("Total words: " + words2.size());

        BSTSet<String> set2 = new BSTSet<>();
        for(String word: words2) {
            set2.add(word);
        }
        System.out.println("Total different words: " + set2.getSize());


    }

}
