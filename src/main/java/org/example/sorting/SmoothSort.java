package org.example.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmoothSort {
    public static void main(String[] args) {

        int[] arr = {2,100,200,35,15,164}; //Подставить генератор чисел
        SmoothSort sort = new SmoothSort();
        sort.sort(arr);
        System.out.println(sort.counter);
    }

    int counter;

    public void sort(int[] array) {
        sortLocal(array, array.length);
    }

    //Генерация массива чисел Леонардо
    public List<Integer> generateLeonardoNumbers(int[] inputArray) {
        List<Integer> leonardoNumbers = new ArrayList<>();
        leonardoNumbers.add(1);
        leonardoNumbers.add(1);
        counter += 2;
        for (int i = 2; leonardoNumbers.get(i - 1) < inputArray.length; i++) {
            leonardoNumbers.add(leonardoNumbers.get(i - 1) + leonardoNumbers.get(i - 2) + 1); //Расчет чисел Леонардо
            counter++;
        }
        return leonardoNumbers;
    }

    // Текущая куча - самая правая куча
    public void sortLocal(int[] array, int bound) {
        if (array.length > 1) { // В других случаях массив остаётся в первоначальном виде
            List<Integer> leonardoNumbers = generateLeonardoNumbers(array);
            //Делаем две кучи
            Node root1 = new Node(array[0]); // Корень "предправой" кучи (второй справа) Первый элемент массива
            Node root2 = new Node(array[1]); // Корень "правой" кучи (первый справа) Второй элемент массива
            counter += 2;
            root2.setPrevRootOfHeap(root1); // Делаем связь между двумя кучами, чтобы не потерять
            for (int i = 2; i < array.length; i++) { // Начинаем с третьего элемента, т.к. два по умолчанию существуют, это начала двух куч
                Node node = new Node(array[i]); // Создаем текущий узел
                if (root1 != null && (Math.abs(leonardoNumbers.indexOf(root1.getSizeOfHeap()) - leonardoNumbers.lastIndexOf(root2.getSizeOfHeap())) == 1)) {
                    // Если существует минимум 2 кучи и элементы(в качестве размеров куч) в массиве чисел Леонардо соседние, то поизводим слияние двух куч в одну
                    node.setSizeOfHeap(root1.getSizeOfHeap() + root2.getSizeOfHeap() + 1); // Меняем размер кучи, где корнем является node
                    node.setLeft(root1); // Слева "предправая" куча
                    node.setRight(root2); // Справа правая куча
                    //Вы разблокировали общую кучу

                    if (root1.getPrevRootOfHeap() != null) { // Если есть еще кучи помимо этих двух
                        node.setPrevRootOfHeap(root1.getPrevRootOfHeap()); // Переставляем указатель, направленный на ближайшую левую кучу, на созданную кучу
                        root1 = root1.getPrevRootOfHeap(); // Ту, на которую был указатель, представляем как вторую справа
                    } else root1 = null; // Если нет других куч
                    siftInHeap(node); // Просейка имеющейся кучи
                } else { // Иначе
                    root1 = root2; // Если существовали оба корня, то смещаемся к самому "свежему" (предварительно создав связь, чтобы не потерять кучи)
                    node.setPrevRootOfHeap(root1); // Создаём связь между кучами
                }
                counter++;
                root2 = node; // Представляем нашу кучу как самую первую справа, самый правый элемент
            }
            setArray(array, root2, bound);
            System.out.println(Arrays.toString(array));
        }
    }
    //Формируем отсортированный массив
    public void setArray(int[] array, Node root, int bound) { //массив, узел, граница кучи(длина массива)
        Node currentRoot = root; //самый правый элемент, начало кучи.
        for (int i = array.length - 1; i >= array.length - bound; i--) {
            array[i] = getHigh(currentRoot).getValue(); //Берем значение из исходного массива и на его место ставим наибольшее значение корня
            counter++;

            if (currentRoot.getRight() != null) currentRoot = currentRoot.getRight();//Если справа от самого правого элемента
            else currentRoot = currentRoot.getPrevRootOfHeap();//Больше ничего нет, то достаем связанный узел с кучей, куча кончилась
        }
        for (int i = array.length - bound - 1; i >= 0; i--) {//Если граница больше длины исходного массива. Если нет, цикл не запустится.
            array[i] = currentRoot.getValue(); //Получаем значение самого правого элементав куче и записываем в массив.
            if (currentRoot.getRight() != null) currentRoot = currentRoot.getRight(); //Если справа еще есть элементы, то делаем их правыми.
            else currentRoot = currentRoot.getPrevRootOfHeap(); //Если нет, достаем узел,связанный с этой кучей
        }
    }
    // Просейка, возвращает узел с самым большим значением.
    public Node getHigh(Node root) {
        siftBetweenHeaps(root, root, root);
        return root;
    }
//Просейка между кучами
    public Node siftBetweenHeaps(Node root, Node current, Node result) {
        Node prevRoot = current.getPrevRootOfHeap(); //Первый узел в куче
        if (prevRoot != null) { //Если не пустой
            if (prevRoot.getValue() > result.getValue()) { //Выбираем наибольший узел и записываем его наверх
                result = prevRoot;
                counter++;
            }
            return siftBetweenHeaps(root, prevRoot, result);//Вызываем метод до тех пор, пока кучи не будут просеяны в порядке от бОльшего к меньшему.
        }
        if (root != result) { //Если узел в корне не равен самому большому узлу, меняем их местами, просеиваем кучу.
            swapValues(root, result);
            siftInHeap(result);
        }
        return root;
    }
//Просейка внутри кучи
    public Node siftInHeap(Node root) {
        if (root != null && root.getLeft() != null) {
            Node maxNode = null;
            if (root.getLeft().getValue() > root.getValue()
                    && root.getLeft().getValue() >= root.getRight().getValue()) {
                maxNode = root.getLeft();
                counter++;
            } else if (root.getRight().getValue() > root.getValue()
                    && root.getRight().getValue() >= root.getLeft().getValue()) {
                maxNode = root.getRight();
                counter++;
            }
            if (maxNode != null) {
                swapValues(maxNode, root);
                return siftInHeap(maxNode);
            }
        }
        return root; // Вернёт наименьший node
    }
//Меняем узлы местами
    public void swapValues(Node n1, Node n2) {
        int temp = n2.getValue();
        n2.setValue(n1.getValue());
        n1.setValue(temp);
        counter++;
    }
}


