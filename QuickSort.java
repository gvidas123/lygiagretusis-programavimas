public class QuickSort {
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = Main.partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }



    public static int[] start(int[] array) {
        quickSort(array);
        return array;
    }


}