import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь к первому файлу: ");
        String path1 = scanner.nextLine();

        System.out.print("Введите путь ко второму файлу: ");
        String path2 = scanner.nextLine();

        List<String> lines1 = Files.readAllLines(Paths.get(path1));
        List<String> lines2 = Files.readAllLines(Paths.get(path2));

        int maxLines = Math.max(lines1.size(), lines2.size());
        boolean hasDiff = false;

        for (int i = 0; i < maxLines; i++) {
            String line1 = i < lines1.size() ? lines1.get(i) : "(файл закончился)";
            String line2 = i < lines2.size() ? lines2.get(i) : "(файл закончился)";

            if (!line1.equals(line2)) {
                System.out.println("\nНесовпадение в строке " + (i + 1) + ":");
                System.out.println("Файл 1: " + line1);
                System.out.println("Файл 2: " + line2);
                hasDiff = true;
            }
        }

        if (!hasDiff) {
            System.out.println("Файлы полностью совпадают");
        }
    }
}