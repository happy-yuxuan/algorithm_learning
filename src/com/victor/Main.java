package com.victor;

import com.victor.List.Array;

public class Main {

    public static void main(String[] args) {
	    Array<Integer> arr = new Array<>();

        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(1);
        System.out.println(arr);

        arr.remove(1);
        System.out.println(arr);
    }
}
