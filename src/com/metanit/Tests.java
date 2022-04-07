package com.metanit;

import java.util.ArrayList;
import java.util.List;

public class Tests {
    public static void testCase1() {
        int n = 2;
        double minS = 40;
        List<Apartment> inputList = new ArrayList<>();
        Apartment apartment1 = new Apartment("ленинский", 23, 2, 45, 2000000);
        Apartment apartment2 = new Apartment("ленинский", 23, 2, 45, 1800000);
        Apartment apartment3 = new Apartment("ленинский", 23, 2, 45, 1500000);

        inputList.add(0, apartment1);
        inputList.add(1, apartment2);
        inputList.add(2, apartment3);

        List<Apartment> expected = new ArrayList<>();
        expected.add(0, apartment3);

        List<Apartment> outputList = Solution.findTheMostCheap(inputList, n, minS);

        assert expected.equals(outputList);
    }

    public static void testCase2() {
        int n = 3;
        double minS = 50;
        List<Apartment> inputList = new ArrayList<>();
        Apartment apartment1 = new Apartment("Западный", 23, 3, 55, 2000000);
        Apartment apartment2 = new Apartment("Западный", 23, 2, 45, 1800000);
        Apartment apartment3 = new Apartment("ленинский", 23, 3, 54, 1500000);

        inputList.add(0, apartment1);
        inputList.add(1, apartment2);
        inputList.add(2, apartment3);

        List<Apartment> expected = new ArrayList<>();
        expected.add(0, apartment1);
        expected.add(1, apartment3);


        List<Apartment> outputList = Solution.findTheMostCheap(inputList, n, minS);

        assert expected.equals(outputList);
    }

    public static void testCase3() {
        int n = 4;
        double minS = 90;
        List<Apartment> inputList = new ArrayList<>();
        Apartment apartment1 = new Apartment("Западный", 23, 3, 55, 2000000);
        Apartment apartment2 = new Apartment("Западный", 35, 4, 95, 1800000);
        Apartment apartment3 = new Apartment("ленинский", 45, 3, 54, 1500000);
        Apartment apartment4 = new Apartment("ленинский", 23, 4, 91, 1740000);
        Apartment apartment5 = new Apartment("северный", 23, 4, 68, 1900000);
        Apartment apartment6 = new Apartment("северный", 35, 4, 90, 1740000);

        inputList.add(0, apartment1);
        inputList.add(1, apartment2);
        inputList.add(2, apartment3);
        inputList.add(3, apartment4);
        inputList.add(4, apartment5);
        inputList.add(5, apartment6);

        List<Apartment> expected = new ArrayList<>();
        expected.add(0, apartment2);
        expected.add(1, apartment4);
        expected.add(2, apartment6);


        List<Apartment> outputList = Solution.findTheMostCheap(inputList, n, minS);

        assert expected.equals(outputList);
    }
}
