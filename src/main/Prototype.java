/*package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static String dataType;
    private static String outputFileName;
    private static ArrayList<String> inFiles = new ArrayList<>();

    public static void main(String[] args) {
        //Создаем списки для записи данных из файлов
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        String fileOut;
        String fileOne;
        String fileTwo;
        String fileThree;
        int[] a1;
        int[] a3;
        int[] a2;
        int[] aTemp;
        int[] result;

        String[] names = new String[] {"Roman","Anna", "Petr", "Maria"};

        Arrays.sort(names);
        for(int i = 0; i <  names.length; i++) {
            System.out.print(names[i] + "  ");
        }

        switch (args[0]){
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
        }

    }


    //Функция чтения файла и записи в Лист
    public static ArrayList<Integer> readFileToList(String file){
        ArrayList<Integer> listFromFile = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            while(sc.hasNext()){
                listFromFile.add(sc.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listFromFile;
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
        int positionA = 0, positionB = 0;

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

        for (int i = 0; i < arrayC.length; i++) {
            if (positionA == arrayFirst.length) {
                arrayC[i] = arrayTwo[i - positionB];
                positionB++;
            } else if (positionB == arrayTwo.length) {
                arrayC[i] = arrayFirst[i - positionA];
                positionA++;
            } else if (arrayFirst[i - positionA] < arrayTwo[i - positionB]) {
                arrayC[i] = arrayFirst[i - positionA];
                positionB++;
            } else {
                arrayC[i] = arrayTwo[i - positionB];
                positionA++;
            }
        }
        return arrayC;
    }
}
*/