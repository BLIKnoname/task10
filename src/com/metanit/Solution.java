package com.metanit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private static final double e = 1e-10;

    public static List<Apartment> findTheMostCheap(List<Apartment> list, int countOfRooms, double minS) {

        Map<String, Apartment> result = new LinkedHashMap<>();

        for (Apartment apartment : list) {

            if (result.containsKey(apartment.street)) {
                Apartment aFromMap = result.get(apartment.street);
                if ((aFromMap.price - apartment.price > e) && apartment.countOfRooms == countOfRooms && apartment.s >= minS) {

                    result.put(apartment.street, apartment);

                }
            } else if (apartment.countOfRooms == countOfRooms
                    && apartment.s >= minS) {
                result.put(apartment.street, apartment);
            }
        }
        return new ArrayList<>(result.values());
    }
}
