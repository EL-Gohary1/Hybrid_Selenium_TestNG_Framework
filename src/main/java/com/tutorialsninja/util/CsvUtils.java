package com.tutorialsninja.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class CsvUtils {

    public String[] csvReader(int index) {
        try {
            FileReader fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\data3.csv");
            CSVReader csvReader = new CSVReader(fr);
            String[] csvCell;

            int currentIndex = 0;

            while ((csvCell = csvReader.readNext()) != null) {
                if (currentIndex == index) {
                    return csvCell;
                }
                currentIndex++;
            }
            return csvCell;
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

}
