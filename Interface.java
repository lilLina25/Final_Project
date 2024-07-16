import java.util.Arrays;
import java.util.Scanner;

public class Interface {
    Scanner sc = new Scanner(System.in);


    public void choiceMethodInput() {
        int choice = 0;
        while (choice != 4) {
            switch (choice) {
                case 0:
                    System.out.println("Выберите метод ввода данных:\n1 - сгенерировать рандомно \n2 - загрузить данные из файла \n3 - ввести данные вручную");
                    break;
                case 1:
                    System.out.println("Укажите размер массива:");
                    int arrayLength = sc.nextInt();
                   // dataConfirmation(метод генерации рандомом(arrayLength));
                    break;
                case 2:
                    System.out.println("Введите путь к выбранному файлу:");
                    String path = sc.next();
                    //  dataConfirmation(); - тут метод который вытаскивает данные из репозитория
                    break;
                case 3:
                    System.out.println("Введите данные для сортировки:");
                    // dataConfirmation(); - метод который достает файлы из репозитория
                    break;
                default:
                    System.out.println("Ошибка ввода! Пожалуйста, введите число соответствующее выбранной команде");
            }
            choice = sc.nextInt();
        }
    }

    public void dataConfirmation(int [] array){
        System.out.println("Данные для сортировки:");
        System.out.println(Arrays.toString(array));
        int choice = 0;
        while (choice != 3) {
            switch (choice) {
                case 0:
                    System.out.println("Данные указаны верно?\n1 - Да, можно переходить к сортировке \n2 - Нет, требуется корректировка");
                    break;
                case 1:
                    choiceMethodSorted();
                    break;
                case 2:
                    choiceMethodInput();
                    break;
                default:
                    System.out.println("Ошибка ввода! Пожалуйста, введите число соответствующее выбранной команде");
            }
            choice = sc.nextInt();
        }
    }

    public void choiceMethodSorted(){
    int choice1 = 0;
        while (choice1 != 3) {
        switch (choice1) {
            case 0:
                System.out.println("Выберите метод сортировки данных:\n1 - Library sorting \n2 - Smooth sorting");
                break;
            case 1:
                int choice2 = 0;
                boolean choiceMethod = true;
                while (choice2 != 3) {
                    switch (choice2) {
                        case 0:
                            System.out.println("Выберите способ сортировки данных:\n1 - полная ребалансировка \n2 - локальная ребалансировка");
                            break;
                        case 1:
                            //етод в который мы передаем переменную - (!choiceMethod)
                            break;
                        case 2:
                            //метод в который мы передаем переменную - (choiceMethod)
                            break;
                        default:
                            System.out.println("Ошибка ввода! Пожалуйста, введите число соответствующее выбранной команде");
                    }
                    choice2 = sc.nextInt();
                }
                break;
            case 2:
                //метод плавной сортировки
                break;
            default:
                System.out.println("Ошибка ввода! Пожалуйста, введите число соответствующее выбранной команде ");
        }
        choice1 = sc.nextInt();
    }
}
    public void choiceMethodOutput() {
        int choice = 0;
        while (choice != 3) {
            switch (choice) {
                case 0:
                    System.out.println("Выберите метод вывоода данных:\n1 - вывести отсортированные данные в консоль \n2 - загрузить отсортированный данные в файл");
                    break;
                case 1:
                    // System.out.println("Метод который достает данные из репозитория");
                    break;
                case 2:
                    System.out.println("Введите путь к файлу, в который необходимо загрузить отсортированные данные:");
                    String path = sc.next();
                    break;
                default:
                    System.out.println("Ошибка ввода! Пожалуйста, введите число соответствующее выбранной команде");
            }
            choice = sc.nextInt();
        }
    }
}
