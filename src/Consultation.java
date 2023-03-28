import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Consultation {
    private Doctor doctor;
    private Patient patient;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private double cost;
    private byte[] encryptedImage;
    private byte[] encryptedNote;
    private static ArrayList<Consultation> allConsultations = new ArrayList<Consultation>();

    public Consultation(Doctor doctor, Patient patient, LocalDate date, LocalTime startTime, LocalTime endTime,
            double cost, byte[] encryptedNote) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.encryptedNote = encryptedNote;
    }

    public Consultation(Doctor doctor, Patient patient, LocalDate date, LocalTime startTime, LocalTime endTime,
            double cost, byte[] encryptedNote, byte[] encryptedImage) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.encryptedNote = encryptedNote;
        this.encryptedImage = encryptedImage;
    }

    public static ArrayList<Consultation> getAllConsultations() {
        return allConsultations;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public byte[] getEncryptedImage() {
        return encryptedImage;
    }

    public void setEncryptedImage(byte[] encryptedImage) {
        this.encryptedImage = encryptedImage;
    }

    @Override
    public String toString() {
        return "Consultation details:" +
                "\n" + "\tDoctor: " + doctor +
                "\n" + "\tPatient: " + patient +
                "\n" + "\tDate: " + date +
                "\n" + "\tStart time: " + startTime +
                "\n" + "\tEnd time: " + endTime +
                "\n" + "\tCost: " + cost +
                "\n";
    }
}
