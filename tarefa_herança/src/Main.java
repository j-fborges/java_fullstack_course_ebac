import br.com.j_fborges.LegalPerson;
import br.com.j_fborges.NaturalPerson;

/**
 *
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome, Lets create natural and legal people");

        NaturalPerson person = new NaturalPerson(8338393448L, "Johnny Hooks", 1990, "Sammantha Hooks", "Bobby Hooks");


        LegalPerson company = new LegalPerson("Shirley`s Burguers", 2015, "Shirley Bailey,Mohammed Ashtok", 57852697759985544L);

        company.addFounder("Carla Tavares");

        System.out.println(person);
        System.out.println(company);

    }
}

