import br.com.j_fborges.NameSorter;

/**
 *
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome, Lets sort names through Java streams");

        NameSorter sorter = new NameSorter();

        sorter.loadNames();
        sorter.sortAndPrintNames();
        sorter.printMasculineNames();
        sorter.printFeminineNames();
    }
}

