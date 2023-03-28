import javax.swing.*;
import java.awt.*;

public class ViewConsultation extends JFrame{
    JFormattedTextField patientID;
    JButton view;
    JButton backBtn;

    public ViewConsultation() {
        this.setTitle("Make a consultation");
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(320,100,1000,1000);
        this.setResizable(false);
        JLabel background=new JLabel(new ImageIcon("/Users/kalanarathnayake/IdeaProjects/w1903043_OOP_CW/src/images/MakeConBg.png"));
        background.setBounds(0,0,1000,1000);
        this.add(background);
        JLabel title = new JLabel("Consultation Details");
        title.setBounds(350, 50, 300, 50);
        title.setFont(new Font("Glacial Indifference", Font.PLAIN, 30));
        title.setForeground(new Color(0, 0, 0, 255));
        background.add(title);
        background.add(queriesPatientID());
        background.add(label("Patient ID",250,150));
        background.add(getView());
        background.add(backBtn());

    }

    public JLabel label (String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 300, 50);
        label.setFont(new Font("Fira Sans", Font.PLAIN, 18));
        label.setForeground(new Color(0, 0, 0, 255));
        return label;
    }
    public JFormattedTextField queriesPatientID() {
        patientID = new HintTextField("123456");
        patientID.setBounds(400, 150, 200, 50);
        patientID.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Focus.focusOnTextField(patientID);
        return patientID;
    }

    public JButton getView () {
        view = new JButton("View");
        view.setBounds(700,150,70,50);
        view.setBackground(new Color(252, 169, 139, 255));
        view.setForeground(new Color(255, 255, 255, 255));
        view.setOpaque(true);
        view.setBorderPainted(true);
        view.setBorder(null);
        view.setFont(new Font("Fira Sans", Font.PLAIN, 18));
        return view;
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

}
