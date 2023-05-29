package hw02;

import java.util.Random;

public class task01 {
    public static void main(String[] args) {
        int[] array = randomArray(10, 1, 20);
        System.out.println("Первоначальный массив: ");
        printArray(array);
        heapsort(array);
        System.out.println("Отсортированный массив: ");
        printArray(array);
    }

    // сортиреут массив
    private static void heapsort(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            transformToHeap(array, n, i);
        }

        for (int i=n-1; i>=0; i--)
        {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            transformToHeap(array, i, 0);
        }
    }

    // превращает массив в вдоичную кучу
    private static void transformToHeap(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest])
            largest = left;

        if (right < n && array[right] > array[largest])
            largest = right;

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            transformToHeap(array, n, largest);
        }
    }

    // создает массив случайтых чисел между min и max, длиной len
    public static int[] randomArray(int len, int min, int max) {
        Random rand = new Random();
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = rand.nextInt(min, max + 1);
        }
        return result;
    }

    // Вывод массива в консоль
    public static void printArray(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
