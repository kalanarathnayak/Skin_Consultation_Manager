import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    JButton viewDocBtn;
    JButton addConsultBtn;
    JButton viewConBtn;
    public App (){
        this.setTitle("Westminster Skin Consultation Manger");
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(320,100,800,600);
        this.setResizable(false);
        JLabel background=new JLabel(new ImageIcon("/Users/kalanarathnayake/IdeaProjects/w1903043_OOP_CW/src/images/HomePageBg.png"));
        this.add(background);
        background.setBounds(0,0,800,600);
        viewDocBtn = new JButton("View All Doctors");
        viewDocBtn.setBounds(520, 200, 200, 50);
        viewDocBtn.setBackground(new Color(252, 169, 139, 255));
        viewDocBtn.setForeground(new Color(255, 255, 255, 255));
        viewDocBtn.setOpaque(true);
        viewDocBtn.setBorderPainted(true);
        viewDocBtn.setBorder(null);
        viewDocBtn.setFont(new Font("Fira Sans", Font.PLAIN, 18));
        background.add(viewDocBtn);
        viewDocBtnHandler handler = new viewDocBtnHandler();
        viewDocBtn.addActionListener(handler);
        addConsultBtn = new JButton("Add Consultation");
        addConsultBtn.setBounds(520, 300, 200, 50);
        addConsultBtn.setBackground(new Color(252, 169, 139, 255));
        addConsultBtn.setForeground(new Color(255, 255, 255, 255));
        addConsultBtn.setOpaque(true);
        addConsultBtn.setBorderPainted(true);
        addConsultBtn.setBorder(null);
        addConsultBtn.setFont(new Font("Fira Sans", Font.PLAIN, 18));
        background.add(addConsultBtn);
        addConsultBtnHandler handler1 = new addConsultBtnHandler();
        addConsultBtn.addActionListener(handler1);
        viewConBtn = new JButton("View Consultation");
        viewConBtn.setBounds(520, 400, 200, 50);
        viewConBtn.setBackground(new Color(252, 169, 139, 255));
        viewConBtn.setForeground(new Color(255, 255, 255, 255));
        viewConBtn.setOpaque(true);
        viewConBtn.setBorderPainted(true);
        viewConBtn.setBorder(null);
        viewConBtn.setFont(new Font("Fira Sans", Font.PLAIN, 18));
        background.add(viewConBtn);
        viewConsultBtnHandler handler2 = new viewConsultBtnHandler();
        viewConBtn.addActionListener(handler2);
    }
    private class viewDocBtnHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewDocBtn) {
                dispose();
                new DoctorTable();
            }
        }
    }

    private class addConsultBtnHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addConsultBtn) {
                dispose();
                new CheckConsultation();
            }
        }
    }

    private class viewConsultBtnHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewConBtn) {
                dispose();
                new ViewConsultation();
            }
        }
    }

}
