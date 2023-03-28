import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Doctor extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = -299482035708790407L;
    private String specialization;
    private String licenceNumber;


    public Doctor(String name, String surName, LocalDate birthDate, String phoneNumber, String specialization, String licenceNumber) {
        super(name, surName, birthDate, phoneNumber);
        this.specialization = specialization;
        this.licenceNumber = licenceNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    @Override
    public String toString() {
        return "\tFirst name: " + super.getName() +
                "\n" + "\tSurname: " + super.getSurName() +
                "\n" + "\tDate of Birth: " + super.getBirthDate() +
                "\n" + "\tPhone number: " + super.getPhoneNumber() +
                "\n" + "\tSpecialization: " + specialization +
                "\n" + "\tLicence number: " + licenceNumber +
                "\n";
    }
}

