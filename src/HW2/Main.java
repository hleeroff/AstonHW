package HW2;

import java.util.Comparator;

/**
 * Класс Main содержит входные данные и проверку для использования класса MyArrayList.
 */
public class Main {
    public static void main(String[] args) {
        // Создание списка целых чисел
        MyArrayList<Integer> list = new MyArrayList<>();
        // Добавление элементов
        list.add(1);
        list.add(9);
        list.add(2);
        list.add(7);
        list.add(4);

        System.out.println("Get list: " + list);

        // Добавление по индексу
        list.add(1, 3);
        list.add(3, 8);
        list.add(2, 5);
        System.out.println("Added elements by index: " + list);

        // Получить по индексу
        System.out.println("Element by index 3: " + list.get(3));

        // Удалить по индексу
        System.out.println("Removed element by index 2: " + list.remove(2));
        System.out.println("Get list: " + list);

        // Очистить список
        list.clear();
        System.out.println("Get list: " + list);

        // Отстортировать
        list.add(1);
        list.add(9);
        list.add(2);
        list.add(7);
        list.sort(Comparator.naturalOrder());
        System.out.println("Sorted list: " + list);

        // Тестирование с большими входными данными
        MyArrayList<Integer> largeList = new MyArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeList.add((int) (Math.random() * 100000));
        }
        largeList.sort(Comparator.naturalOrder());
        System.out.println("Get first element after sorting: " + largeList.get(0));
        System.out.println("Get last element after sorting: " + largeList.get(largeList.size() - 1));
    }
}
