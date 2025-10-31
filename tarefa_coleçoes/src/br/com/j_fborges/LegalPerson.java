package br.com.j_fborges;

import java.util.ArrayList;
import java.util.List;

public class LegalPerson extends Person{

    private List<String> foundersNames = new ArrayList<String>();

    private Long companyRegistrationNumber = null;

    public List<String> getFoundersNames() {
        return foundersNames;
    }

    public void setFoundersNames(List<String> foundersNames) {
        this.foundersNames = foundersNames;
    }

    public Long getCompanyRegistrationNumber() {
        return companyRegistrationNumber;
    }

    public void setCompanyRegistrationNumber(Long companyRegistrationNumber) {
        this.companyRegistrationNumber = companyRegistrationNumber;
    }

    public LegalPerson(String name, Integer birthYear, String foundersNames, Long companyRegistrationNumber) {
        super(name, birthYear);

        String[] parsedNames = foundersNames.split(",");
        List<String> namesList = new ArrayList<String>();

        for(String founderName : parsedNames){
            namesList.add(founderName);
        }

        this.foundersNames = namesList;
        this.companyRegistrationNumber = companyRegistrationNumber;
    }

    public void addFounder(String founder){
        List<String> founders = getFoundersNames();
        founders.add(founder);
        setFoundersNames(founders);
    }

    @Override
    public String toString() {
        return "LegalPerson{" +
                "name='" + getName() + '\'' +
                ", birthYear=" + getBirthYear()+
                ", foundersNames=" + foundersNames +
                ", companyRegistrationNumber=" + companyRegistrationNumber +
                '}';
    }
}
