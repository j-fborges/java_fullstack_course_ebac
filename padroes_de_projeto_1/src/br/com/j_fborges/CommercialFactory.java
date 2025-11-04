package br.com.j_fborges;

public class CommercialFactory extends AbstractCarFactory {

    @Override
    Car retrieveCar(String requestedGrade) {
        if ("A".equals(requestedGrade)) {
            return new Brasilia(100, "gas", "blue");
        } else {
            return null;
        }
    }
}
