import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class CheckConsultation extends JFrame {
    static JComboBox<String> doctorNames;
    static JFormattedTextField date;
    static JFormattedTextField startTime;
    static JFormattedTextField endTime;
    JButton backBtn;
    JButton checkBtn;
    public CheckConsultation() {
        this.setTitle("Check Consultation");
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(320, 100, 900, 700);
        this.setResizable(false);
        JLabel background = new JLabel(new ImageIcon("/Users/kalanarathnayake/IdeaProjects/w1903043_OOP_CW/src/images/doctorTableBg.png"));
        background.setBounds(0, 0, 900, 700);
        this.add(background);
        JLabel title = new JLabel("Find a Doctor, Book an Appointment!");
        title.setBounds(180, 50, 600, 50);
        title.setFont(new Font("Glacial Indifference", Font.PLAIN, 30));
        title.setForeground(new Color(0, 0, 0, 255));
        background.add(title);
        background.add(backBtn());
        background.add(queriesConsultation());
        background.add(queriesDate());
        background.add(queriesStartTime());
        background.add(queriesEndTime());
        background.add(label("Doctor", 300, 163));
        background.add(label("Consultation Date",300,250));
        background.add(label("Start Time",300,350));
        background.add(label("End Time",300,450));
        background.add(checkBtn());

    }

    public JComboBox<String> queriesConsultation() {
        //add all doctor names to the combo box
        doctorNames = new JComboBox<>();
        for (Doctor doctor : WestminsterSkinConsultationManager.getDoctors()) {
            doctorNames.addItem(doctor.getName() + " " + doctor.getSurName() + "-" +doctor.getLicenceNumber());
        }
        doctorNames.setBounds(295, 200, 310, 50);
        return doctorNames;
    }

    public JFormattedTextField queriesDate() {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date = new HintTextField(dateObj.format(formatter));
        date.setBounds(300, 300, 300, 50);
        date.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Focus.focusOnTextField(date);
        return date;
    }

    public JFormattedTextField queriesStartTime() {
        startTime = new HintTextField("4:00 PM");
        startTime.setBounds(300, 400, 300, 50);
        startTime.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Focus.focusOnTextField(startTime);
        return startTime;
    }

    public JFormattedTextField queriesEndTime() {
        endTime = new HintTextField("5:00 PM");
        endTime.setBounds(300, 500, 300, 50);
        endTime.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Focus.focusOnTextField(endTime);
        return endTime;
    }

    public JButton backBtn() {
        Icon icon = new ImageIcon("/Users/kalanarathnayake/IdeaProjects/w1903043_OOP_CW/src/images/bckBtn.png");
        backBtn = new JButton(icon);
        backBtn.setOpaque(false);
        backBtn.setBorderPainted(false);
        backBtn.setFocusable(false);
        backBtn.setBounds(15, 20, 70, 70);
        backBtn.addActionListener(e -> {
            this.dispose();
            new App();
        });
        return backBtn;
    }

    public JLabel label (String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 300, 50);
        label.setFont(new Font("Fira Sans", Font.PLAIN, 14));
        label.setForeground(new Color(0, 0, 0, 255));
        return label;
    }

    //get the selected doctor name from the combo box
    public static String getDoctorName() {
        String name =  Objects.requireNonNull(doctorNames.getSelectedItem()).toString();
        String[] names = name.split("-");
        return names[1];
    }

    //get the date from the text field and convert it to a LocalDate object and if the date is invalid return null
    public static LocalDate getDate() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date.getText(), formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    //get the start time from the text field and convert it to a LocalTime object and if the time is invalid return null
    public static LocalTime getStartTime() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
            return LocalTime.parse(startTime.getText(), formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    //get the end time from the text field and convert it to a LocalTime object and if the time is invalid return null
    public static LocalTime getEndTime() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
            return LocalTime.parse(endTime.getText(), formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public boolean isFilled() {
        if (getDoctorName().equals("") || date.getText().equals("") || startTime.getText().equals("") || endTime.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        }  else {
            return true;
        }
    }

    public boolean isDetailsValid(){
        String errorMessage = "";
        if (isFilled()) {
            if (getDate() == null) {
                errorMessage += "Invalid date\n";
            }
            if (getStartTime() == null) {
                errorMessage += "Invalid start time\n";
            }
            if (getEndTime() == null) {
                errorMessage += "Invalid end time\n";
            }
            if(!errorMessage.equals("")){
                JOptionPane.showMessageDialog(null, errorMessage);
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //if isDetailsValid() returns true, check if the start time is before the end time and start time is after 4:00 PM and end time is before 10:00 PM
    //and the time between the start time and end time should be at least 1 hour
    public boolean isTimeValid() {
        if (isDetailsValid()) {
            if (Objects.requireNonNull(getDate()).isAfter(LocalDate.now()) || getDate().isEqual(LocalDate.now())) {
                if (Objects.requireNonNull(getStartTime()).isBefore(getEndTime())) {
                    if (getStartTime().isAfter(LocalTime.of(15, 59)) && Objects.requireNonNull(getEndTime()).isBefore(LocalTime.of(22, 1))) {
                        if (getStartTime().until(getEndTime(), ChronoUnit.HOURS) >= 1) {
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(null, "The time between the start time and end time should be at least 1 hour");
                            return false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "The clinic is only open from 4:00 PM to 10:00 PM");
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "The start time should be before the end time");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "The date should be today or after today");
                return false;
            }
        }
        return false;
    }


    //check if the doctor is available on the selected date and time and don't allow the user to book an appointment if the doctor is not available
   //if there isn't any consultations in the array list don't check the availability
    public boolean isAvailable() {
        if (Consultation.getAllConsultations().size() != 0) {
            for (Consultation consultation : Consultation.getAllConsultations()) {
                if (consultation.getDoctor().getLicenceNumber().equals(getDoctorName())) {
                    if (consultation.getDate().equals(getDate())) {
                        if (consultation.getStartTime().isBefore(getStartTime()) && consultation.getEndTime().isAfter(getStartTime())) {
                            JOptionPane.showMessageDialog(null, "The doctor is not available at the selected time");
                            return false;
                        } else if (consultation.getStartTime().isBefore(getEndTime()) && consultation.getEndTime().isAfter(getEndTime())) {
                            JOptionPane.showMessageDialog(null, "The doctor is not available at the selected time");
                            return false;
                        } else if (consultation.getStartTime().equals(getStartTime()) && consultation.getEndTime().equals(getEndTime())) {
                            JOptionPane.showMessageDialog(null, "The doctor is not available at the selected time");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    //select an available doctor at random for that time and date
    public void selectAvailableDoctor() {
        ArrayList<Doctor> availableDoctors = new ArrayList<>();
        for (Doctor doctor : WestminsterSkinConsultationManager.getDoctors()) {
            if (!doctor.getLicenceNumber().equals(getDoctorName())) {
                if (Consultation.getAllConsultations().size() != 0) {
                    for (Consultation consultation : Consultation.getAllConsultations()) {
                        if (consultation.getDoctor().getLicenceNumber().equals(doctor.getLicenceNumber())) {
                            if (consultation.getDate().equals(getDate())) {
                                if (consultation.getStartTime().isBefore(getStartTime()) && consultation.getEndTime().isAfter(getStartTime())) {
                                    break;
                                } else if (consultation.getStartTime().isBefore(getEndTime()) && consultation.getEndTime().isAfter(getEndTime())) {
                                    break;
                                } else if (consultation.getStartTime().equals(getStartTime()) && consultation.getEndTime().equals(getEndTime())) {
                                    break;
                                }
                            }
                        }
                    }
                }
                availableDoctors.add(doctor);
            }
        }
        if (availableDoctors.size() != 0) {
            Random random = new Random();
            int index = random.nextInt(availableDoctors.size());
            JOptionPane.showMessageDialog(null, "The doctor is not available at the selected time Dr."
                    + availableDoctors.get(index).getName() + " " + availableDoctors.get(index).getSurName() + " has been selected instead");
            //set the available doctor and set it to the doctor combo box
            doctorNames.setSelectedItem(availableDoctors.get(index).getName() + " " + availableDoctors.get(index).getSurName() + "-" +availableDoctors.get(index).getLicenceNumber());
            dispose();
            new MakeConsultation();
        } else {
            JOptionPane.showMessageDialog(null, "There are no available doctors at the selected time");
        }
    }


    //create checkBtn and add an action listener to it to check if the doctor is available on the selected date and time or if the text fields are filled
    public JButton checkBtn() {
        checkBtn = new JButton("Check Availability");
        checkBtn.setFont(new Font("Fira Sans", Font.PLAIN, 18));
        checkBtn.setForeground(new Color(255, 255, 255, 255));
        checkBtn.setBackground(new Color(252, 169, 139, 255));
        checkBtn.setOpaque(true);
        checkBtn.setBorderPainted(true);
        checkBtn.setBorder(null);
        checkBtn.setFocusable(false);
        checkBtn.setBounds(300, 600, 300, 50);
        checkBtn.addActionListener(e -> {
            if (isTimeValid()) {
                if (isAvailable()) {
                    JOptionPane.showMessageDialog(null, "The doctor is available at the selected time");
                    dispose();
                    new MakeConsultation();
                } else {
                    selectAvailableDoctor();
                }
            }
        });
        return checkBtn;
    }

}
