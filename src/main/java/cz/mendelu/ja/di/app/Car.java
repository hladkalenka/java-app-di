package cz.mendelu.ja.di.app;

import java.util.UUID;

public class Car {

    private UUID id;
    private final String spz;
    private final String znacka;

    public Car(String spz, String znacka) {
        this.spz = spz;
        this.znacka = znacka;
    }

    public Car with(UUID id) {
        var copy = new Car(spz, znacka);
        copy.id = id;
        return copy;
    }

    public UUID getId() {
        return id;
    }

    public String getSpz() {
        return spz;
    }

    public String getZnacka() {
        return znacka;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", spz='" + spz + '\'' +
                ", znacka='" + znacka + '\'' +
                '}';
    }
}
