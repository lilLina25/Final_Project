package org.example.Models;

import com.opencsv.bean.CsvBindByName;

public class Coffee {

    private int id;

    private String paymentType;

    private int price;

    private String typeOfCoffee;

   private Coffee(CoffeeBuilder builder){
       this.id = builder.id;
       this.paymentType = builder.paymentType;
       this.price = builder.price;
       this.typeOfCoffee = builder.typeOfCoffee;
   }

   public static CoffeeBuilder builder( ){
       return new CoffeeBuilder();
   }

   public static class CoffeeBuilder{

       private int id;

       private String paymentType;

       private int price;

       private String typeOfCoffee;

       public CoffeeBuilder id(int id){
           this.id = id;
           return this;
       }

       public CoffeeBuilder paymentType(String paymentType){
           this.paymentType = paymentType;
           return this;
       }

       public CoffeeBuilder price(int price){
           this.price = price;
           return this;
       }

       public CoffeeBuilder typeOfCoffee(String typeOfCoffee){
           this.typeOfCoffee = typeOfCoffee;
           return this;
       }

       public Coffee build(){
           return new Coffee(this);
       }
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

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", paymentType='" + paymentType + '\'' +
                ", price=" + price +
                ", typeOfCoffee='" + typeOfCoffee + '\'' +
                '}';
    }
}
