package HW2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Класс MyArrayList представляет собой динамический массив, реализующий интерфейс ListOperations.
 * Позволяет: добавлять, удалять, получать и сортировать элементы.
 *
 * MyArrayList автоматически увеличивает свою емкость при необходимости и обеспечивает работу
 * с различными типами данных.
 *
 * @param <T> тип элементов хранимых в списке
 */
public class MyArrayList<T> implements ListOperations<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;

    /**
     * Конструктор по умолчанию создающий пустой список с дефолтной емкостью.
     */
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент для добавления
     */
    @Override
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * Добавляет элемент по указанному индексу.
     *
     * @param index   индекс по которому будет добавлен элемент
     * @param element элемент для добавления
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона
     */
    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Получает элемент по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента для удаления
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона
     */
    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T oldValue = (T) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // Clear to let GC do its work
        return oldValue;
    }

    /**
     * Очищает весь список.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Сортирует список с использованием предоставленного компаратора.
     *
     * @param comparator компаратор для сравнения элементов
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Реализация алгоритма QuickSort
     */
    private void quickSort(int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<? super T> comparator) {
        T pivot = get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(get(j), pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    @SuppressWarnings("unchecked")
    private void swap(int i, int j) {
        T temp = (T) elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * Увеличение емкости массива при необходимости.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * 2);
        }
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Возвращает строковое представление списка.
     *
     * @return строковое представление списка
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}