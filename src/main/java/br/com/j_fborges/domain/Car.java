package main.java.br.com.j_fborges.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TST_CARS")
public class Car implements Persistent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cars_seq")
    @SequenceGenerator(name = "cars_seq", sequenceName = "sq_car", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_car_model_fk",
            foreignKey = @ForeignKey(name = "fk_car_model"),
            referencedColumnName = "id", nullable = false
    )
    private CarModel model;

    @Column(name = "VEHICLE_ID_NUMBER", length = 17, nullable = false)
    private String vehicleIdNumber;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "TST_CAR_ACCESSORIES",
            joinColumns = { @JoinColumn(name = "id_car_fk") },
            inverseJoinColumns = { @JoinColumn(name = "id_accessory_fk") }
    )
    private List<Accessory> accessories;

    public Car(){
        this.accessories = new ArrayList<>();
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public String getVehicleIdNumber() {
        return vehicleIdNumber;
    }

    public void setVehicleIdNumber(String vehicleIdNumber) {
        this.vehicleIdNumber = vehicleIdNumber;
    }
}
