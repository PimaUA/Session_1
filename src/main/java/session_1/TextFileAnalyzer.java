package session_1;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.function.Failable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.io.File;
import java.nio.charset.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TextFileAnalyzer {
    private static final Logger LOGGER = LogManager.getLogger(TextFileAnalyzer.class);

    public static void main(String[] args) {
        TextFileAnalyzer textFileAnalyzer = new TextFileAnalyzer();
        textFileAnalyzer.countUniqueWords();
    }

    public void countUniqueWords() {
        try {
            String[] words = readFileAndConvertToLowerCase().split("[\\p{Punct}\\s]+");
            List<String> list = Arrays.asList(words);
            long result = Failable.stream(list).filter(i -> Collections.frequency(list, i) == 1).stream().count();
            System.out.println(result);
        } catch (IOException e) {
            LOGGER.info("File not found!");
        }
    }

    public String readFileAndConvertToLowerCase() throws IOException {
        String finalLine = null;
        File file = new File("src/main/resources/text2.txt");
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

