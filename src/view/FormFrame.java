/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 25-Dec-19
Time: 11:10 PM
Lau ji Ghauri aya fir
*/

package view;

import model.Employee;
import model.Lecturer;
import model.SecurityGuard;
import util.IconRenderer;
import util.Prefs;
import view.Interface.FormListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormFrame extends JFrame implements ActionListener {

    private JTextField nameField;
    private JTextField ageField;
    private JTextField salaryField;
    private JTextField courseHourlyRateField;
    private JTextField totalCourseHourField;

    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel salaryLabel;
    private JLabel courseHourlyRateLabel;
    private JLabel totalCourseHourLabel;

    private JButton saveButton;
    private JButton cancelButton;

    private JPanel formPanel;

    private static final int LECTURER = 0;
    private static final int SECURITY_GUARD = 1;
    private int formMode;
    private Component parent;

    private FormListener formListener;

    public FormFrame(Component parent){

        super();
        this.parent = parent;
        setSize(500,555);
        setIconImage(IconRenderer.getIcon("/image/insert.png", 40).getImage());
        setResizable(false);
        this.setVisible(false);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.formMode = LECTURER;

        //nameField
        this.nameField = new JTextField(15);
        this.nameField.setFont(new Font("SanSerif", Font.PLAIN, 20));
        this.nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                checkFields();
            }
        });

        //ageField
        this.ageField = new JTextField(15);
        this.ageField.setFont(new Font("SanSerif", Font.PLAIN, 20));
        this.ageField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                checkFields();
            }
        });

        //salaryField
        this.salaryField = new JTextField(15);
        this.salaryField.setFont(new Font("SanSerif", Font.PLAIN, 20));
        this.salaryField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == '.') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                checkFields();
            }
        });

        //rateField
        this.courseHourlyRateField = new JTextField(15);
        this.courseHourlyRateField.setFont(new Font("SanSerif", Font.PLAIN, 20));
        this.courseHourlyRateField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == '.') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                checkFields();
            }
        });

        //totalField
        this.totalCourseHourField = new JTextField(15);
        this.totalCourseHourField.setFont(new Font("SanSerif", Font.PLAIN, 20));
        this.totalCourseHourField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                checkFields();
            }
        });

        //labels
        this.nameLabel = new JLabel("Name: ", SwingConstants.CENTER);
        this.nameLabel.setFont(new Font("SanSerif", Font.PLAIN, 18));
        this.nameLabel.setForeground(Color.WHITE);
        this.nameLabel.setLabelFor(this.nameField);
        this.nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);

        this.ageLabel = new JLabel("Age: ", SwingConstants.CENTER);
        this.ageLabel.setFont(new Font("SanSerif", Font.PLAIN, 18));
        this.ageLabel.setForeground(Color.WHITE);
        this.ageLabel.setLabelFor(this.ageField);
        this.ageLabel.setDisplayedMnemonic(KeyEvent.VK_A);

        this.salaryLabel = new JLabel("Basic salary: ", SwingConstants.CENTER);
        this.salaryLabel.setFont(new Font("SanSerif", Font.PLAIN, 18));
        this.salaryLabel.setForeground(Color.WHITE);
        this.salaryLabel.setLabelFor(this.salaryField);
        this.salaryLabel.setDisplayedMnemonic(KeyEvent.VK_B);

        this.courseHourlyRateLabel = new JLabel("Course rate: ", SwingConstants.CENTER);
        this.courseHourlyRateLabel.setFont(new Font("SanSerif", Font.PLAIN, 18));
        this.courseHourlyRateLabel.setForeground(Color.WHITE);
        this.courseHourlyRateLabel.setLabelFor(this.courseHourlyRateField);
        this.courseHourlyRateLabel.setDisplayedMnemonic(KeyEvent.VK_R);

        this.totalCourseHourLabel = new JLabel("Total courses: ", SwingConstants.CENTER);
        this.totalCourseHourLabel.setFont(new Font("SanSerif", Font.PLAIN, 18));
        this.totalCourseHourLabel.setForeground(Color.WHITE);
        this.totalCourseHourLabel.setLabelFor(this.totalCourseHourField);
        this.totalCourseHourLabel.setDisplayedMnemonic(KeyEvent.VK_T);

        //buttons
        this.saveButton = new JButton("Save");
        this.saveButton.setOpaque(true);
        this.saveButton.setBackground(Color.ORANGE);
        this.saveButton.setForeground(Color.BLACK);
        this.saveButton.setToolTipText("Save info to database");
        this.saveButton.setFont(new Font("SanSerif", Font.BOLD, 18));
        this.saveButton.addActionListener(this);

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setOpaque(true);
        this.cancelButton.setBackground(Color.ORANGE);
        this.cancelButton.setForeground(Color.BLACK);
        this.cancelButton.setToolTipText("Cancel");
        this.cancelButton.setFont(new Font("SanSerif", Font.BOLD, 18));
        this.cancelButton.addActionListener(this);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                super.windowClosing(e);
            }
        });

        this.saveButton.setPreferredSize(this.cancelButton.getPreferredSize());
        setupLayout();
    }

    private void setupLayout(){

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        namePanel.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));
        namePanel.setBackground(Color.DARK_GRAY);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        agePanel.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));
        agePanel.setBackground(Color.DARK_GRAY);
        agePanel.add(ageLabel);
        agePanel.add(ageField);

        JPanel salaryPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        salaryPanel.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));
        salaryPanel.setBackground(Color.DARK_GRAY);
        salaryPanel.add(salaryLabel);
        salaryPanel.add(salaryField);

        JPanel ratePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        ratePanel.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));
        ratePanel.setBackground(Color.DARK_GRAY);
        ratePanel.add(courseHourlyRateLabel);
        ratePanel.add(courseHourlyRateField);

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));
        totalPanel.setBackground(Color.DARK_GRAY);
        totalPanel.add(totalCourseHourLabel);
        totalPanel.add(totalCourseHourField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(25,40,0,0));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        formPanel = new JPanel(new GridLayout(7,1));
        formPanel.setBackground(Color.DARK_GRAY);
        formPanel.add(namePanel);
        formPanel.add(agePanel);
        formPanel.add(salaryPanel);
        formPanel.add(ratePanel);
        formPanel.add(totalPanel);
        formPanel.add(buttonPanel);
        JLabel label = new JLabel("All fields must be filled", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        formPanel.add(label);

        this.setLayout(new BorderLayout());
        this.add(formPanel, BorderLayout.CENTER);
    }
    public void showForm(boolean isLecturer){

        this.setLocationRelativeTo(parent);
        this.saveButton.setEnabled(false);

        this.nameLabel.setBorder(BorderFactory.createEmptyBorder(0,62,0,0));
        this.ageLabel.setBorder(BorderFactory.createEmptyBorder(0,77,0,0));
        this.salaryLabel.setBorder(BorderFactory.createEmptyBorder(0,12,0,0));
        this.courseHourlyRateLabel.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));
        this.totalCourseHourLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        if(isLecturer){
            formMode = LECTURER;
            this.setTitle("Add new Lecturer");
            this.courseHourlyRateLabel.setText("Course rate: ");
            this.totalCourseHourLabel.setText("Total courses: ");

            formPanel.setBorder(BorderFactory.createCompoundBorder
                    (BorderFactory.createEmptyBorder(30,30,30,30),
                            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.ORANGE, 2),
                                    "Lecturer", SwingConstants.CENTER, SwingConstants.LEFT,
                                    new Font("SanSerif", Font.ITALIC|Font.BOLD,
                                            25), Color.WHITE)));
        }
        else{
            formMode = SECURITY_GUARD;
            this.setTitle("Add new Security guard");
            this.courseHourlyRateLabel.setText("Hourly rate: ");
            this.totalCourseHourLabel.setText("Total Hours: ");

            this.courseHourlyRateLabel.setBorder(BorderFactory.createEmptyBorder(0,21,0,0));
            this.totalCourseHourLabel.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));

            formPanel.setBorder(BorderFactory.createCompoundBorder
                    (BorderFactory.createEmptyBorder(30,30,30,30),
                            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.ORANGE, 2),
                                    "Security guard", SwingConstants.CENTER, SwingConstants.LEFT,
                                    new Font("SanSerif", Font.ITALIC|Font.BOLD,
                                            25), Color.WHITE)));

        }
        this.setVisible(true);
    }

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == saveButton){

            if(formListener != null){

                formListener.onClickSave(saveEmployee());
            }
            resetForm();
            this.setVisible(false);

        }
        if (e.getSource() == cancelButton){

            resetForm();
            this.setVisible(false);
        }
    }

    private void resetForm() {

        this.nameField.setText("");
        this.ageField.setText("");
        this.salaryField.setText("");
        this.courseHourlyRateField.setText("");
        this.totalCourseHourField.setText("");
    }
    private void checkFields(){

        if(nameField.getText().length() > 0 &&
                ageField.getText().length() > 0 &&
                salaryField.getText().length() > 0 &&
                courseHourlyRateField.getText().length() > 0 &&
                totalCourseHourField.getText().length() > 0){

            saveButton.setEnabled(true);
        }
        else{
            saveButton.setEnabled(false);
        }
    }
    private Employee saveEmployee(){

        if(formMode == LECTURER){

            Lecturer lecturer = new Lecturer();
            lecturer.setId(Prefs.getCount());
            lecturer.setName(nameField.getText().trim());
            lecturer.setAge(Integer.parseInt(ageField.getText().trim()));
            lecturer.setBasicSalary(Float.parseFloat(salaryField.getText().trim()));
            lecturer.setCourseRate(Float.parseFloat(courseHourlyRateField.getText().trim()));
            lecturer.setTotalCourses(Integer.parseInt(totalCourseHourField.getText().trim()));

            return lecturer;
        }
        else{

            SecurityGuard securityGuard = new SecurityGuard();
            securityGuard.setId(Prefs.getCount());
            securityGuard.setName(nameField.getText().trim());
            securityGuard.setAge(Integer.parseInt(ageField.getText().trim()));
            securityGuard.setBasicSalary(Float.parseFloat(salaryField.getText().trim()));
            securityGuard.setHourlyRate(Float.parseFloat(courseHourlyRateField.getText().trim()));
            securityGuard.setTotalHours(Integer.parseInt(totalCourseHourField.getText().trim()));

            return securityGuard;
        }
    }
}
