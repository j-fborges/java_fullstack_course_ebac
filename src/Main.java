

import br.com.j_fborges.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class Main {

    @Table(value = "Indeed, a Table")
    List<Integer> list = createIntTable();

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {

        System.out.println("Hello and welcome, Lets create annotations.");

        Main mainObject = new Main();
        Annotation[] a = mainObject.getClass().getMethod("createIntTable").getAnnotations();

        for (Annotation ann : a) {
            System.out.println("Annotation: " + ann.toString());
            System.out.println("Annotation: " + ann.annotationType());
            System.out.println("Annotation: " + ann);
        }

        System.out.println(mainObject.getClass().getMethod("createIntTable").getAnnotation(Table.class).value());
        System.out.println(mainObject.getClass().getDeclaredField("list").getAnnotation(Table.class).value());
    }

    @Table(value = "Yes, a Table")
    public static ArrayList<Integer> createIntTable() {
        return new ArrayList<Integer>();
    }

}

