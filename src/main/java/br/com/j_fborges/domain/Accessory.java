package main.java.br.com.j_fborges.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TST_ACCESSORIES")
public class Accessory implements Persistent{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accessories_seq")
    @SequenceGenerator(name = "accessories_seq", sequenceName = "sq_accessory", initialValue = 1, allocationSize = 1)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "id_car_brand_fk",
            foreignKey = @ForeignKey(name = "fk_car_brand"),
            referencedColumnName = "id", nullable = false
    )
    private CarBrand brand;

    @ManyToMany(mappedBy = "accessories")
    private List<Car> cars;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Accessory(){
        this.cars = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
