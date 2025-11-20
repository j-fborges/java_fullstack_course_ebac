package br.com.j_fborges;

import java.util.Objects;

public class NameEntry implements Comparable<NameEntry> {
    public NameEntry(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public NameEntry(NameEntry entry) {
        this.name = entry.name;
        this.gender = entry.gender;
    }

    public String genderToFullString(String genderString) {
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

    private String gender = "";

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NameEntry nameEntry = (NameEntry) o;
        return Objects.equals(name, nameEntry.name) && Objects.equals(gender, nameEntry.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender);
    }
}
