import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Massive {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();

        BufferedReader reader = new BufferedReader(new FileReader(path));

        List<int[]> arrays = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] numbers = line.trim().split(" ");
            int[] arr = new int[numbers.length];

            for (int i = 0; i < numbers.length; i++) {
                arr[i] = Integer.parseInt(numbers[i]);
            }

            arrays.add(arr);
        }

        reader.close();

        int globalMax = Integer.MIN_VALUE;
        int globalMin = Integer.MAX_VALUE;
        int globalSum = 0;

        for (int i = 0; i < arrays.size(); i++) {
            int[] arr = arrays.get(i);

            int max = arr[0];
            int min = arr[0];
            int sum = 0;

            for (int num : arr) {
                if (num > max) max = num;
                if (num < min) min = num;
                sum += num;
            }

            if (max > globalMax) globalMax = max;
            if (min < globalMin) globalMin = min;
            globalSum += sum;

            System.out.println("Массив " + (i + 1) + ": " + Arrays.toString(arr));
            System.out.println("  Максимум: " + max);
            System.out.println("  Минимум: " + min);
            System.out.println("  Сумма: " + sum);
            System.out.println();
        }

        System.out.println("Общая статистика:");
        System.out.println("  Максимум среди всех массивов: " + globalMax);
        System.out.println("  Минимум среди всех массивов: " + globalMin);
        System.out.println("  Общая сумма всех массивов: " + globalSum);
    }
}
