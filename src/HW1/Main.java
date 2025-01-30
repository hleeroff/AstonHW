package HW1;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    /* Для задачи №1
    Перевернуть строку и вывести на консоль
    String string = "I love Java"; */
    public static void turnString(String string) {
        System.out.println("Reversed loop: " + reverseWithLoop(string));
        System.out.println("Reversed methods: " + reverseWithStringMethods(string));

    }

    // Решение задачи №1 через циклы
    public static String reverseWithLoop(String string) {
        String reversed = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            reversed += string.charAt(i);
        }
        return reversed;
    }

    // Решение задачи №1 через методы String (StringBuilder)
    public static String reverseWithStringMethods(String string) {
        return new StringBuilder(string).reverse().toString();
    }

    /* Для задачи №2
    int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
    Удалить дубликаты из массива и вывести в консоль */
    public static void getDistinctNumbers(int[] ints) {
        System.out.println("Distinct numbers loop: " + Arrays.toString(removeDuplicatesWithLoop(ints)));
        System.out.println("Distinct numbers collections: " + Arrays.toString(removeDuplicatesWithCollections(ints)));
    }

    // Решение задачи №2 через циклы
    public static int[] removeDuplicatesWithLoop(int[] ints) {
        if (ints == null || ints.length == 0) {
            return ints;
        }
        // Отсортировать массив для удаления дубликатов
        Arrays.sort(ints);
        int uniqueCount = 1;
        for (int i = 1; i < ints.length; i++) {
            if (ints[i] != ints[i - 1]) {
                uniqueCount++;
            }
        }
        // Создаем новый массив для уникальных элементов
        int[] result = new int[uniqueCount];
        result[0] = ints[0];
        int index = 1;
        // Заполняем новый массив уникальными элементами
        for (int i = 1; i < ints.length; i++) {
            if (ints[i] != ints[i - 1]) {
                result[index++] = ints[i];
            }
        }
        return result;
    }

    // Решение задачи №2 через коллекцию HashSet
    public static int[] removeDuplicatesWithCollections(int[] ints) {
        if (ints == null || ints.length == 0) {
            return ints;
        }
        // Используем HashSet для автоматического удаления дубликатов
        HashSet<Integer> set = new HashSet<>();
        for (int num : ints) {
            set.add(num);
        }
        // Преобразуем HashSet обратно в массив
        int[] result = new int[set.size()];
        int index = 0;
        for (int num : set) {
            result[index++] = num;
        }
        return result;
    }

    /* Для задачи №3
    Дан массив, заполненный уникальными значениями типа int.
    int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
    Необходимо найти элемент, который меньше максимума, но больше всех остальных. */
    public static Integer findSecondMaxElement(int[] arr) {
        if (arr == null || arr.length < 2) {
            // Если массив маленький вернуть null
            return null;
        }
        // Решение через циклы
        int secondMaxWithLoop = findSecondMaxWithLoop(arr);
        System.out.println("Second max loop: " + secondMaxWithLoop);
        // Решение через сортировку
        int secondMaxWithSorting = findSecondMaxWithSorting(arr);
        System.out.println("Second max sorting: " + secondMaxWithSorting);
        // Вернуть результат одного из методов
        return secondMaxWithLoop;
    }

    // Решение задачи №3 через циклы
    public static int findSecondMaxWithLoop(int[] arr) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                // Обновляем второй max
                secondMax = max;
                // Обновляем max
                max = num;
            } else if (num > secondMax && num < max) {
                // Обновляем второй max
                secondMax = num;
            }
        }
        return secondMax;
    }

    // Решение задачи №3 через сортировку
    public static int findSecondMaxWithSorting(int[] arr) {
        // Сортировка по возрастанию
        Arrays.sort(arr);
        // Второй по величине элемент на предпоследней позиции
        return arr[arr.length - 2];
    }

    /* Для задачи №4
    Найти длину последнего слова в строке. В строке только буквы и пробелы.
    "Hello world" - 5
    "    fly me    to the moon    " - 4 */
    public static Integer lengthOfLastWord(String string) {
        if (string == null || string.trim().isEmpty()) {
            // Если строка пустая/состоит только из пробелов
            return null;
        }
        // Решение через циклы
        int lengthWithLoop = lengthOfLastWordWithLoop(string);
        System.out.println("Length for last word loop: " + lengthWithLoop);
        // Решение через методы String
        int lengthWithStringMethods = lengthOfLastWordWithStringMethods(string);
        System.out.println("Length for last word string methods: " + lengthWithStringMethods);
        // Вернуть результат одного из методов
        return lengthWithLoop;
    }

    // Решение задачи №4 через циклы
    public static int lengthOfLastWordWithLoop(String string) {
        // Убатьп пробелы в начале/конце
        string = string.trim();
        int length = 0;
        // Проходим по строке с конца
        for (int i = string.length() - 1; i >= 0; i--) {
            if (string.charAt(i) != ' ') {
                // Увеличичить длину если символ не пробел
                length++;
            } else if (length > 0) {
                // Выйтик из цикла если найден пробел и длина больше 0
                break;
            }
        }
        return length;
    }

    // Решение задачт №4 через методы String
    public static int lengthOfLastWordWithStringMethods(String string) {
        // Убирать пробелы в начале и конце
        string = string.trim();
        // Найти индекс последнего пробела
        int lastSpaceIndex = string.lastIndexOf(' ');
        // Если пробел не найден то вся строка 1 слово
        if (lastSpaceIndex == -1) {
            return string.length();
        }
        // Вернуть длину подстроки от последнего пробела до конца строки
        return string.substring(lastSpaceIndex + 1).length();
    }

    /* Для задачи №5
    Определить, что строка является палиндромом
    Сложность по памяти O(1), не создавать новые String, StringBuilder
    Примеры: abc - false, 112233 - false, aba - true, 112211 - true */
    public static boolean isPalindrome(String string) {
        if (string == null || string.isEmpty()) {
            // Если строка пустая или null то вернуть false
            return false;
        }
        // Решение через цикл
        boolean isPalindromeWithLoop = isPalindromeWithLoop(string);
        System.out.println("Is palindrome loop? " + isPalindromeWithLoop);
        // Решение через рекурсию
        boolean isPalindromeWithRecursion = isPalindromeWithRecursion(string, 0, string.length() - 1);
        System.out.println("Is palindrome recursion? " + isPalindromeWithRecursion);
        // Вернуть результат одного из методов
        return isPalindromeWithLoop;
    }

    // Решение задачи №5 через цикл
    public static boolean isPalindromeWithLoop(String string) {
        int left = 0;
        int right = string.length() - 1;
        while (left < right) {
            if (string.charAt(left) != string.charAt(right)) {
                // Если символы не совпадают то не палиндром
                return false;
            }
            left++;
            right--;
        }
        // Если все символы совпали то палиндром
        return true;
    }

    // Решение задачи №5 через рекурсию
    public static boolean isPalindromeWithRecursion(String string, int left, int right) {
        if (left >= right) {
            // Базовый случай строка пройдена до середины
            return true;
        }
        if (string.charAt(left) != string.charAt(right)) {
            // Если символы не совпадают то строка не палиндром
            return false;
        }
        // Рекурсивный вызов для следующей пары символов
        return isPalindromeWithRecursion(string, left + 1, right - 1);
    }

    public static void main(String[] args) {
        // Для задачи №1
        System.out.println("Task № 1");
        turnString("I love Java");

        // Для задачи №2
        System.out.println("Task № 2");
        int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
        getDistinctNumbers(ints);

        // Для задачи №3
        System.out.println("Task № 3");
        int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
        Integer secondMax = findSecondMaxElement(arr);
        if (secondMax != null) {
            System.out.println("Second max element: " + secondMax);
        } else {
            System.out.println("Array is small!");
        }

        // Для задачи №4
        System.out.println("Task № 4");
        String str1 = "Hello world";
        String str2 = "    fly me    to the moon    ";
        System.out.println("Length for last word in '" + str1 + "': " + lengthOfLastWord(str1));
        System.out.println("Length for last word in '" + str2 + "': " + lengthOfLastWord(str2));

        // Для задачи №5
        System.out.println("Task № 5");
        String string1 = "abc";
        String string2 = "112233";
        String string3 = "aba";
        String string4 = "112211";
        System.out.println("Is '" + string1 + "' palindrome? " + isPalindrome(string1));
        System.out.println("Is '" + string2 + "' palindrome? " + isPalindrome(string2));
        System.out.println("Is '" + string3 + "' palindrome? " + isPalindrome(string3));
        System.out.println("Is '" + string4 + "' palindrome? " + isPalindrome(string4));
    }
}