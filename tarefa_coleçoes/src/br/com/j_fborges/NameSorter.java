package br.com.j_fborges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NameSorter {


    public List<NameEntry> getNameList() {
        return nameList;
    }

    public void setNameList(List<NameEntry> nameList) {
        this.nameList = nameList;
    }

    private List<NameEntry> nameList = new ArrayList();


    public void loadNames() {
        String names = "Miguel-M,Laura-F,Rafael-M,Beatriz-F,Lucas-M,Inês-F,Gabriel-M,Isis-F,Pedro-M,Ruth-F,João-M,Raquel-F,Matheus-M,Liz-F,Henrique-M,Jade-F,Thiago-M,Ester-F,Gustavo-M,Isabel-F,Bruno-M,Mel-F,Felipe-M,Vivien-F,Daniel-M,Iris-F,Caio-M,Nair-F,Leonardo-M,Muriel-F,Diego-M,Gisele-F,André-M,Eloise-F,Vinícius-M,Belel-F,Eduardo-M,Sol-F,Rodrigo-M,Yasmim-F,Samuel-M,Brigitte-F,Victor-M,Mabel-F,Igor-M,Raíss-F,Marcelo-M,Mariel-F,Otávio-M,Betel-F,Alexandre-M,Ingrid-F,Renato-M,Noemi-F,Anderson-M,Luz-F,César-M,Helene-F,Fernando-M,Iris-F,Mateus-M,Scarlet-F,Roberto-M,Isabel-F,Caio-M,Lior-F,Adriel-M,Rachel-F,Fábio-M,Gabrielle-F,Jonas-M,Maris-F,Tiago-M,Muriel-F,Paulo-M,Mel-F,Ricardo-M,Raquel-F,Renan-M,Inês-F,Leandro-M,Yasmim-F,Antônio-M,Ester-F,Cássio-M,Liz-F,Hugo-M,Isis-F,Flávio-M,Noémi-F,Rafael-M,Gisele-F,Bruno-M,Iris-F,Thiago-M,Sol-F,Diego-M,Jade-F,Igor-M,Vivien-F,Marcelo-M,Nair-F,André-M,Mabel-F,Otávio-M,Ruth-F";
        String[] nameArray = names.split(",");
        List<NameEntry> nameList = new ArrayList();


        for (String name : nameArray) {
            String[] nameEntryArray = name.split("-");
            nameList.add(new NameEntry(nameEntryArray[0], nameEntryArray[1]));
        }

        setNameList(nameList);
        System.out.println(getNameList());
    }

    public void sortAnPrintNames() {
        List<NameEntry> nameList = getNameList();

        Collections.sort(nameList);
        System.out.println("Sorted Names:");
        System.out.println(getNameList());
    }

    public void printMasculineNames() {
        List<NameEntry> masculineNameList = new ArrayList();

        for (NameEntry name : getNameList()) {
            if (name.getGender().equals("M")) {
                masculineNameList.add(name);
            }
        }

        System.out.println("Masculine Sorted Names:");
        System.out.println(masculineNameList);
    }

    public void printFeminineNames() {
        List<NameEntry> feminineNameList = new ArrayList();

        for (NameEntry name : getNameList()) {
            if (name.getGender().equals("F")) {
                feminineNameList.add(name);
            }
        }

        System.out.println("Feminine Sorted Names:");
        System.out.println(feminineNameList);
    }
}



