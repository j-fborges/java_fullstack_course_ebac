package br.com.j_fborges;

public abstract class Car {
    private int horsePower;
    private String fuelSource;
    private String color;

    public Car(int horsePower, String fuelSource, String color) {
        this.horsePower = horsePower;
        this.fuelSource = fuelSource;
        this.color = color;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "horsePower=" + horsePower +
                ", fuelSource='" + fuelSource + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
