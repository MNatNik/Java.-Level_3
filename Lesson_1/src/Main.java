
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        taskOne();
        taskTwo();
        taskThree();
    }
    public static void taskOne() {
        System.out.println("Задание №1");
        Integer[] iArray = {1, 2, 3, 4};
        System.out.println("Первоначальный массив: " + Arrays.toString(iArray));
        List<Integer> list1 = Arrays.asList(iArray);
        listSwap1stAnd2nd(list1);
        System.out.println("Массив, в котором первый и второй элемент поменялись местами: " + Arrays.toString(list1.toArray()));

        String[] sArray = {"one", "two", "three"};
        System.out.println("Первоначальный массив: " + Arrays.toString(sArray));
        List<String> list2 = Arrays.asList(sArray);
        listSwap1stAnd2nd(list2);
        System.out.println("Массив, в котором первый и второй элемент поменялись местами: " + Arrays.toString(list2.toArray()));
    }
    public static <T> List<T> listSwap1stAnd2nd(List<T> list) {
        List<T> tempList = list;
        T firstElement = tempList.get(0);
        T secondElement = tempList.get(1);
        tempList.set(0, secondElement);
        tempList.set(1, firstElement);
        return tempList;
    }
    public static void taskTwo() {
        System.out.println("\n\nЗадание №2");
        String[] sArray = {"one", "two", "three"};
        System.out.println("Результат преобразования массива в ArrayList: " + arrayToArrayList(sArray));
    }
    private static <T> ArrayList arrayToArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
    public static void taskThree() {
        System.out.println("\n\nЗадание №3");
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();

        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        ArrayList fruits = new ArrayList<>();
        fruits.add(apple1);
        fruits.add(apple2);
        fruits.add(apple3);
        fruits.add(orange1);
        fruits.add(orange2);
        ArrayList boxWithApple = new ArrayList<>();
        ArrayList boxWithOranges = new ArrayList<>();
        for (int i = 0; i < fruits.size(); i++ ){
            if (fruits.get(i) instanceof Apple) {
                boxWithApple.add(fruits.get(i));
            } else {
                boxWithOranges.add(fruits.get(i));
            }
        }
        System.out.println("Кол-во яблок в коробке с яблоками: " + boxWithApple.size());
        System.out.println("Кол-во апельсинов в коробке с апельсинами: " + boxWithOranges.size());
    }
}
