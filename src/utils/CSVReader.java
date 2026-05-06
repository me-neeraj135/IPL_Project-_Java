package utils;

import javax.swing.plaf.basic.BasicBorders;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CSVReader {
    public static <T> List<T> read(String filePath, Function<Map<String, String>, T> mapper) throws IOException {
        List<T> data = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String[] headers = br.readLine().split(",");
        String line;

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            Map<String, String> row = new HashMap<>();

            for (int i = 0; i < headers.length; i++) {
                row.put(headers[i], i < values.length ? values[i] : "");
            }
            data.add(mapper.apply(row));
        }
        br.close();
        return data;
    }

}
