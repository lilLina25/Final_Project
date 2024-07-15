package org.example.DataGeneration;

import org.example.Models.Coffee;

import java.util.Random;

public class RandomGeneration {

    private int count;

    private enum TypeOfCoffee{
        AMERICANO, LATTE, MOCHA, CAPPUCCINO, RISTRETTO, MACCHIATO, LUNGO,
        FLAT_WHITE, CORRETTO, ICED_COFFEE, AFFOGATO, IRISH_COFFEE,
        TURKISH_COFFEE, FRAPPE, ESPRESSO;

        public static TypeOfCoffee getRandomTypeOfCoffee(){
            return values()[new Random().nextInt(values().length)];
        }

        @Override
        public String toString() {
            // Преобразуем имя enum в строку с первой заглавной буквой и остальными строчными
            String lower = name().toLowerCase();
            return Character.toUpperCase(lower.charAt(0)) + lower.substring(1).replace('_', ' ');
        }


    }

    private enum PaymentType{
        CARD, CASH;

        public static PaymentType getRandomPaymentType(){
            return values()[new Random().nextInt(values().length)];
        }

        @Override
        public String toString() {
            // Преобразуем имя enum в строку с первой заглавной буквой и остальными строчными
            String lower = name().toLowerCase();
            return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
        }
    }


    public RandomGeneration(int count) {
        this.count = count;
    }

    // Метод создает классы с рандомными данными и передает их в репозиторий
    public void dataGeneration(){
        for(int i = 0; i < count; i++){
            Coffee coffee = new Coffee.CoffeeBuilder()
                    .id(new Random().nextInt(count * 10))
                    .paymentType(PaymentType.getRandomPaymentType().toString())
                    .price(new Random().nextInt(1000))
                    .typeOfCoffee(TypeOfCoffee.getRandomTypeOfCoffee().toString())
                    .build();

            // Тут отпраялем наше кофе в репозиторий условным методом add
            // Some class.add(coffee);
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
