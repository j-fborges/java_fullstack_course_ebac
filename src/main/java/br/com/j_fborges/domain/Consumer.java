package br.com.j_fborges.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TB_CONSUMER")
public class Consumer implements Persistent{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consumer_seq")
    @SequenceGenerator(name = "consumer_seq", sequenceName = "sq_consumer", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "ID_NUMBER", nullable = false, unique = true)
    private Long idNumber;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "TELEPHONE", nullable = false)
    private Long tel;

    @Column(name = "ADDRESS", nullable = false, length = 100)
    private String address;

    @Column(name = "ADDRESS_NUMBER", nullable = false)
    private Integer addressNumber;

    @Column(name = "CITY", nullable = false, length = 100)
    private String city;

    @Column(name = "STATE", nullable = false, length = 50)
    private String state;

    public Consumer(String id, String name, String idNumber, String email, String tel, String address, String addressNumber, String city, String state) {
        this.id = Long.valueOf(id);
        this.name = name;
        this.idNumber = Long.valueOf(idNumber);
        this.email = email;
        this.tel = Long.valueOf(tel);
        this.address = address;
        this.addressNumber = Integer.valueOf(addressNumber);
        this.city = city;
        this.state = state;
    }

    public Consumer( String name, String idNumber, String email, String tel, String address, String addressNumber, String city, String state) {
        this.name = name;
        this.idNumber = Long.valueOf(idNumber);
        this.email = email;
        this.tel = Long.valueOf(tel);
        this.address = address;
        this.addressNumber = Integer.valueOf(addressNumber);
        this.city = city;
        this.state = state;
    }

    public Consumer() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Integer addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(idNumber, consumer.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idNumber);
    }


    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
