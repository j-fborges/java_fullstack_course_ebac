package br.com.j_fborges.domain;

import java.util.Objects;

public class Consumer {

    private String name;
    private Long idNumber;
    private Long tel;
    private String address;
    private Integer addressNumber;
    private String city;
    private String state;

    public Consumer(String name, String idNumber, String tel, String address, String addressNumber, String city, String state) {
        this.name = name;
        this.idNumber = Long.valueOf(idNumber);
        this.tel = Long.valueOf(tel);
        this.address = address;
        this.addressNumber = Integer.valueOf(addressNumber);
        this.city = city;
        this.state = state;
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


}
