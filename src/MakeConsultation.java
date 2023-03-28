import javax.crypto.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class MakeConsultation extends JFrame {

    private static BufferedImage image;
    byte[] encryptedImageData;
    SecretKey key;
    KeyPair pair;
    JFormattedTextField name;
    JFormattedTextField surName;
    JFormattedTextField birthDate;
    JFormattedTextField phoneNumber;
    JFormattedTextField patientID;
    JFormattedTextField cost;
    JTextArea notes;
    JButton upload;
    JButton costGetter;
    JButton submit;

    public MakeConsultation() {
        this.setTitle("Make a consultation");
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(320,100,1000,1000);
        this.setResizable(false);
        JLabel background=new JLabel(new ImageIcon("/Users/kalanarathnayake/IdeaProjects/w1903043_OOP_CW/src/images/MakeConBg.png"));
        background.setBounds(0,0,1000,1000);
        this.add(background);
        JLabel title = new JLabel("Patient Details");
        title.setBounds(400, 50, 300, 50);
        title.setFont(new Font("Glacial Indifference", Font.PLAIN, 30));
        title.setForeground(new Color(0, 0, 0, 255));
        background.add(title);
        background.add(label("Name *", 150, 150));
        background.add(label("Surname *", 550, 150));
        background.add(label("Date of Birth *", 150, 300));
        background.add(label("Phone Number *", 550, 300));
        background.add(label("Patient ID *", 150, 450));
        background.add(label("Cost", 550, 450));
        background.add(label("Notes", 150, 600));
        background.add(queriesName());
        background.add(queriesSurName());
        background.add(queriesBirthDate());
        background.add(queriesPhoneNumber());
        background.add(queriesPatientID());
        background.add(queriesCost());
        background.add(queriesNotes());
        background.add(getCostGetter());
        background.add(upload());
        background.add(submit());
    }

    public JLabel label (String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 300, 50);
        label.setFont(new Font("Fira Sans", Font.PLAIN, 18));
        label.setForeground(new Color(0, 0, 0, 255));
        return label;
    }

    public JFormattedTextField queriesName() {
        name = new HintTextField("John");
        name.setBounds(150, 200, 300, 50);
        name.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Focus.focusOnTextField(name);
        return name;
    }

    public JFormattedTextField queriesSurName() {
        surName = new HintTextField("Appleseed");
        surName.setBounds(550, 200, 300, 50);
        surName.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Focus.focusOnTextField(surName);
        return surName;
    }

    public JFormattedTextField queriesBirthDate() {
        birthDate = new HintTextField("01/01/2000");
        birthDate.setBounds(150, 350, 300, 50);
        birthDate.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Focus.focusOnTextField(birthDate);
        return birthDate;
    }

    public JFormattedTextField queriesPhoneNumber() {
        phoneNumber = new HintTextField("0712345678");
        phoneNumber.setBounds(550, 350, 300, 50);
        phoneNumber.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Focus.focusOnTextField(phoneNumber);
        return phoneNumber;
    }

    public JFormattedTextField queriesPatientID() {
        patientID = new HintTextField("123456");
        patientID.setBounds(150, 500, 300, 50);
        patientID.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Focus.focusOnTextField(patientID);
        return patientID;
    }

    public JFormattedTextField queriesCost() {
        cost = new HintTextField("£100");
        cost.setBounds(550, 500, 300, 50);
        cost.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Focus.focusOnTextField(cost);
        cost.setEditable(false);
        return cost;
    }

    public String getCost () {
        String cost1 = cost.getText();
        String[] costs = cost1.split("£");
        return costs[1];
    }

    public JTextArea queriesNotes() {
        notes = new JTextArea();
        notes.setBounds(150, 650, 300, 100);
        notes.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return notes;
    }

    public JButton upload() {
        //https://www.geeksforgeeks.org/encrypt-and-decrypt-image-using-java/
        upload = new JButton("Upload Images");
        upload.setBounds(150, 775, 300, 50);
        upload.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    image = ImageIO.read(selectedFile);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(image, "jpg", baos);
                    baos.flush();
                    byte[] imageData = baos.toByteArray();
                    baos.close();
                    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                    keyGenerator.init(128);
                    key = keyGenerator.generateKey();

                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    encryptedImageData = cipher.doFinal(imageData);

                } catch (IOException | NoSuchPaddingException | IllegalBlockSizeException |
                         NoSuchAlgorithmException | BadPaddingException | InvalidKeyException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return upload;
    }

    public void setTextCost() {
        LocalTime startTime = CheckConsultation.getStartTime();
        LocalTime endTime = CheckConsultation.getEndTime();
        assert startTime != null;
        int duration = (int) Duration.between(startTime, endTime).toHours();
        //loop in consultation array list to count the same patient id
        int count = 0;
        for (Consultation consultation : Consultation.getAllConsultations()) {
            if (consultation.getPatient().getPatientID().equals(patientID.getText())) {
                count++;
            }
        }
        if (count == 0) {
            cost.setText("£" + (duration * 15));
        } else {
            cost.setText("£" + (duration * 25));
        }
    }

    public JButton getCostGetter () {
        costGetter = new JButton("get Cost");
        costGetter.setBounds(550,600,300,50);
        costGetter.addActionListener(e -> setTextCost());
        return costGetter;
    }

    public boolean isFilled() {
        if (phoneNumber.getText().equals("") || name.getText().equals("") || surName.getText().equals("") || birthDate.getText().equals("") || patientID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill all the mandatory fields marked by * mark");
            return false;
        }  else {
            return true;
        }
    }

    //check if the entered phone number contains only numbers
    public boolean phNoIsNumeric() {
        return phoneNumber.getText().matches("[0-9]+") && phoneNumber.getText().length() == 10;
    }

    //check if the entered patient id contains only numbers
    public int patientIDIsNumeric() {
        try {
            return Integer.parseInt(patientID.getText());
        }catch (NumberFormatException e) {
            return 0;
        }
    }

    //check if entered name contains only letters
    public boolean nameIsAlpha() {
        for (char c : name.getText().toCharArray()) {
            if (!Character.isLetter(c))
                return false;
        }
        return true;
    }

    //check if entered surname contains only letters
    public boolean surNameIsAlpha() {
        for (char c : surName.getText().toCharArray()) {
            if (!Character.isLetter(c))
                return false;
        }
        return true;
    }

    //check if entered birthdate is in the correct format
    public LocalDate getBirthdate() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(birthDate.getText(), formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    //if note is not filled add a default note
    public String getNotes() {
        if (notes.getText().equals("")) {
            return "No notes";
        } else {
            return notes.getText();
        }
    }

    public byte[] getEncryptedNoteData() {
        byte[] cipherText;
        try {
            //https://www.tutorialspoint.com/java_cryptography/java_cryptography_decrypting_data.htm

            //Creating KeyPair generator object
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

            //Initializing the key pair generator
            keyPairGen.initialize(2048);

            //Generate the pair of keys
            pair = keyPairGen.generateKeyPair();

            //Getting the public key from the key pair
            PublicKey publicKey = pair.getPublic();

            //Creating a Cipher object
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            //Initializing a Cipher object
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            //Add data to the cipher
            byte [] input = getNotes().getBytes();
            cipher.update(input);

            //encrypting the data
            cipherText = cipher.doFinal();
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return cipherText;
    }
    //show error message if the entered phone number, patient id, name, surname or birthdate is not in the correct format
    public boolean isDetailsValid(){
        String errorMessage = "";
        if (isFilled()) {
            if (!phNoIsNumeric()) {
                errorMessage += "Invalid phone number\n";
            }
            if (patientIDIsNumeric() == 0) {
                errorMessage += "Invalid patient id\n";
            }
            if (!nameIsAlpha()) {
                errorMessage += "Invalid name\n";
            }
            if (!surNameIsAlpha()) {
                errorMessage += "Invalid surname\n";
            }
            if (getBirthdate() == null) {
                errorMessage += "Invalid birthdate (dd/mm/yyyy)\n";
            }
            if (Objects.equals(cost.getText(), "")){
                errorMessage += "get cost by pressing get cost button\n";
            }
            if (!errorMessage.equals("")) {
                JOptionPane.showMessageDialog(null, errorMessage);
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }


    public Doctor getDoc () {
        for (Doctor doctor : WestminsterSkinConsultationManager.getDoctors()) {
            if (doctor.getLicenceNumber().equals(CheckConsultation.getDoctorName())) {
                return doctor;
            }
        }
        return null;
    }
    //create a button to submit details if isDetailsValid() returns true
    public JButton submit() {
        submit = new JButton("Submit");
        submit.setBounds(550, 775, 300, 50);
        LocalDate dob = getBirthdate();
        submit.addActionListener(e -> {
            if (isDetailsValid()) {
                //create a new patient object
                Patient patient = new Patient(name.getText(), surName.getText(), dob, phoneNumber.getText(), patientID.getText());
                //create a new consultation object
                Consultation consultation;
                if (encryptedImageData != null) {
                    consultation = new Consultation(getDoc(), patient, CheckConsultation.getDate(), CheckConsultation.getStartTime(), CheckConsultation.getEndTime(), Double.parseDouble(getCost()), getEncryptedNoteData(), encryptedImageData);
                    encryptedImageData = null;
                } else {
                    consultation = new Consultation(getDoc(), patient, CheckConsultation.getDate(), CheckConsultation.getStartTime(), CheckConsultation.getEndTime(), Double.parseDouble(getCost()), getEncryptedNoteData());
                }
                Consultation.getAllConsultations().add(consultation);
                //close the frame
                dispose();
                JOptionPane.showMessageDialog(null, "Consultation added");
                new App();
            }
        });
        return submit;
    }


}

