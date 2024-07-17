package org.example.sorting;

import java.util.Arrays;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public class LibrarySort {
    int[] arr = {2,100,200,35,15,164}; //Подставить генератор чисел
    int counter = 0;

    public static void main(String[] args) {
        LibrarySort sort = new LibrarySort();
        sort.sortArray(sort.primaryArray); //Метод сортировки
    }
    int primaryArray[] = arr; //Основной массив
    int primaryLength = primaryArray.length; // Длина основного массива
    int e = 2;//Во сколько раз увеличить массив
    int arrayLength = e * (primaryLength + 1) - 1;//Формула для расчета количества элементов во вспомогательном массиве.
    int supportingArray[] = new int[arrayLength]; //Вспомогательный массив
    int start = 0; //Начало сортировки
    int finish = supportingArray.length - 1; // Конец сортировки
//Основной метод
    public int[] sortArray(int[] arr) {
        LibrarySort sort = new LibrarySort();
        supportingArray[sort.sortPos(1, 1, sort.arrayLength)] = primaryArray[0]; //Переносим первый элемент в середину вспомогательного массива
        int i = 1; // Итерация с единицы, тк первый элемент уже вставлен.
        int start = 0;
        int finish = arrayLength - 1;
        int pos; // Позиция для переноса элемента
        boolean rebalanceLocal = false;//Локальная или полная(false) перебалансировка
        System.out.println("Дополнительный перед вставкой "+Arrays.toString(supportingArray));
        //Перебираем элементы основного массива
        while (i < primaryLength) {
            pos = binarySearch(primaryArray[i], start, finish, arrayLength); // Позиция для вставки в доп массив
            System.out.println("Итерация " + i + ", позиция для вставки " + pos + ", число " + arr[i]);
            //Бинарным поиском ищем место для вставки в дополнительном массиве
                //Точки вставки нашлась, но надо проверить, свободна ли она
                if (supportingArray[pos] != 0) {
                    //Точка вставки занята
                    if (rebalanceLocal) {
                        //Локальная перебалансировка
                        rebalanceLocal(arr[i++], pos, arrayLength);
                        continue;
                    } else {
                        //Полная перебалансировка
                        rebalanceTotal(i, arrayLength);
                        continue;
                    }
                } else {
                    //Свободная точка для вставки найдена, вставляем
                    supportingArray[pos] = arr[i];
                }
            System.out.println("Дополнительный массив после вставки "+Arrays.toString(supportingArray));
            i++;
        }
        //Переносим из дополнительного массива в основной
        System.out.println("Дополнительный массив перед переносом "+Arrays.toString(supportingArray));
        pos = 0;
        for (i = 0; i <= arrayLength - 1; i++) {
            if (supportingArray[i] != 0) {
                arr[pos++] = supportingArray[i];
            }
        }
        System.out.println("Количество перестановок " + counter);
        System.out.println("РЕЗУЛЬТАТ, Основной массив после сортировки "+Arrays.toString(arr));
        return arr;
    }
//Позиция элемента для вставки (после перебалансировки, например)
    public int sortPos(int number, int count, int arrayLength) {
        double d = number * floor((arrayLength + 1) / (count + 1)) - 1; //number - какой по счёту в массиве (с начала) элемент
        int i = (int) d;                                                //count - сколько на данный момент перенесено элементов
        return i;                                                       //arrayLength - полный размер вспомогательного массива
    }
//Если элемент равен концу отрезка (Если элемент равен концу отрезка, то ставим его рядом слева или справа)
    public int posNearby(int start, int arrayLength) {
        //Проверяем свободное место перед элементом, то есть слева
        for (int left = start - 1; left >= 0; left--) {   //start - позиция, на которой уже стоит элемент равный искомому
            if (supportingArray[left] == 0) {             //arrayLength - полный размер вспомогательного массива
                //Пустая ячейка
                return left;//Место для вставки обнаружено
            } else if (supportingArray[left] != supportingArray[start]) {
                //Встретился элемент с другим значением
                break; //Прерываем работу цикла, ячейка занята
            }
            //Проверяем свободное место справа, если слева все занято
            for (int right = start + 1; right <= arrayLength - 1; right++) {
                if (supportingArray[right] == 0) {
                    //Пустая ячейка
                    return right; //Место для вставки обнаружено
                } else if (supportingArray[right] != supportingArray[start]) {
                    //Встретился элемент с другим значением
                    break; //Места нет, прерываем работу цикла
                }
            }
        }
        return start; //Нет места для вставки, возвращаем точку, с которой начали поиск.
    }
//Бинарный поиск места вставки во вспомогательном массиве
    public int binarySearch(int search, int start, int finish, int arrayLength) {
        //Сужаем диапазон слева, если на левом краю пустые элементы
        while (supportingArray[start] == 0 && start < arrayLength - 1) {
            ++start;
        }
        if (search == supportingArray[start]) { //search - значение элемента из основного массива, который нужно перенести во вспомогательный
            return posNearby(start, arrayLength); //Если искомый элемент такого же значения, что и элемент на границе,то нужно вставить сразу за ним или перед ним
        } else if (search < supportingArray[start]) {  //Тогда искомому элементу место между левой границей и началом дополнительного массива
            if (start > 0) { //Перед start есть свободное место
                finish = start;
                start = 0;
                double d = floor((start + finish) / 2);
                int i = (int) d;
                return i;
            } else {
                //start == 0, перед ним нечего вставлять, возвращаем не пустую ячейку
                return start;
            }
        }
        while (supportingArray[finish] == 0 && finish > 0) { //Сужаем справа диапазон, если на левом краю пустые элементы
            --finish;
        }
        if (search == supportingArray[finish]) { //Если на границе такого же значения элемент что и искомый, то нужно вставить сразу за ним или перед ним
            return posNearby(finish, arrayLength);
        } else if (search > supportingArray[finish]) {//Искомому элементу место между правой границей и концом дополнительного массива
            if (finish < arrayLength - 1) {//После $finish есть свободное место
                start = finish;
                finish = arrayLength - 1;
                double d = ceil((start + finish) / 2);
                int i = (int) d;
                return i;
            } else {//finish == arrayLength - 1, после него ничего не вставить
                return finish;//Возвращаем не пустую ячейку
            }
        }
        //Искомый элемент не совпадает с краями, значит, его нужно вставить где-то внутри диапазона
        //При этом нужно проверить, есть ли внутри диапазона ещё элементы
        if (finish - start > 1) {//Делить диапазон имеет смысл, если там минимум 3 элемента
            //Индекс середины диапазона
            double d = ceil((start + finish) / 2);
            int middle = (int) d;
            int middlePos = 0; //Не пустую "середину" диапазона пока не нашли
            int offset = 0; //Будем сдивгаться от точной середины в поисках непустого элемента

            //Сдвигаемся от середины влево/вправо, пока не обнаружим непустой элемент
            while (middle - offset > start && middlePos == 0) {
                if (supportingArray[middle - offset] != 0) {
                    middlePos = middle - offset;
                } else if (middle + offset < finish && supportingArray[middle + offset] != 0) {
                    middlePos = middle + offset;
                }
                ++offset;
            }
            //Если внутри нашли непустой элемент, то, в зависимости от его значения или ищем место возле него или рекурсивно применяем к левой или правой части диапазона
            if (middlePos != 0) {
                if (supportingArray[middlePos] == search) {
                    return posNearby(middlePos, arrayLength);
                } else {
                    if (supportingArray[middlePos] > search) {
                        finish = middlePos;
                    } else {//arrayLength[middle_Pos] < search
                        start = middlePos;
                    }
                    return binarySearch(search, start, finish, arrayLength);
                }
            } else {//middle_Pos == 0 - внутри диапазона (не считая его границ) не найдены непустые элементы
                return middle;//Середина такого диапазона - место, куда можно вставить элемент
            }

        } else {//Нашли минимальный отрезок, но свободного места в нём нет
            double d = floor((start + finish) / 2);
            int i = (int) d;
            return i;
        }
    }
//Локальная перебаласировка для вспомогательного массива
    public void rebalanceLocal(int insert, int pos, int arrayLength) {
        //insert - значение, которое надо вставить
        //pos - начиная с какого элемента нужно сдвинуть несколько штук влево или вправо
        //arrayLength - полный размер вспомогательного массива

        //Уточняем pos для insert, иногда надо чуть левее или правее
        while (pos - 1 >= 0 && supportingArray[pos - 1] != 0 && supportingArray[pos - 1] > insert) {
            --pos;
        }
        while (pos + 1 <= arrayLength - 1 && supportingArray[pos + 1] != 0 && supportingArray[pos + 1] < insert) {
            ++pos;
        }
        int middle = (int) arrayLength / 2;//Середина массива
        if (pos <= middle) {//Точка вставки в левой части массива
            if (supportingArray[pos] != 0 && supportingArray[pos] < insert) ++pos;
            //Сдвигаем вправо
            int right = pos;
            while (supportingArray[++right] != 0) {}
            for (int i = right; i > pos; i--) {
                supportingArray[i] = supportingArray[i - 1];
                counter++;
            }
        } else {//Точка вставки в правой части массива
            if (supportingArray[pos] != 0 && insert < supportingArray[pos]) --pos;
            //Сдвигаем влево
            int left = pos;
            while (supportingArray[--left] != 0) {}
            for (int i = left; i < pos; i++) {
                supportingArray[i] = supportingArray[i + 1];
                counter++;
            }
        }
        supportingArray[pos] = insert;
    }
//Полная перебалансировка вспомогательного массива
    public void rebalanceTotal(int count, int arrayLength) {
        //count - сколько элементов на данный момент в массиве
        int libraryNumber;//Какой по счёту элемент переносим
        int libraryLeftPos;//Самая левая позиция при которой влево переносили элементы

        libraryNumber = count; //Начинаем переносить последние с конца по счёту элементы
        libraryLeftPos = arrayLength - 1;//Пока неизвестна, поэтому максимально правое значение

        //Проходим элементы в дополнительном массиве от последнего к началу
        int i = arrayLength - 1;
        LibrarySort sort = new LibrarySort();
        while(i >= 0) {
            if(supportingArray[i] != 0) {//Не пустой элемент
                int pos = sort.sortPos(libraryNumber, count, arrayLength); //Его надо перенести сюда

                if(i == pos) {//Элемент уже на своём месте
                    --i;//Переходим дальше влево по дополнительному массиву
                } else if(i < pos) {//Элемент нужно перенести вправо
                    supportingArray[pos] = supportingArray[i];
                    supportingArray[i] = 0;
                    counter++;
                    --i;//Переходим дальше влево по дополнительному массиву
                } else {//i > pos - элемент нужно перенести влево
                    //Рекурсивная процедура для переноса элемента влево
                    removeLeft(i, pos, count, arrayLength, libraryNumber, libraryLeftPos);
                    //Уточняем, был ли это самый влево перенесённый из соседей
                    if(pos < libraryLeftPos) {
                        libraryLeftPos = pos;
                    }
                    i = i > libraryLeftPos  ? libraryLeftPos  - 1 : --i;
                }
                --libraryNumber;//Переносимых элементов стало на одного меньше
            } else {//Пустая клетка, нечего переносить
                --i;//Переходим дальше влево по дополнительному массиву
            }
        }
    }
//Перенос элемента левее в перебалансируемом массиве.
    public void removeLeft(int i, int pos, int count, int arrayLength, int libraryNumber, int libraryLeftPos) {
        //i - в какой ячейке находится элемент, который нужно перенести
        //pos - в какую ячейку нужно перенести элемент
        //count - сколько всего сейчас элементов во вспомогательном массиве
        int left = 0; int leftPos = 0;//Пока ближайший сосед слева не найден
        int j = i;//Начинаем перебирать сразу от переносимого элемента

        //Нужно выяснить ближайшего соседа слева
        while(j > 0 && left == 0) {//Пока в пределах вспомогательного массива и пока не найден ближайший сосед слева
            --j; //Продолжаем влево поиск ближайшего соседа
            if(supportingArray[j] != 0) left = j;//Ближайший сосед слева обнаружен
        }
        if(left == 0 || left < pos) {//Ближайший сосед слева (если он найден) не мешает переносу
            //Ничего дополнительно предпринимать не нужно
        } else { //left >= pos, сосед слева мешает переносу
            --libraryNumber;//Значит, левее нужно перенести мешающего соседа слева
            leftPos = sortPos(libraryNumber, count, arrayLength);//Соседа слева надо перенести сюда
            //Рекурсивно переносим соседа слева на его правильную позицию
            removeLeft(left, leftPos, count, arrayLength, libraryNumber, libraryLeftPos);
            counter++;
            //Сосед слева отодвинут, теперь можно перенести элемент
        }
        //С соседом слева всё нормально, переносим элемент
        supportingArray[pos] = supportingArray[i];
        supportingArray[i] = 0;
    }
}
