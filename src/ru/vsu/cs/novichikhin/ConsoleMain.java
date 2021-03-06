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

        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }

            params.inputFile = args[0];
            if (args.length > 1) {
                params.outputFile = args[1];
                return params;
            }
        } else {
            params.help = true;
            params.error = true;
        }

        return params;
    }

    public static void main(String[] args) throws FileNotFoundException {
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
        out.println("  <cmd> args <input-file> (<output-file>)");
        out.println("  <cmd> --help");
        System.exit(params.error ? 1 : 0);
    }

    private static void makeListWithRightWords(CmdParams params) throws FileNotFoundException {
        List<String> text = null;

        try {
            text = ListUtils.readLinesFromFile(params.inputFile);
            assert (text != null);
        } catch (Exception e) {
            printError();
        }

        WordSelection selection = new WordSelection();
        List<String> rightWords = selection.findRightWords(text);

        printResult(params, rightWords);
    }

    private static void printResult(CmdParams params, List<String> list) throws FileNotFoundException {
        if (params.outputFile != null) {
            ListUtils.writeListToFile(list, params.outputFile);
        } else {
            ListUtils.writeListToConsole(list);
        }
    }

    private static void printError() {
        System.err.print("???????????? ?? ?????????? ??????????????????????");
        System.exit(1);
    }
}
