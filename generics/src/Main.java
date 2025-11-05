import br.com.j_fborges.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome.");

        List<Car> carList = new ArrayList<>();

        carList.add(new BrasiliaCar(100, "gas", "blue"));
        carList.add(new MilleCar(160, "gas", "gray"));
        carList.add(new CivicCar(120, "gas", "white"));
        carList.add(new CorolaCar(120, "gas", "red"));

        printCars(carList);
    }

    public static void printCars(List<? extends Car> list) {
        for (Car car : list) {
            System.out.println(car);
        }
    }
}

