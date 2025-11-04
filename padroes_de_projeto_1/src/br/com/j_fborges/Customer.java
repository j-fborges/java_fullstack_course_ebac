package br.com.j_fborges;

public class Customer {

    private String gradeRequest;
    private boolean hasCompanyContract;

    public Customer(String gradeRequest, boolean hasCompanyContract) {
        this.gradeRequest= gradeRequest;
        this.hasCompanyContract = hasCompanyContract;
    }

    public boolean getHasCompanyContract() {
        return hasCompanyContract;
    }

    public String getGradeRequest() {
        return gradeRequest;
    }
}
