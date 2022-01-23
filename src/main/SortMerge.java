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
    private int[] result = new int[0];
    private Map<String, ArrayList<String>> dataFromFiles = new HashMap<String, ArrayList<String>>();

    SortMerge(String sortMode, String dataType, String outputFileName) { // Сохраняем параметры в глобальных переменных и в зависимости от метода сортировки утанавливаем значение lastInt
        this.outputFileName = outputFileName;
        this.sortMode = sortMode;
        this.dataType = dataType;
    }

    public void sortToFile(ArrayList<String> inFiles){
        if (dataType.equals("integer")) {
            if (sortMode.equals("asc")) {
                //lastInt = Integer.MIN_VALUE;
                for (String file: inFiles) {
                    readFileToList(file);
                }
                for (Map.Entry<String, ArrayList<String>> pair: dataFromFiles.entrySet()) {
                    int[] temp = new int[pair.getValue().size()];
                    for (int i = 0; i < pair.getValue().size(); i++) {
                        temp[i] = Integer.parseInt(pair.getValue().get(i));
                    }
                    result = sortMergeUp(result, temp);
                }

                resultToFile(result, outputFileName);
            } else {
                lastInt = Integer.MAX_VALUE;
            }
        }
    }
    // нужно сделать функцию сортировки файла по строкам по возрастанию - идея такая: по первому символу строки 1 и 2 определяем, какая должна быть выше.

    // нужно сделать функцию сортировки файла по строкам по убыванию - идея такая: по первому символу строки 1 и 2 определяем, какая должна быть выше.

    // Сделать функцию чтения файла так, чтобы мы записывали куда-то содержимое файлов и потом с ним работали.

        /*switch (args[0]){
        case "-a":
            if (args[1].equals("-i")){
                fileOne = args[3]; //запускаем сортировку слиянием по возрастанию
                fileTwo = args[4];
                fileThree = args[5]; //Делаем так, что теперь делаем сортировку, если у нас 3 файла. Для этого используем временный файл.
                list1 = readFileToList(fileOne); //Запускаем считывание с файлов в списки
                list2 = readFileToList(fileTwo);
                list3 = readFileToList(fileThree);
                a1 = new int[list1.size()]; // Создаем массивы для сортировки слиянием
                a2 = new int[list2.size()];
                aTemp = new int[list3.size()];
                for (int i = 0; i < list1.size(); i++) { // не получается преобразовать Лист в список. Делаем через цикл
                    a1[i] = list1.get(i);
                }
                for (int i = 0; i < list2.size(); i++) {
                    a2[i] = list2.get(i);
                }
                for (int i = 0; i < list3.size(); i++) {
                    aTemp[i] = list3.get(i);
                }
                a3 = sortMergeUp(a1, a2); // Запускаем сортировку слиянием для первых двух файлов
                result = sortMergeUp(a3, aTemp); // Запускаем сортировку слиянием для третьего файла и помещаем в новый массив
                fileOut = args[2]; //Проверили, что с двух файлов выводятся в список данные правильно и сортируются
                resultToFile(result, fileOut); //Теперь проверяем, что данные выведутся в файл
            } else if (args[1].equals("-s")){

            }
            break;
        case "-d":
            //запускаем сортировку слиянием по возрастанию
            fileOne = args[3];
            fileTwo = args[4];
            //Делаем так, что теперь делаем сортировку, если у нас 3 файла. Для этого используем временный файл.
            fileThree = args[5];
            //Запускаем считывание с файлов в списки
            list1 = readFileToList(fileOne);
            list2 = readFileToList(fileTwo);
            list3 = readFileToList(fileThree);
            // Создаем массивы для сортировки слиянием
            a1 = new int[list1.size()];
            a2 = new int[list2.size()];
            aTemp = new int[list3.size()];
            // не получается преобразовать Лист в список. Делаем через цикл
            for (int i = 0; i < list1.size(); i++) {
                a1[i] = list1.get(i);
            }
            for (int i = 0; i < list2.size(); i++) {
                a2[i] = list2.get(i);
            }
            for (int i = 0; i < list3.size(); i++) {
                aTemp[i] = list3.get(i);
            }
            // Запускаем сортировку слиянием для первых двух файлов
            a3 = sortMergeDown(a1, a2);
            // Запускаем сортировку слиянием для третьего файла и помещаем в новый массив
            result = sortMergeDown(a3, aTemp);
                //Заполняем новый массив результатом сортировки слиянием и выводим в консоль для проверки
                for (int i = 0; i < result.length; i++) {
                    System.out.println(result[i]);
                }
            //Проверили, что с двух файлов выводятся в список данные правильно и сортируются
            //Теперь проверяем, что данные выведутся в файл
            fileOut = args[2];
            resultToFile(result, fileOut);
            //запускаем сортировку слиянием по убыванию
            break;
        default:
            //запускаем сортировку слиянием по возрастанию
            fileOne = args[2];
            fileTwo = args[3];
            //Делаем так, что теперь делаем сортировку, если у нас 3 файла. Для этого используем временный файл.
            fileThree = args[4];
            //Запускаем считывание с файлов в списки
            list1 = readFileToList(fileOne);
            list2 = readFileToList(fileTwo);
            list3 = readFileToList(fileThree);
            // Создаем массивы для сортировки слиянием
            a1 = new int[list1.size()];
            a2 = new int[list2.size()];
            aTemp = new int[list3.size()];
            // не получается преобразовать Лист в список. Делаем через цикл
            for (int i = 0; i < list1.size(); i++) {
                a1[i] = list1.get(i);
            }
            for (int i = 0; i < list2.size(); i++) {
                a2[i] = list2.get(i);
            }
            for (int i = 0; i < list3.size(); i++) {
                aTemp[i] = list3.get(i);
            }
            // Запускаем сортировку слиянием для первых двух файлов
            a3 = sortMergeUp(a1, a2);
            // Запускаем сортировку слиянием для третьего файла и помещаем в новый массив
            result = sortMergeUp(a3, aTemp);
                //Заполняем новый массив результатом сортировки слиянием и выводим в консоль для проверки
                for (int i = 0; i < result.length; i++) {
                    System.out.println(result[i]);
                }
            //Проверили, что с двух файлов выводятся в список данные правильно и сортируются
            //Теперь проверяем, что данные выведутся в файл
            fileOut = args[1];
            resultToFile(result, fileOut);
            break;
    }*/
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
    public static void resultToFile(int[] mass, String fileOut){
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
    public static int [] sortMergeUp(int[] arrayFirst, int[] arrayTwo) {
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

    //Функция для сортировки слиянием двух массивов по убыванию
    public static int[] sortMergeDown(int[] arrayFirst, int[] arrayTwo){

        int[] arrayC = new int[arrayFirst.length + arrayTwo.length];
        int positionA = 0, positionB = 0;
        return null;
    }

}
