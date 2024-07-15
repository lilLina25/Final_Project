package org.example.DataGeneration;

import com.opencsv.CSVReader;
import org.example.Models.Coffee;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVFileGeneration implements Generatable {

    private int count;

    public CSVFileGeneration(int count) {
        this.count = count;
    }


    public List<String[]> readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                csvReader.readNext(); // пропускаем хедеры
                return csvReader.readAll();
            }
        }
    }

    @Override
    public void dataGeneration()  {

        Path path = Paths.get("src/main/resources/Data.csv");

        List<String[]> allLines = null;

        try {
            // Считываем CSV в список
            allLines = readAllLines(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Проходим по списку, собираем класс из данных и отправляем
        for (int i = 0; i < count; i++) {
            String[] line = allLines.get(i % allLines.size());
            Coffee coffee = Coffee.builder()
                    .id(Integer.parseInt(line[0]))
                    .paymentType(line[1])
                    .price(Integer.parseInt(line[2]))
                    .typeOfCoffee(line[3])
                    .build();

            // Отправляем в репозиторий
            // Some class.add(coffee)
        }

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}