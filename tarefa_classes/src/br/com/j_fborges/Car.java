package br.com.j_fborges;

public class Car {

    private String licensePlate = "";
    private float currentKmPerHour = 0;
    private int passengerNumber = 0;
    private float currentGasLevel = 0;

    public boolean isParked() {
        float currentSpeed = getCurrentKmPerHour();

        return !(currentSpeed > 0);
    }

    public float getCurrentKmPerHour() {
        return currentKmPerHour;
    }

    public void setCurrentKmPerHour(float currentKmPerHour) {
        this.currentKmPerHour = currentKmPerHour;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public float getCurrentGasLevel() {
        return currentGasLevel;
    }

    public void setCurrentGasLevel(float currentGasLevel) {
        this.currentGasLevel = currentGasLevel;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void registerCar(String _licensePlate){
        setLicensePlate(_licensePlate);
        System.out.println("Car license is now " + getLicensePlate());
    }

    public void pilotEnters(){
        if(isParked()) {
            int passengersN = getPassengerNumber();
            setPassengerNumber(passengersN+1);
            System.out.println("Passenger Number is now " + getPassengerNumber());
        } else {
            System.out.println("Car needs to be parked for someone to hop in!");
        }
    }

    public void passengerEnters(){
        if(isParked()) {
            int passengersN = getPassengerNumber();
            setPassengerNumber(passengersN+1);
            System.out.println("Passenger Number is now " + getPassengerNumber());
        } else {
            System.out.println("Car needs to be parked for someone to hop in!");
        }
    }

    public void accelerate(){
        float currentSpeed = getCurrentKmPerHour();
        setCurrentKmPerHour(currentSpeed + 10);
        System.out.println("Current Speed is " + getCurrentKmPerHour());
    }

    public void decelerate(){
        float currentSpeed = getCurrentKmPerHour();
        setCurrentKmPerHour(currentSpeed - 10);
        System.out.println("Current Speed is " + getCurrentKmPerHour());
    }

    public void fillGasTank(){
        setCurrentGasLevel(100);
    }
}
