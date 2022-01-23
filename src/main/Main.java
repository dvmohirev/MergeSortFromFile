package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static String dataType;
    private static String outputFileName;
    private static ArrayList<String> inFiles = new ArrayList<>();

    // Осталось написать возрастание/убывание по строкам и убывание по числам

    public static void main(String[] args) { // Считываем аргументы и запускаем сортировку
        int i = 0, j = 0;
        String sortMode;
        switch (args[0]){
            case "-a":
                sortMode = "asc";
                i++;
                if (args[i].equals("-s")) { // Проверить
                    dataType = "string";
                    outputFileName = args[i + 1];
                    i += 2;
                } else if (args[i].equals("-i")) { // вариант работает
                    dataType = "integer";
                    outputFileName = args[i + 1];
                    i += 2;
                } else { // вариант работает
                    System.out.println("Parameters error.");
                    System.exit(0);
                }
                for (j = i; j < args.length; j++) { // вариант работает
                    inFiles.add(args[j]);
                }
                if (inFiles.size() < 1) { // вариант работает
                    System.out.println("No input files found.");
                } else { // вариант работает
                    SortMerge sortMerge = new SortMerge(sortMode, dataType, outputFileName);
                    sortMerge.sortToFile(inFiles);
                }
                break;
            case "-d":
                sortMode = "desc";
                i++;
                if (args[i].equals("-s")) {
                    dataType = "string";
                    outputFileName = args[i + 1];
                    i += 2;
                } else if (args[i].equals("-i")) {
                    dataType = "integer";
                    outputFileName = args[i + 1];
                    i += 2;
                } else { // вариант работает
                    System.out.println("Parameters error.");
                    System.exit(0);
                }
                for (j = i; j < args.length; j++) { // вариант работает
                    inFiles.add(args[j]);
                }
                if (inFiles.size() < 1) { // вариант работает
                    System.out.println("No input files found.");
                } else { // вариант работает
                    SortMerge sortMerge = new SortMerge(sortMode, dataType, outputFileName);
                    sortMerge.sortToFile(inFiles);
                }
            default:
                sortMode = "asc";
                if (args[i].equals("-s")) {
                    dataType = "string";
                    outputFileName = args[i + 1];
                    i += 2;
                } else if (args[i].equals("-i")) { // вариант работает
                    dataType = "integer";
                    outputFileName = args[i + 1];
                    i += 2;
                } else { // вариант работает
                    System.out.println("Parameters error.");
                    System.exit(0);
                }
                for (j = i; j < args.length; j++) { // вариант работает
                    inFiles.add(args[j]);
                }
                if (inFiles.size() < 1) { // вариант работает
                    System.out.println("No input files found.");
                } else { // вариант работает
                    SortMerge sortMerge = new SortMerge(sortMode, dataType, outputFileName);
                    sortMerge.sortToFile(inFiles);
                }
        }
    }
}
