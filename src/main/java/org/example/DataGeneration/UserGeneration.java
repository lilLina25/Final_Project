package org.example.DataGeneration;

import org.example.Models.Coffee;

public class UserGeneration implements Generatable {

    private int id;

    private String paymentType;

    private int price;

    private String typeOfCoffee;

    public UserGeneration(int id, String paymentType, int price, String typeOfCoffee) {
        this.id = id;
        this.paymentType = paymentType;
        this.price = price;
        this.typeOfCoffee = typeOfCoffee;
    }

   // Метод создает класс коффе с данными который ввел пользователь и передает их в репозиторий
    @Override
    public void dataGeneration() {
        Coffee coffee = new Coffee.CoffeeBuilder()
                .id(id)
                .paymentType(paymentType)
                .price(price)
                .typeOfCoffee(typeOfCoffee)
                .build();


        // Отправляем в репозиторий
        // SomeClass.add(coffee)
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTypeOfCoffee() {
        return typeOfCoffee;
    }

    public void setTypeOfCoffee(String typeOfCoffee) {
        this.typeOfCoffee = typeOfCoffee;
    }
}
