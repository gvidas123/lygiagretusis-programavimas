import java.util.Random;
public class Main {



    public static void main(String[] args){
        int[] a = generateRandomArray(1000);
        int [] array;
        long startTime = System.currentTimeMillis();
        array = QuickSort.start(a);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("the time it took for single threaded quicksort:" + duration );


        System.out.println("\nMultiple threads:\n");

        startTime = System.currentTimeMillis();
        MultiThreadedQuickSort.parallelQuicksort(array,0,array.length -1);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;


        System.out.println("the time it took for multi threaded quicksort:" + duration );
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
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
