package ru.vsu.cs.novichikhin;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

public class ConsoleMain {
    public static class CmdParams {
        String inputFile;
        String outputFile;
        boolean error;
        boolean help;

    }

    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();

        if (args.length == 1 || args.length == 2 || args.length == 4) {

            if (args.length == 1) {
                if (args[0].equals("--help")) {
                    params.help = true;
                } else {
                    returnError(params);
                }
            } else {
                if (args.length == 2 && args[0].equals("-i")) {
                    params.inputFile = args[1];
                    params.outputFile = null;
                } else {

                    if (args[0].equals("-i") && args[2].equals("-o")) {
                        params.inputFile = args[1];
                        params.outputFile = args[3];
                    } else {
                        returnError(params);
                    }
                }
            }
        } else {
            returnError(params);
        }
        return params;
    }

    private static void returnError(CmdParams params) {
        params.help = true;
        params.error = true;
    }

    public static void main(String[] args) {
        CmdParams params = parseArgs(args);

        if (params.help) {
            printUsage(params);
        } else {
            makeListWithRightWords(params);
        }
    }

    private static void printUsage(CmdParams params) {
        PrintStream out = params.error ? System.err : System.out;
        out.println("Usage:");
        out.println("  <cmd> -i <input-file> -o <output-file>");
        out.println("  <cmd> -i <input-file>");
        out.println("  <cmd> --help");
        out.println(" <cmd> --window");
        System.exit(params.error ? 1 : 0);
    }

    private static void makeListWithRightWords(CmdParams params) {
        List<String> text = null;
        try {
            text = ListUtils.readLinesFromFile(params.inputFile);
            assert (text != null);
        } catch (Exception e) {
            printError(1);
        }

        try {
            WordSelection selection = new WordSelection();
            List<String> rightWords = selection.findRightWords(text);
            if (rightWords == null) {
                printError(2);
            }

            printResult(params, rightWords);
        } catch (Exception e) {
            printError(2);
        }
    }

    private static void printResult(CmdParams params, List<String> list) throws FileNotFoundException {
        if (params.outputFile != null) {
            ListUtils.writeListToFile(list, params.outputFile);
        } else {
            ListUtils.writeListToConsole(list);
        }
    }

    private static void printError(int number) {
        switch (number) {
            case 1 -> System.err.print("Данные в файле отсутствуют");
            case 2 -> System.out.print("В файле нет слов, в которых количество гласных букв больше количества " +
                    "согласных. Словом считается непрерывная последовательность символов (строчных и прописных) А-Я.");
        }
        System.exit(1);
    }
}
