class MultiThreadedQuickSort extends Thread {
    private int arr[];
    private int low,high;
    public static int numThreads = 16;
    public static int count = 0;

    public MultiThreadedQuickSort(int[] arr, int low, int high){
        this.arr = arr;
        this.low = low;
        this.high = high;
    }

    public void run(){
        parallelQuicksort(arr,low,high);
    }

    public static void parallelQuicksort(int[] arr, int low, int high){
        if (high>low){
            int i = Main.partition(arr,low,high);
            if (count < numThreads){

                count++;
                MultiThreadedQuickSort quicksort  = new MultiThreadedQuickSort(arr, low, i-1);
                quicksort.start();
                try{
                    quicksort.join();

                }
                catch (InterruptedException e){}
            }
            else{
                QuickSort.quickSort(arr,low,i-1);
            }
            if (count < numThreads){
                count++;
                MultiThreadedQuickSort quicksort  = new MultiThreadedQuickSort(arr, i+1, high);
                quicksort.start();
                try{
                    quicksort.join();

                }
                catch (InterruptedException e){}
            }
            else{
                QuickSort.quickSort(arr,i+1,high);
            }
        }
    }





}