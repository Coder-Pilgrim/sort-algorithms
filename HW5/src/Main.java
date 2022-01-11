import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static double[] numbers = new double[259822];
    public static void main(String[] args) throws FileNotFoundException {

        double[] read5 = readQueries();
        quickSort(read5);
        double[] read2 = readQueries();
        mergeSort(read2);
        double[] read3 = readQueries();
        shellSort(read3);
        double[] read1 = readQueries();
        insertionSort(read1);
        double[] read4 = readQueries();
        selectionSort(read4);

    }

    public static double[] readQueries() throws FileNotFoundException {
        int j = -1;
            File myFile = new File("C:\\Users\\Ismet\\IdeaProjects\\Comp203\\HW5\\Query.txt");
            Scanner networks = new Scanner(myFile).useLocale(Locale.US);

            while (networks.hasNextLine()) {
                String line = networks.nextLine();

                double oneLine = Double.parseDouble(line);
                j++;
                numbers[j] = oneLine;
            }
            return numbers;
        }


    public static void insertionSort(double[] numbers){
        long startTime = System.currentTimeMillis();

        for (int i = 1; i < numbers.length; i++){
            double newElement = numbers[i];

            int j;

            for (j = i; j > 0 && numbers[j-1] > newElement; j--){
                numbers[j] = numbers[j - 1];
            }

            numbers[j] = newElement;
        }
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;

        System.out.println("Time taken for insertion sort for array size " + numbers.length + ": " + elapsed + " ms");
    }

    public static void shellSort(double numbers[]){
        long startTime = System.currentTimeMillis();

        for (int gap = numbers.length / 2; gap > 0; gap /= 2){
            for (int i = gap; i < numbers.length; i++){
                double newElement = numbers[i];

                int j = i;

                while (j >= gap && numbers[j - gap] > newElement){
                    numbers[j] = numbers[j - gap];
                    j -= gap;
                }

                numbers[j] = newElement;
            }
        }
        /*
        for (int i = 0; i < numbers.length; i++){
            System.out.println(numbers[i]);
        }
        */
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;

        System.out.println("Time taken for shell sort for array size " + numbers.length + ": " + elapsed + " ms");
    }

    public static void mergeSort(double[] numbers){
        long startTime = System.currentTimeMillis();
        mergeSort(numbers, 0, numbers.length);

        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;

        System.out.println("Time taken for merge sort for array size " + numbers.length + ": " + elapsed + " ms");
    }

    public static void mergeSort(double[] numbers, int start, int end){
        if (end - start < 2) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(numbers, start, mid);
        mergeSort(numbers, mid, end);
        merge(numbers, start, mid, end);
    }
    public static void merge(double[] numbers, int start, int mid, int end) {
        if (numbers[mid - 1] <= numbers[mid]) {
            return;
        }

        int i = start;
        int j = mid;
        int tempIndex = 0;

        double[] temp = new double[end - start];
        while (i < mid && j < end) {
            temp[tempIndex++] = numbers[i] <= numbers[j] ? numbers[i++] : numbers[j++];
        }

        System.arraycopy(numbers, i, numbers, start + tempIndex, mid - i);
        System.arraycopy(temp, 0, numbers, start, tempIndex);
    }

    public static void selectionSort(double numbers[]){
        long startTime = System.currentTimeMillis();

        for (int i = numbers.length - 1; i > 0; i--){
            int largest = 0;
            for (int j = 1; j <= i; j++){
                if (numbers[j] > numbers[largest]){
                    largest = j;
                }
            }
            swap(numbers , largest, i);
        }

        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;

        System.out.println("Time taken for selection sort for array size " + numbers.length + ": " + elapsed + " ms");
    }

    public static void swap(double [] array, int i, int j){
        if (i == j){
            return;
        }

        double temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int partition(double[] input, int start, int end) {
        double pivot = input[start];
        int i = start;
        int j = end;

        while (i < j) {
            while (i < j && input[--j] >= pivot);
            if (i < j) {
                input[i] = input[j];
            }
            while (i < j && input[++i] <= pivot);
            if (i < j) {
                input[j] = input[i];
            }
        }
        input[j] = pivot;
        return j;

    }

    public static void quick(double[] numbers, int start, int end){
        if (end - start < 2) {
            return;
        }

        int pivotIndex = partition(numbers, start, end);

        quick(numbers, start, pivotIndex);
        quick(numbers, pivotIndex + 1, end);
    }

    public static void quickSort(double numbers[]){
        long startTime = System.currentTimeMillis();
        quick(numbers, 0, numbers.length);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;

        System.out.println("Time taken for quick sort for array size " + numbers.length + ": " + elapsed + " ms");
    }
}
