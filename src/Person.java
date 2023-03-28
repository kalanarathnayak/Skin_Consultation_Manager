import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    private String name;
    private String surName;
    private LocalDate birthDate;
    private String phoneNumber;

    public Person(String name, String surName, LocalDate birthDate, String phoneNumber) {
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
