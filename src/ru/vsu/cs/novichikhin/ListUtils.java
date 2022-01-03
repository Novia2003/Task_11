package ru.vsu.cs.novichikhin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ListUtils {

    public static List<String> readLinesFromFile(String fileName) throws FileNotFoundException {
        List<String> lines;

        try (Scanner scanner = new Scanner(new File(fileName))) {
            lines = new ArrayList<>();
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        }

        if (lines.get(0).equals("")) {
            return null;
        }

        return lines;
    }

    public static List<String> readWordsFromFile(String fileName) throws FileNotFoundException {
        List<String> words = new ArrayList<>();

        List<String> lines = readLinesFromFile(fileName);
        assert lines != null;

        for (String line : lines) {
            String[] wordsInLine = line.split(", ");
            words.addAll(Arrays.asList(wordsInLine));
        }

        return words;
    }

    public static void writeListToConsole(List<String> lists) {
        for (int i = 0; i < lists.size(); i++) {
            System.out.print(lists.get(i));

            if (i < lists.size() - 1) {
                System.out.print(", ");
            }
        }
    }

    public static void writeListToFile(List<String> lists, String fileName) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(fileName);

        for (int i = 0; i < lists.size(); i++) {
            out.print(lists.get(i));

            if (i < lists.size() - 1) {
                out.print(", ");
            }
        }

        out.close();
    }
}