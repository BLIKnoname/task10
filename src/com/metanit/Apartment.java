package com.metanit;

import java.util.Objects;

public class Apartment {

    public String street;
    public double sKitchen;
    public int countOfRooms;
    public double s;
    public double price;

    public Apartment(String street, double sKitchen, int countOfRooms, double s, double price) {
        this.street = street;
        this.sKitchen = sKitchen;
        this.countOfRooms = countOfRooms;
        this.s = s;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return sKitchen == apartment.sKitchen && countOfRooms == apartment.countOfRooms && Double.compare(apartment.s, s) == 0 && Objects.equals(street, apartment.street) && Objects.equals(price, apartment.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, sKitchen, countOfRooms, s, price);
    }

    @Override
    public String toString() {
        return "Квартира {" +
                "адрес = '" + street + '\'' +
                ", площадь кухни = " + sKitchen +
                ", кол-во комнат = " + countOfRooms +
                ", общая площадь = " + s +
                ", цена = " + price +
                '}';
    }
}


