import br.com.j_fborges.Car;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        Car car = new Car();

        car.registerCar("JAV4444");
        car.fillGasTank();
        car.pilotEnters();
        car.accelerate();
        car.accelerate();
        car.accelerate();
        car.accelerate();
        car.accelerate();
        car.decelerate();
        car.decelerate();
        car.decelerate();
        car.decelerate();
        car.decelerate();
        car.passengerEnters();

        System.out.println("Where shall we go?");
    }
}