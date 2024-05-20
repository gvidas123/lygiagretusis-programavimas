package multithreadedquick;
import java.util.Random;

public class Main {


    static int numberOfThreads = 4; // 0 = 1 thread, 1 = 2 threads, 2 = 4, 3 = 8 threads and so on up to a maximum of 5 where the max is 32
    public static void main(String[] args){
        int[] a = generateRandomArray(40000000);
        int[] a2 = a.clone();
        int[] a3 = a.clone();
        int[] a4 = a.clone();

        System.out.println("\nMultiple threads:\n");
        long startTime2 = System.currentTimeMillis();
        MultiThreadedQuickSort.parallelQuickSort(a2,5 - numberOfThreads);
        long endTime2 = System.currentTimeMillis();
        long duration2 = endTime2 - startTime2;
        //printArray(a2);
        for (int i = 0; i < a2.length; i++) {
            if (i < a.length - 1) {
                if (a2[i] > a2[i+1]) {
                    System.out.println(i + " " + a2[i] + "  " + i + " " + a2[i+1]);
                }
            }

        }
        System.out.println("the time it took for " + Math.pow(2,numberOfThreads) + " threaded quicksort:" + duration2 );
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000); // Generate random integers up to 1 million
        }
        return array;
    }


}
