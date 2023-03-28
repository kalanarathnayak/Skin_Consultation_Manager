import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    private static ArrayList<Doctor> doctors;
    private static final int MAX_DOCTORS = 10;

    public WestminsterSkinConsultationManager() {
        doctors = new ArrayList<>();
    }

    public static ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    @Override
    public void addDoctor(Doctor doctor) {
        if (doctors.size() < MAX_DOCTORS) {
            doctors.add(doctor);
            System.out.println("Doctor added successfully!");
        } else {
            System.out.println("Error: The maximum number of doctors has been reached!");
        }
    }

    public void getDoctorDetails() {
        Scanner scanner = new Scanner(System.in);
        String name = stringValidator("name");
        String surName = stringValidator("surname");
        LocalDate birthDate = dateValidator();
        String phoneNumber = numberValidator("phone number");
        String specialization = stringValidator("specialization");
        // check if the licence number is unique with every doctor except the first run
        String licenceNumber = null;
        if (doctors.size() > 0) {
            boolean unique = false;
            while (!unique) {
                System.out.print("Enter the doctor's licence number: ");
                licenceNumber = scanner.nextLine();
                boolean exists = false;
                for (Doctor doctor : doctors) {
                    if (doctor.getLicenceNumber().equals(licenceNumber)) {
                        exists = true;
                        System.out.println("Error: A doctor with the same licence number already exists!");
                        break;
                    }
                }
                if (!exists) {
                    unique = true;
                }
            }
        } else {
            System.out.print("Enter the doctor's licence number: ");
            licenceNumber = scanner.nextLine();
        }
        Doctor doctor = new Doctor(name, surName, birthDate, phoneNumber, specialization, licenceNumber);
        addDoctor(doctor);
    }

    @Override
    public void deleteDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the doctor's licence number: ");
        String licenceNumber;
        boolean doctorExists = false;
        while (!doctorExists) {
            licenceNumber = scanner.nextLine();
            for (int i = 0; i < doctors.size(); i++) {
                Doctor doctor = doctors.get(i);
                if (doctor.getLicenceNumber().equals(licenceNumber)) {
                    System.out.println("Doctor with medical license number " + licenceNumber
                            + " has been successfully deleted!");
                    System.out.println(doctor);
                    doctors.remove(doctor);
                    doctorExists = true;
                }
            }
            if (!doctorExists) {
                System.out.print("The doctor with the licence number " + licenceNumber
                        + " does not exist! Please enter a valid licence number: ");
            }
        }
    }

    @Override
    public void printDoctors() {
        // print all doctors alphabetically ordered by surname without considering the
        // case sensitivity temporarily without affecting the original order
        ArrayList<Doctor> sortedDoctors = new ArrayList<>(doctors);
        sortedDoctors.sort(Comparator.comparing(Doctor::getSurName, String.CASE_INSENSITIVE_ORDER));
        System.out
                .println("\nThe following doctors are currently registered in the system: \n(sorted alphabetically)\n");
        for (Doctor doctor : sortedDoctors) {
            // print number before each doctor
            System.out.println((sortedDoctors.indexOf(doctor) + 1) + ". " + doctor);
        }
    }

    @Override
    public void saveInFileDoctors() {
        try {
            FileOutputStream fos = new FileOutputStream("doctors.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Doctor doctor : doctors) {
                oos.writeObject(doctor);
            }
            oos.close();
            fos.close();
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readFromFileDoctors() {
        try {
            FileInputStream fis = new FileInputStream("doctors.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            doctors.clear();
            while (true) {
                try {
                    Doctor doctor = (Doctor) ois.readObject();
                    doctors.add(doctor);
                } catch (EOFException e) {
                    break;
                }
            }
            System.out.println("Data has been loaded to the system successfully.");
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred. File not found.");
        }
    }

    public String stringValidator(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the doctor's " + prompt + ": ");
            String string = scanner.nextLine();
            if (string.matches("[a-zA-Z\\s]+")) {
                return string;
            } else {
                System.out.println("Invalid input! Please enter a valid input: ");
            }
        }
    }

    public String numberValidator(String prompt) {
        Scanner scanner1 = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the doctor's " + prompt + ": ");
            String string = scanner1.nextLine();
            if (string.matches("[0-9]+") && string.length() == 10) {
                return string;
            } else {
                System.out.println("Invalid input! Please enter a valid input (phone number 10 digits long)");
            }
        }
    }

    public LocalDate dateValidator() {
        Scanner scanner = new Scanner(System.in);
        boolean validDate = false;
        LocalDate date = null;
        while (!validDate) {
            try {
                System.out.print("Enter the date (dd/MM/yyyy): ");
                String dateString = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                date = LocalDate.parse(dateString, formatter);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("\nInvalid date format or values! Please enter the date in the format dd/MM/yyyy");
            }
        }
        return date;
    }

    public void fileLoadReminder() {
        File tempFile = new File("doctors.txt");
        boolean exists = tempFile.exists();
        if (exists) {
            System.out.println("""
                    ---------------------------------------ATTENTION------------------------------------------
                    A file with doctors has been found in the system.
                    Do you want to load the data from the file to the system?
                    Enter 5 to load the data from the file to the system
                    or any other key to continue without loading the data from the file to the system.
                    """);
        }
    }

    private void displayMenu() {
        System.out.println("""

                -------------------------- Westminster Skin Consultation Centre --------------------------

                Please select one of the following options to proceed:

                1. Add a doctor
                2. Delete a doctor
                3. Print the list of doctors
                4. Save the list of doctors in a file
                5. Load the list of doctors from a file
                6. View GUI
                7. Exit

                ------------------------------------------------------------------------------------------
                """);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int option;
        String alert = "Error: There are no doctors to preform this operation!";
        fileLoadReminder();
        do {
            displayMenu();
            try {
                System.out.print("Enter the number of your option: ");
                option = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                option = 0;
            }
            switch (option) {
                case 1 -> getDoctorDetails();
                case 2 -> {
                    if (doctors.size() != 0) {
                        deleteDoctor();
                    } else {
                        System.out.println(alert);
                    }
                }
                case 3 -> {
                    if (doctors.size() != 0) {
                        printDoctors();
                    } else {
                        System.out.println(alert);
                    }
                }
                case 4 -> {
                    if (doctors.size() != 0) {
                        saveInFileDoctors();
                    } else {
                        System.out.println(alert);
                    }
                }
                case 5 -> readFromFileDoctors();
                case 6 -> {
                    if (doctors.size() != 0) {
                        new App();
                    } else {
                        System.out.println(alert);
                    }
                }
                case 7 -> {
                    System.out.println("Thank you for using the system!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option! Please enter a valid option.");
            }
            System.out.print("\nPress Enter key to continue...");
            Scanner enter = new Scanner(System.in);
            enter.nextLine();
        } while (true);
    }

    public static void main(String[] args) {
        new WestminsterSkinConsultationManager().run();
    }
}
