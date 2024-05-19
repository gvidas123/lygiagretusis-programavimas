import java.util.Random;

public class MultiThreadedQuickSort {

    private static class QuickSortThread extends Thread {
        private  int[] array;
        private final int low;
        private final int high;
        private final int depth;

        public QuickSortThread(int[] array, int low, int high, int deph) {
            this.array = array;
            this.low = low;
            this.high = high;
            this.depth = deph;
        }

        @Override
        public void run() {
            System.out.println("OOGA");
            compute(array, low, high, depth);

        }

        private static void compute(int[] array, int low , int high , int depth ) {
            if (low < high) {

                if (depth < 5) {

                    int pivotIndex = partition(array, low, high);

                    //QuickSortThread leftThread = new QuickSortThread(array, low, pivotIndex - 1, depth + 1);
                    QuickSortThread rightThread = new QuickSortThread(array, pivotIndex + 1, high, depth + 1);

                    //leftThread.start();
                    rightThread.start();

                    try {
                        //leftThread.join();
                        rightThread.join();

                        //depth--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    compute(array, low, pivotIndex - 1, depth + 1);

                }
                else {

                    quickSort(array,low,high);
                }
            }
        }
        public static synchronized void quickSort( int[] array, int low, int high) {
            if (low < high) {
                int pivotIndex = partition(array, low, high);
                quickSort(array, low, pivotIndex - 1);
                quickSort(array, pivotIndex + 1, high);
            }
        }
        private static int partition(int[] arr, int start, int end) {
            int i = start, j = end;

            // Decide random pivot
            int pivoted = new Random().nextInt(j - i) + i;

            // Swap the pivoted with end
            // element of array;
            int t = arr[end];
            arr[end] = arr[pivoted];
            arr[pivoted] = t;
            j--;

            // Start partitioning
            while (i <= j) {
                if (arr[i] <= arr[end]) {
                    i++;
                    continue;
                }

                if (arr[j] >= arr[end]) {
                    j--;
                    continue;
                }

                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
                j--;
                i++;
            }

            // Swap pivoted to its
            // correct position
            t = arr[j + 1];
            arr[j + 1] = arr[end];
            arr[end] = t;
            return j + 1;
        }
    }

    public static void parallelQuickSort(int[] array, int starting_depth) {

        QuickSortThread MotherThread = new QuickSortThread(array, 0, array.length - 1, starting_depth);
        MotherThread.start();
        try {
            MotherThread.join();

        }
        catch(InterruptedException e) {

        }
    }
}