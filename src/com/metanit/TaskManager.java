package com.metanit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    public static TripleArg parseInputFile(String inputFilePath) {

        try {
            List<String> lines = new ArrayList<>();
            Scanner sc = new Scanner(new File(inputFilePath));
            while (sc.hasNext()) lines.add(sc.nextLine());

            List<Apartment> result = new ArrayList<>();
            int countOfRooms = 0;
            double minS = 0;

            for (int j = 0; j < lines.size(); j++) {
                String x = lines.get(j);
                String[] line = x.split("\\s+");
                if (j < lines.size() - 1) {
                    String currentStreet = line[0];
                    double currentSKitchen = Double.parseDouble(line[1]);
                    int currentCountOfRooms = Integer.parseInt(line[2]);
                    double currentS = Double.parseDouble(line[3]);
                    double currentPrice = Double.parseDouble(line[4]);
                    Apartment currentApartment = new Apartment(currentStreet, currentSKitchen, currentCountOfRooms, currentS, currentPrice);
                    result.add(currentApartment);
                } else {
                    try {
                        countOfRooms = Integer.parseInt(line[0]);
                        minS = Double.parseDouble(line[1]);
                        if (countOfRooms < 1 || minS < 1){
                            throw new IllegalArgumentException();
                        }
                        break;
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("значения не найдены");
                    }
                }
            }
            return new TripleArg(result, countOfRooms, minS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void writeToOutputFile(String outputFilePath, List<Apartment> result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            if (!result.isEmpty()){
                for (Apartment apartment : result) {
                    writer.write(apartment + "\n");
                }
            }
            else{
                writer.write("искомых квартир нет");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
