package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SortMerge {
    private final String sortMode;
    private final String dataType;
    //private String lastLine = null;
    private int lastInt;
    private final String outputFileName;
    private int[] resultInt = new int[0];
    private String[] resultStr = new String[0];
    private Map<String, ArrayList<String>> dataFromFiles = new HashMap<String, ArrayList<String>>();

    SortMerge(String sortMode, String dataType, String outputFileName) { // Сохраняем параметры в глобальных переменных и в зависимости от метода сортировки утанавливаем значение lastInt
        this.outputFileName = outputFileName;
        this.sortMode = sortMode;
        this.dataType = dataType;
    }

    public void sortToFile(ArrayList<String> inFiles){
        if (dataType.equals("integer")) {
            if (sortMode.equals("asc")) {
                for (String file: inFiles) {
                    readFileToList(file);
                }
                for (Map.Entry<String, ArrayList<String>> pair: dataFromFiles.entrySet()) {
                    int[] temp = new int[pair.getValue().size()];
                    for (int i = 0; i < pair.getValue().size(); i++) {
                        temp[i] = Integer.parseInt(pair.getValue().get(i));
                    }
                    resultInt = sortMergeUpInt(resultInt, temp);
                }

                resultToFileInt(resultInt, outputFileName);
            } else if (sortMode.equals("desc")){
                for (String file: inFiles) {
                    readFileToList(file);
                }
                for (Map.Entry<String, ArrayList<String>> pair: dataFromFiles.entrySet()) {
                    int[] temp = new int[pair.getValue().size()];
                    for (int i = 0; i < pair.getValue().size(); i++) {
                        temp[i] = Integer.parseInt(pair.getValue().get(i));
                    }
                    resultInt = sortMergeDownInt(resultInt, temp);
                }

                resultToFileInt(resultInt, outputFileName);
            }
        } else if (dataType.equals("string")){
            if (sortMode.equals("asc")) {
                for (String file: inFiles) {
                    readFileToList(file);
                }
                for (Map.Entry<String, ArrayList<String>> pair: dataFromFiles.entrySet()) {
                    String[] temp = new String[pair.getValue().size()];
                    for (int i = 0; i < pair.getValue().size(); i++) {
                        temp[i] = pair.getValue().get(i);
                    }
                    resultStr = sortMergeUpStr(resultStr, temp);
                }

                resultToFileStr(resultStr, outputFileName);
            } else if (sortMode.equals("desc")){
                for (String file: inFiles) {
                    readFileToList(file);
                }
                for (Map.Entry<String, ArrayList<String>> pair: dataFromFiles.entrySet()) {
                    String[] temp = new String[pair.getValue().size()];
                    for (int i = 0; i < pair.getValue().size(); i++) {
                        temp[i] = pair.getValue().get(i);
                    }
                    resultStr = sortMergeDownStr(resultStr, temp);
                }

                resultToFileStr(resultStr, outputFileName);
            }
        }
    }
    // нужно сделать функцию сортировки файла по строкам по возрастанию - идея такая: по первому символу строки 1 и 2 определяем, какая должна быть выше.

    // нужно сделать функцию сортировки файла по строкам по убыванию - идея такая: по первому символу строки 1 и 2 определяем, какая должна быть выше.

    // Сделать функцию чтения файла так, чтобы мы записывали куда-то содержимое файлов и потом с ним работали.

    public Map<String, ArrayList<String>> readFileToList(String file){
        ArrayList<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            int i = 0;
            while(sc.hasNext()){
                list.add(sc.nextLine());
            }
            dataFromFiles.put(file, list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataFromFiles;
    }

    //Функция вывода отсортированного массива в файл
    public static void resultToFileInt(int[] mass, String fileOut){
        try {
            FileWriter fw = new FileWriter(fileOut);
            //fw.write("Начинаем запись:");
            for (int i = 0; i < mass.length; i++) {
                fw.write(mass[i] + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Дублирую функцию по записи в файл для String
    public static void resultToFileStr(String[] mass, String fileOut){
        try {
            FileWriter fw = new FileWriter(fileOut);
            //fw.write("Начинаем запись:");
            for (int i = 0; i < mass.length; i++) {
                fw.write(mass[i] + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Функция для сортировки слиянием двух массивов по возрастанию
    public static int [] sortMergeUpInt(int[] arrayFirst, int[] arrayTwo) {
        int[] arrayC = new int[arrayFirst.length + arrayTwo.length];
        int i=0, j=0;
        for (int k=0; k < arrayC.length; k++) {

            if (i > arrayFirst.length-1) {
                int a = arrayTwo[j];
                arrayC[k] = a;
                j++;
            }
            else if (j > arrayTwo.length-1) {
                int a = arrayFirst[i];
                arrayC[k] = a;
                i++;
            }
            else if (arrayFirst[i] < arrayTwo[j]) {
                int a = arrayFirst[i];
                arrayC[k] = a;
                i++;
            }
            else {
                int b = arrayTwo[j];
                arrayC[k] = b;
                j++;
            }
        }
        return arrayC;
    }

    //Функция для сортировки слиянием двух массивов по убыванию - НУЖНО ДОРАБОТАТЬ
    public static int[] sortMergeDownInt(int[] arrayFirst, int[] arrayTwo){

        int[] arrayC = new int[arrayFirst.length + arrayTwo.length];
        int i=arrayFirst.length-1, j=arrayTwo.length-1;
        for (int k=0; k < arrayC.length; k++) {

            if (i < arrayFirst.length-1) {
                int a = arrayTwo[j];
                arrayC[k] = a;
                j--;
            }
            else if (j < arrayTwo.length-1) {
                int a = arrayFirst[i];
                arrayC[k] = a;
                i--;
            }
            else if (arrayFirst[i] > arrayTwo[j]) {
                int a = arrayFirst[i];
                arrayC[k] = a;
                i--;
            }
            else {
                int b = arrayTwo[j];
                arrayC[k] = b;
                j--;
            }
        }
        return arrayC;
    }

    public static String [] sortMergeUpStr(String[] arrayFirst, String[] arrayTwo) {
        String[] arrayC = new String[arrayFirst.length + arrayTwo.length];
        int i=0, j=0;
        return arrayC;
    }

    //Функция для сортировки слиянием двух массивов по убыванию
    public static String[] sortMergeDownStr(String[] arrayFirst, String[] arrayTwo){

        String[] arrayC = new String[arrayFirst.length + arrayTwo.length];
        int i=0, j=0;
        return arrayC;
    }

}
