import java.time.LocalDate;

public class Patient extends Person{
    private String patientID;

    public Patient(String name, String surName, LocalDate birthDate, String phoneNumber, String patientID) {
        super(name, surName, birthDate, phoneNumber);
        this.patientID = patientID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
}
