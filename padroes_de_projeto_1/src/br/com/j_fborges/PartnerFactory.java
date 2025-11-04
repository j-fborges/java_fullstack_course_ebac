package br.com.j_fborges;

public class PartnerFactory extends AbstractCarFactory{

    @Override
    Car retrieveCar(String requestedGrade) {

        if ("A".equals(requestedGrade)) {
            return new CorolaCar(100, "gas", "red");
        } else {
            return null;
        }
    }
}
