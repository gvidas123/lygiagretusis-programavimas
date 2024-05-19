import java.util.Random;
public class Main {



    public static void main(String[] args){
        int[] a = generateRandomArray(1000000);
        int[] a2 = a.clone();
        int[] a3 = a.clone();
        int[] a4 = a.clone();
        //printArray(a2);

        long startTime = System.currentTimeMillis();
        QuickSort.start(a);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        for (int i = 0; i < a.length; i++) {
            if (i < a.length - 1) {
                if (a[i] > a[i+1]) {
                System.out.println("unfortunate");
                }
            }

        }
        //printArray(a);
        System.out.println("the time it took for single threaded quicksort:" + duration );

        System.out.println("\nMultiple threads:\n");
        long startTime2 = System.currentTimeMillis();
        MultiThreadedQuickSort.parallelQuickSort(a2,4);
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
        System.out.println("the time it took for 2 threaded quicksort:" + duration2 );
        System.out.println("\nMultiple threads:\n");
        long startTime3 = System.currentTimeMillis();
        MultiThreadedQuickSort.parallelQuickSort(a3,3);
        long endTime3 = System.currentTimeMillis();
        long duration3 = endTime3 - startTime3;
        //printArray(a2);
        for (int i = 0; i < a3.length; i++) {
            if (i < a.length - 1) {
                if (a3[i] > a3[i+1]) {
                    System.out.println(i + " " + a3[i] + "  " + i + " " + a3[i+1]);
                }
            }

        }
        System.out.println("the time it took for 4 threaded quicksort:" + duration3 );
        System.out.println("\nMultiple threads:\n");
        long startTime4 = System.currentTimeMillis();
        MultiThreadedQuickSort.parallelQuickSort(a4,2);
        long endTime4 = System.currentTimeMillis();
        long duration4 = endTime4 - startTime4;
        //printArray(a2);
        for (int i = 0; i < a4.length; i++) {
            if (i < a.length - 1) {
                if (a4[i] > a4[i+1]) {
                    System.out.println(i + " " + a4[i] + "  " + i + " " + a4[i+1]);
                }
            }

        }
        System.out.println("the time it took for 8 threaded quicksort:" + duration4 );



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
