package session_1;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.function.Failable;

import java.io.*;
import java.io.File;
import java.nio.charset.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TextFileAnalyzer {

    public static void main(String[] args) throws IOException {
        TextFileAnalyzer textFileAnalyzer = new TextFileAnalyzer();
        textFileAnalyzer.countUniqueWords();
    }

    public void countUniqueWords() throws IOException {
        String[] words = readFileAndConvertToLowerCase().split("[\\p{Punct}\\s]+");
        List<String> list = Arrays.asList(words);
        long result = Failable.stream(list).filter(i -> Collections.frequency(list, i) == 1).stream().count();
        System.out.println(result);
    }

    public String readFileAndConvertToLowerCase() throws IOException {
        String finalLowerCaseLine = null;
        File file = new File("src/main/resources/text.txt");
        try (LineIterator iterator = FileUtils.lineIterator(file, StandardCharsets.UTF_8.name())) {
            StringBuilder stringBuilder = new StringBuilder();
            while (iterator.hasNext()) {
                String line = iterator.nextLine() + " ";
                finalLowerCaseLine = stringBuilder.append(line).toString().toLowerCase();
            }
        }
        return finalLowerCaseLine;
    }
}

