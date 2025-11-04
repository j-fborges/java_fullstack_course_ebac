import br.com.j_fborges.*;

/**
 *
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome.");


        Customer customer = new Customer("A", false);
        Customer customer2 = new Customer("A", true);
        AbstractCarFactory factory = getFactory(customer);
        AbstractCarFactory factory2 = getFactory(customer2);
        Car car = factory.create(customer.getGradeRequest());
        Car car2 = factory2.create(customer2.getGradeRequest());
        car.startEngine();
        car2.startEngine();

    }

    private static AbstractCarFactory getFactory(Customer customer) {
        if (customer.getHasCompanyContract()) {
            return new PartnerFactory();
        } else {
            return new CommercialFactory();
        }
    }
}

