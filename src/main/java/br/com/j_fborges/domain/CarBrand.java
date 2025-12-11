package main.java.br.com.j_fborges.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TST_CAR_BRANDS")
public class CarBrand implements Persistent{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_brands_seq")
    @SequenceGenerator(name = "car_brands_seq", sequenceName = "sq_car_brand", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "CREATION_YEAR", nullable = false)
    private Integer creationYear;

    @OneToMany(mappedBy = "carBrand")
    private List<CarModel> carModels;

    @OneToMany(mappedBy = "brand")
    private List<Accessory> accessories;

    public CarBrand(){
        this.carModels = new ArrayList<>();
        this.accessories = new ArrayList<>();
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

    public Integer getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(Integer creationYear) {
        this.creationYear = creationYear;
    }

    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }
}
