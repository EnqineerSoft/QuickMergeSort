import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Dizi oluşturulur ve rastgele sayılar ile doldurulur
        int[] array =new int[10];
        Random random = new Random();
        Scanner sc=new Scanner(System.in);
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10);
         }
        System.out.println("\nSiralanmamis dizi: ");
        for (int i=0;i< array.length;i++)
        {
            System.out.print(array[i]+" ");
        }
        System.out.println("\n");
        // Her pivot seçimi için işlem sayısını tutan dizi oluşturulur
        int[] operationCounts = new int[10];
        // Her pivot seçimi için işlem sayısı hesaplanır
        for (int i = 0; i < 10; i++) {
            int pivotIndex = (int) (array.length * (i + 1) / 10.0);//her indeks için pivot eleman seçilir.
            pivotIndex=Math.max(pivotIndex,0);
            pivotIndex=Math.min(pivotIndex,array.length-1);
            int[] copy=array.clone();
            operationCounts[i] = quickSort(copy, 0, copy.length - 1, pivotIndex);
            System.out.println("Pivot %" + (i + 1) * 10 + " oraninda belirlendiginde islem sayisi: " + operationCounts[i]);
        }
        // En az işlem sayısı olan pivot seçimi bulunur
        int minIndex = 0;
        for (int i = 1; i < operationCounts.length; i++) {
            if (operationCounts[i] < operationCounts[minIndex]) {

                minIndex = i;
            }
        }
    }
    // Quick sort algoritması
    public static int quickSort(int[] array, int start, int end, int pivotIndex) {
        // Eğer start ve end aynı indekste ise döngüden çıkılır
        if (start >= end-1) {
            return 0;
        }
        // Pivot değeri pivotIndex'ten alınır ve pivot ile end arasındaki elemanlar yer değiştirilir
        pivotIndex=Math.min(pivotIndex,array.length-1);
        end=Math.min(end,array.length-1);
        int pivot = array[pivotIndex];
        array[pivotIndex] = array[end];
        array[end] = pivot;
        // Pivot'ın yerine gelecek elemanın indisi
        int storeIndex = start;
        // Pivot ile start'tan end'e kadar olan elemanlar karşılaştırılır ve pivot'ın yerine gelecek eleman bulunur
        for (int i = start; i <= end; i++) {
            if (array[i] < pivot) {
                int temp = array[storeIndex];
                array[storeIndex] = array[i];
                array[i] = temp;
                storeIndex++;
            }
        }
        // Pivot'ın yerine gelecek eleman ile pivot yer değiştirilir
        array[end] = array[storeIndex];
        array[storeIndex] = pivot;
        // Sol ve sağ taraflar için aynı işlemler tekrarlanır
        int leftOperations = quickSort(array, start, storeIndex - 1, pivotIndex);
        int rightOperations = quickSort(array, storeIndex + 1, end, pivotIndex);
        // Toplam işlem sayısı geri döndürülür
        return 1 + leftOperations + rightOperations;
    }
}
