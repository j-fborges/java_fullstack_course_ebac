package br.com.j_fborges;

public class NaturalPerson extends Person {

    private Long idNumber = 0L;

    private String motherName = "";
    private String fatherName = "";

    public NaturalPerson(long idNumber, String name, int birthYear, String motherName, String fatherName) {
        super(name, birthYear);
        this.idNumber = idNumber;
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

    @Override
    public String toString() {

        return "NaturalPerson{" +
                "name='" + getName() + '\'' +
                ", birthYear=" + getBirthYear() +
                ", idNumber=" + idNumber +
                ", motherName='" + motherName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                '}';
    }
}
