import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();

        for (int num : arr) {
            if (num % 2 == 0) {
                even.add(num);
            } else {
                odd.add(num);
            }
        }

        int[] reversed = new int[size];
        for (int i = 0; i < size; i++) {
            reversed[i] = arr[size - 1 - i];
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(path));

        writer.write(Arrays.toString(arr));
        writer.newLine();

        writer.write(even.toString());
        writer.newLine();

        writer.write(odd.toString());
        writer.newLine();

        writer.write(Arrays.toString(reversed));

        writer.close();

        System.out.println("\nДанные записаны в файл: " + path);

        System.out.println("\nСодержимое файла:");
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}
