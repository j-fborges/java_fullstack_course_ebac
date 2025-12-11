package main.java.br.com.j_fborges.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TST_CAR_MODELS")
public class CarModel implements Persistent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_models_seq")
    @SequenceGenerator(name = "car_models_seq", sequenceName = "sq_car_model", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "FABRICATION_YEAR")
    private Integer fabricationYear;

    @ManyToOne
    @JoinColumn(name = "id_car_brand_fk",
            foreignKey = @ForeignKey(name = "fk_car_brand"),
            referencedColumnName = "id", nullable = false
    )
    private CarBrand carBrand;

    @OneToMany(mappedBy = "model")
    private List<Car> cars;

    public CarModel(){
        this.cars = new ArrayList<>();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFabricationYear() {
        return fabricationYear;
    }

    public void setFabricationYear(Integer fabricationYear) {
        this.fabricationYear = fabricationYear;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }
}
