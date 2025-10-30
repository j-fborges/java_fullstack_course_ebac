package br.com.j_fborges;

public class NameEntry implements Comparable<NameEntry>{
    public NameEntry(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String genderToFullString( String genderString){
        return genderString.equals("M") ? "Masculine" : "Feminine";
    }

    @Override
    public String toString() {
        return "NameEntry{ " +
                "Name = '" + name + '\'' +
                ", gender = '" + genderToFullString(gender) + '\'' +
                '}';
    }

    private String name = "";
    private  String gender = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int compareTo(NameEntry other) {
        return this.name.compareTo(other.name);
    }

}
