package br.com.j_fborges;

public abstract class Person {

    private String name = "";
    private Integer birthYear = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Person(String name, Integer birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }
}
