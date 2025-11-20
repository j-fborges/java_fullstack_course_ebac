package br.com.j_fborges;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class NameSorterTest {
    @BeforeClass
    public static void runBeforeAllTests() {
        System.out.println("Running Tests");
    }

    @Test
    public void getNameList() {
        NameSorter nameSorter = new NameSorter();
        List<NameEntry> expectedValue = new ArrayList<>();
        assertEquals(expectedValue, nameSorter.getNameList());
    }

    @Test
    public void setNameList() {
        NameSorter nameSorter = new NameSorter();

        List<NameEntry> expectedValue = new ArrayList<>();

        expectedValue.add(new NameEntry("Foo", "M"));
        expectedValue.add(new NameEntry("Bar", "F"));
        nameSorter.setNameList(expectedValue);
        assertEquals(expectedValue, nameSorter.getNameList());
        Assert.assertNotEquals(new ArrayList<>(), nameSorter.getNameList());
    }

    @Test
    public void loadNames() {
        NameSorter nameSorter = new NameSorter();
        String names = "Miguel-M,Laura-F,Rafael-M,Beatriz-F,Lucas-M,Inês-F,Gabriel-M,Isis-F,Pedro-M,Ruth-F,João-M,Raquel-F,Matheus-M,Liz-F,Henrique-M,Jade-F,Thiago-M,Ester-F,Gustavo-M,Isabel-F,Bruno-M,Mel-F,Felipe-M,Vivien-F,Daniel-M,Iris-F,Caio-M,Nair-F,Leonardo-M,Muriel-F,Diego-M,Gisele-F,André-M,Eloise-F,Vinícius-M,Belel-F,Eduardo-M,Sol-F,Rodrigo-M,Yasmim-F,Samuel-M,Brigitte-F,Victor-M,Mabel-F,Igor-M,Raíss-F,Marcelo-M,Mariel-F,Otávio-M,Betel-F,Alexandre-M,Ingrid-F,Renato-M,Noemi-F,Anderson-M,Luz-F,César-M,Helene-F,Fernando-M,Iris-F,Mateus-M,Scarlet-F,Roberto-M,Isabel-F,Caio-M,Lior-F,Adriel-M,Rachel-F,Fábio-M,Gabrielle-F,Jonas-M,Maris-F,Tiago-M,Muriel-F,Paulo-M,Mel-F,Ricardo-M,Raquel-F,Renan-M,Inês-F,Leandro-M,Yasmim-F,Antônio-M,Ester-F,Cássio-M,Liz-F,Hugo-M,Isis-F,Flávio-M,Noémi-F,Rafael-M,Gisele-F,Bruno-M,Iris-F,Thiago-M,Sol-F,Diego-M,Jade-F,Igor-M,Vivien-F,Marcelo-M,Nair-F,André-M,Mabel-F,Otávio-M,Ruth-F";
        String[] nameArray = names.split(",");

        List<NameEntry> expectedResult = Arrays.stream(nameArray).map(nameEntry -> {
            String[] nameEntryArray = nameEntry.split("-");
            return new NameEntry(nameEntryArray[0], nameEntryArray[1]);
        }).collect(Collectors.toCollection(ArrayList::new));
        nameSorter.loadNames();

        assertEquals(expectedResult.toString(), nameSorter.getNameList().toString());
        Assert.assertArrayEquals(expectedResult.toArray(), nameSorter.getNameList().toArray());
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void sortAnPrintNames() {
        NameSorter nameSorter = new NameSorter();

        List<NameEntry> expectedValue = new ArrayList<>();

        expectedValue.add(new NameEntry("Foo", "M"));
        expectedValue.add(new NameEntry("Bar", "F"));
        nameSorter.setNameList(expectedValue);
        nameSorter.sortAndPrintNames();
        assertEquals("All Sorted Names:" + System.lineSeparator() + "NameEntry{ Name = 'Bar', gender = 'Feminine'}, NameEntry{ Name = 'Foo', gender = 'Masculine'},", outContent.toString().trim());
    }

    @Test
    public void printMasculineNames() {
        NameSorter nameSorter = new NameSorter();

        List<NameEntry> expectedValue = new ArrayList<>();

        expectedValue.add(new NameEntry("Foo", "M"));
        expectedValue.add(new NameEntry("Bar", "F"));
        nameSorter.setNameList(expectedValue);
        nameSorter.printMasculineNames();
        assertEquals("Masculine Sorted Names:" + System.lineSeparator() + "NameEntry{ Name = 'Foo', gender = 'Masculine'},", outContent.toString().trim());
    }

    @Test
    public void printFeminineNames() {
        NameSorter nameSorter = new NameSorter();

        List<NameEntry> expectedValue = new ArrayList<>();

        expectedValue.add(new NameEntry("Foo", "M"));
        expectedValue.add(new NameEntry("Bar", "F"));
        nameSorter.setNameList(expectedValue);
        nameSorter.printFeminineNames();
        assertEquals("Feminine Sorted Names:" + System.lineSeparator() + "NameEntry{ Name = 'Bar', gender = 'Feminine'},", outContent.toString().trim());
    }
}