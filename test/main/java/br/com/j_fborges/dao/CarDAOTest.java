package main.java.br.com.j_fborges.dao;

import main.java.br.com.j_fborges.domain.Accessory;
import main.java.br.com.j_fborges.domain.Car;
import main.java.br.com.j_fborges.domain.CarBrand;
import main.java.br.com.j_fborges.domain.CarModel;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CarDAOTest {

    private ICarDAO carDAO;
    private ICarModelDAO carModelDAO;
    private ICarBrandDAO carBrandDAO;
    private IAccessoryDAO accessoryDAO;

    public CarDAOTest() {
        this.carDAO = new CarDAO();
        this.carModelDAO = new CarModelDAO();
        this.carBrandDAO = new CarBrandDAO();
        this.accessoryDAO = new AccessoryDAO();
    }

    @Test
    public void register(){
        Car car = new Car();
        CarModel carModel = new CarModel();
        CarBrand carBrand = new CarBrand();
        Accessory accessory = new Accessory();


        carBrand.setName("Ford");
        carBrand.setCreationYear(1896);

        carBrand = carBrandDAO.register(carBrand);

        accessory.setBrand(carBrand);
        accessory.setTitle("Smartphone Charger");
// ONLY IF CASCADE MERGE
//        accessory = accessoryDAO.register(accessory);

        ArrayList<Accessory> carAccessories = new ArrayList<>();

        carAccessories.add(accessory);

        carModel.setCarBrand(carBrand);
        carModel.setName("Fiesta");
        carModel.setFabricationYear(2013);

        carModel = carModelDAO.register(carModel);

        car.setModel(carModel);
        car.setVehicleIdNumber("123456789qwertyui");
        car.setAccessories(carAccessories);

        car = carDAO.register(car);

        assertNotNull(car);
        assertNotNull(car.getId());

        assertNotNull(carModel);
        assertNotNull(carModel.getId());

        assertNotNull(carBrand);
        assertNotNull(carBrand.getId());

        assertNotNull(accessory);
        assertNotNull(accessory.getId());
    }
}