package session_1;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.function.Failable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TextFileAnalyzer {
    private static final Logger LOGGER = LogManager.getLogger(TextFileAnalyzer.class);

    public static void main(String[] args) {
        countUniqueWords();
    }

    public static void countUniqueWords() {
        try {
            String[] words = readFileAndConvertToLowerCase().split("[\\p{Punct}\\s]+");
            List<String> list = Arrays.asList(words);
            long result = Failable.stream(list).filter(i -> Collections.frequency(list, i) == 1).stream().count();
            System.out.println("Current text file has "+result+" unique words.");
        } catch (IOException e) {
            LOGGER.info("File not found!");
        }
    }

    public static String readFileAndConvertToLowerCase() throws IOException {
        String finalLine = null;
        File file = new File("src/main/resources/text.txt");
        try (LineIterator iterator = FileUtils.lineIterator(file, StandardCharsets.UTF_8.name())) {
            StringBuilder stringBuilder = new StringBuilder();
            while (iterator.hasNext()) {
                String line = iterator.nextLine() + " ";
                finalLine = stringBuilder.append(line).toString();
            }
        }
        if (finalLine != null) {
            return finalLine.toLowerCase();
        } else return " ";
    }
}

