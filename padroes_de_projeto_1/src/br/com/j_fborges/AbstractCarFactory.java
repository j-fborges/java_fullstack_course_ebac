package br.com.j_fborges;

public abstract class AbstractCarFactory {

    public Car create(String requestedGrade) {
        Car car = retrieveCar(requestedGrade);
        car = prepareCar(car);
        return car;
    }
    private Car prepareCar(Car car){
        car.clean();
        car.mechanicCheck();
        car.fuelCar();
        return car;
    }
    abstract Car retrieveCar(String requestedGrade);
}
