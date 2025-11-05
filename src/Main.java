

import br.com.j_fborges.Table;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome, Lets create annotations.");

        @Table(value = "Indeed, a Table")
        List<Integer> list = createIntTable();

        System.out.println(list);

    }

    @Table(value = "Yes, a Table")
    public static ArrayList<Integer> createIntTable(){
        return new ArrayList<Integer>();
    }
}

