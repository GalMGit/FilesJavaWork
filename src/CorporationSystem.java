import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class CorporationSystem {
    private static List<Employee> employees = new ArrayList<>();
    private static String dataFile;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите путь к файлу данных: ");
        dataFile = scanner.nextLine();

        loadData();

        while (true) {
            showMenu();
            System.out.print("Выберите действие: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: editEmployee(); break;
                case 3: deleteEmployee(); break;
                case 4: searchBySurname(); break;
                case 5: showByAge(); break;
                case 6: showBySurnameStart(); break;
                case 7: showAll(); break;
                case 8: saveToFile(); break;
                case 9: saveAndExit(); return;
                default: System.out.println("Неверный выбор!");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== КОРПОРАЦИЯ ===");
        System.out.println("1. Добавить сотрудника");
        System.out.println("2. Редактировать сотрудника");
        System.out.println("3. Удалить сотрудника");
        System.out.println("4. Поиск по фамилии");
        System.out.println("5. Вывести сотрудников указанного возраста");
        System.out.println("6. Вывести сотрудников, фамилия которых начинается на букву");
        System.out.println("7. Показать всех сотрудников");
        System.out.println("8. Сохранить в файл");
        System.out.println("9. Выход и сохранение");
    }

    private static void loadData() {
        File file = new File(dataFile);
        if (!file.exists()) {
            System.out.println("Файл не найден, будет создан новый.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                employees.add(Employee.fromFileString(line));
            }
            System.out.println("Загружено " + employees.size() + " сотрудников.");
        } catch (IOException e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
        }
    }

    private static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            for (Employee emp : employees) {
                writer.write(emp.toFileString());
                writer.newLine();
            }
            System.out.println("Сохранено " + employees.size() + " сотрудников.");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    private static void saveAndExit() {
        saveToFile();
        System.out.println("До свидания!");
    }

    private static void addEmployee() {
        System.out.print("Фамилия: ");
        String surname = scanner.nextLine();
        System.out.print("Имя: ");
        String name = scanner.nextLine();
        System.out.print("Возраст: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Должность: ");
        String position = scanner.nextLine();
        System.out.print("Зарплата: ");
        double salary = Double.parseDouble(scanner.nextLine());

        employees.add(new Employee(surname, name, age, position, salary));
        System.out.println("Сотрудник добавлен!");
    }

    private static void editEmployee() {
        System.out.print("Введите фамилию сотрудника для редактирования: ");
        String surname = scanner.nextLine();

        Employee found = null;
        for (Employee emp : employees) {
            if (emp.getSurname().equalsIgnoreCase(surname)) {
                found = emp;
                break;
            }
        }

        if (found == null) {
            System.out.println("Сотрудник не найден!");
            return;
        }

        System.out.println("Текущие данные: " + found);
        System.out.print("Новая фамилия (Enter - оставить): ");
        String newSurname = scanner.nextLine();
        if (!newSurname.isEmpty()) found.setSurname(newSurname);

        System.out.print("Новое имя (Enter - оставить): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) found.setName(newName);

        System.out.print("Новый возраст (Enter - оставить): ");
        String newAge = scanner.nextLine();
        if (!newAge.isEmpty()) found.setAge(Integer.parseInt(newAge));

        System.out.print("Новая должность (Enter - оставить): ");
        String newPosition = scanner.nextLine();
        if (!newPosition.isEmpty()) found.setPosition(newPosition);

        System.out.print("Новая зарплата (Enter - оставить): ");
        String newSalary = scanner.nextLine();
        if (!newSalary.isEmpty()) found.setSalary(Double.parseDouble(newSalary));

        System.out.println("Данные обновлены!");
    }

    private static void deleteEmployee() {
        System.out.print("Введите фамилию сотрудника для удаления: ");
        String surname = scanner.nextLine();

        boolean removed = employees.removeIf(emp -> emp.getSurname().equalsIgnoreCase(surname));

        if (removed) {
            System.out.println("Сотрудник удален!");
        } else {
            System.out.println("Сотрудник не найден!");
        }
    }

    private static void searchBySurname() {
        System.out.print("Введите фамилию для поиска: ");
        String surname = scanner.nextLine();

        List<Employee> found = employees.stream()
                .filter(emp -> emp.getSurname().equalsIgnoreCase(surname))
                .collect(Collectors.toList());

        if (found.isEmpty()) {
            System.out.println("Сотрудники не найдены!");
        } else {
            found.forEach(System.out::println);
            saveSearchResult(found);
        }
    }

    private static void showByAge() {
        System.out.print("Введите возраст: ");
        int age = Integer.parseInt(scanner.nextLine());

        List<Employee> filtered = employees.stream()
                .filter(emp -> emp.getAge() == age)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("Сотрудники не найдены!");
        } else {
            filtered.forEach(System.out::println);
            saveSearchResult(filtered);
        }
    }

    private static void showBySurnameStart() {
        System.out.print("Введите букву: ");
        String letter = scanner.nextLine().toLowerCase();

        List<Employee> filtered = employees.stream()
                .filter(emp -> emp.getSurname().toLowerCase().startsWith(letter))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("Сотрудники не найдены!");
        } else {
            filtered.forEach(System.out::println);
            saveSearchResult(filtered);
        }
    }

    private static void showAll() {
        if (employees.isEmpty()) {
            System.out.println("Список сотрудников пуст!");
        } else {
            employees.forEach(System.out::println);
        }
    }

    private static void saveSearchResult(List<Employee> result) {
        System.out.print("Сохранить результат в файл? (y/n): ");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("y")) {
            System.out.print("Введите имя файла: ");
            String filename = scanner.nextLine();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                for (Employee emp : result) {
                    writer.write(emp.toString());
                    writer.newLine();
                }
                System.out.println("Результат сохранен в файл: " + filename);
            } catch (IOException e) {
                System.out.println("Ошибка сохранения: " + e.getMessage());
            }
        }
    }
}