/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 8:33 PM
Lau ji Ghauri aya fir
*/

package view;

import controller.Controller;
import model.Employee;
import model.EmployeeType;
import model.Lecturer;
import model.SecurityGuard;
import util.IconRenderer;
import view.Interface.DialogListener;
import view.Interface.FormListener;
import view.Interface.SalaryDialogListener;
import view.Interface.ToolbarListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class MainFrame extends JFrame {

    private static final int LECTURER = 0;
    private static final int SECURITY_GUARD = 1;

    private Controller controller;
    private ToolbarPanel toolbarPanel;
    private JTabbedPane tabbedPane;
    private LecturerTablePanel lecturerTablePanel;
    private SecurityGuardTablePanel securityGuardTablePanel;
    private FormFrame formFrame;
    private DeleteDialog deleteDialog;
    private SalaryDialog salaryDialog;
    private WindowClosingDialog closingDialog;

    public MainFrame(String title){

        super(title);
        this.setIconImage(IconRenderer.getIcon("/image/database.png", 25).getImage());
        this.setFont(new Font("SanSerif", Font.PLAIN, 20));
        this.getContentPane().setBackground(Color.BLACK);
        this.setMinimumSize(new Dimension(1000,600));
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);

        this.controller = new Controller();
        startDatabase();

        //toolbarPanel
        setupToolbarPanel();

        //lecturerTablePanel
        setupLecturerTablePanel();

        //securityGuardTablePanel
        setupSecurityGuardTablePanel();

        //tabbedPane
        setupTabbedPane();

        //formFrame
        setupFormFrame();

        //deleteDialog
        setupDeleteDialog();

        //salaryDialog
        setupSalaryDialog();

        //closingDialog
        setupClosingDialog();

        //layout setup
        this.setLayout(new BorderLayout());
        this.add(this.toolbarPanel, BorderLayout.NORTH);
        this.add(this.tabbedPane, BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                closingDialog.showDialog();
                try {
                    controller.disconnectFromDatabase();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void setupToolbarPanel(){

        this.toolbarPanel = new ToolbarPanel();
        this.toolbarPanel.setToolbarListener(new ToolbarListener() {
            @Override
            public void onClickExport() {
                controller.exportToDatabase();
                new MessageDialog(MainFrame.this,
                        "Database", "Data exported to database successfully",
                        IconRenderer.getIcon("/image/success.png",40));
            }

            @Override
            public void onClickImport() {
                controller.importFromDatabase();
                lecturerTablePanel.refresh();
                securityGuardTablePanel.refresh();
                new MessageDialog(MainFrame.this,
                        "Database", "Data imported from database successfully",
                        IconRenderer.getIcon("/image/success.png",40));
            }

            @Override
            public void onClickInsert() {
                if(tabbedPane.getSelectedIndex() == 0){

                    formFrame.showForm(true);
                }
                if(tabbedPane.getSelectedIndex() == 1){

                    formFrame.showForm(false);
                }
                System.out.println("Insert");
            }

            @Override
            public void onClickDelete() {
                System.out.println(lecturerTablePanel.getSelectedRow());
                if(tabbedPane.getSelectedIndex() == 0){

                    if(lecturerTablePanel.getSelectedRow() != -1){

                        deleteDialog.showDialog();
                    }
                    else{

                        new MessageDialog(MainFrame.this,
                                "Delete", "Nothing to delete!",
                                IconRenderer.getIcon("/image/error.png",40));
                    }
                }
                if(tabbedPane.getSelectedIndex() == 1){

                    if(securityGuardTablePanel.getSelectedRow() != -1){

                        deleteDialog.showDialog();
                    }
                    else{

                        new MessageDialog(MainFrame.this,
                                "Delete", "Nothing to delete!",
                                IconRenderer.getIcon("/image/error.png",40));
                    }
                }
                System.out.println("Delete");
            }

            @Override
            public void onClickCalculate() {
                salaryDialog.showDialog();
                System.out.println("Calculate");
            }

            @Override
            public void onClickSort(int sortMode) {

                if(tabbedPane.getSelectedIndex() == 0){

                    controller.sortLecturers(sortMode);
                    lecturerTablePanel.refresh();
                }
                if(tabbedPane.getSelectedIndex() == 1){

                    controller.sortSecurityGuards(sortMode);
                    securityGuardTablePanel.refresh();
                }
            }

            @Override
            public void onClickSearch(int id) {

                if(tabbedPane.getSelectedIndex() == LECTURER){

                    boolean found = false;
                    for(int x = 0; x < controller.getLecturers().size(); x++){

                        if(lecturerTablePanel.getValue(x) == id){

                            lecturerTablePanel.setSelectedRow(x);
                            found = true;
                            break;
                        }
                    }
                    if(!found){

                        new MessageDialog(MainFrame.this,
                                "Search", "No record found!",
                                IconRenderer.getIcon("/image/error.png",40));
                    }
                }
                if(tabbedPane.getSelectedIndex() == SECURITY_GUARD){

                    boolean found = false;
                    for(int x = 0; x < controller.getSecurityGuards().size(); x++){

                        if(securityGuardTablePanel.getValue(x) == id){

                            securityGuardTablePanel.setSelectedRow(x);
                            found = true;
                            break;
                        }
                    }
                    if(!found){

                        JOptionPane.showMessageDialog(MainFrame.this,"No record found!",
                                "Search", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
    private void setupTabbedPane(){

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 20));
        tabbedPane.addTab("Lecturer", lecturerTablePanel);
        tabbedPane.addTab("Security Guard", securityGuardTablePanel);
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (tabbedPane.getSelectedIndex() == LECTURER){

                    lecturerTablePanel.refresh();
                }
                if(tabbedPane.getSelectedIndex() == SECURITY_GUARD){

                    securityGuardTablePanel.refresh();
                }
            }
        });
    }

    private void setupLecturerTablePanel(){

        this.lecturerTablePanel = new LecturerTablePanel();
        this.lecturerTablePanel.setData(controller.getLecturers());
    }

    private void setupSecurityGuardTablePanel(){

        this.securityGuardTablePanel = new SecurityGuardTablePanel();
        this.securityGuardTablePanel.setData(controller.getSecurityGuards());
    }

    private void setupFormFrame(){

        this.formFrame = new FormFrame(this);
        this.formFrame.setFormListener(new FormListener() {
            @Override
            public void onClickSave(Employee employee) {

                if(employee instanceof Lecturer){

                    Lecturer lecturer = (Lecturer) employee;
                    controller.addLecturer(lecturer);
                    lecturerTablePanel.refresh();
                    System.out.println(lecturer.toString());
                }
                if(employee instanceof SecurityGuard){

                    SecurityGuard securityGuard = (SecurityGuard) employee;
                    controller.addSecurityGuard(securityGuard);
                    securityGuardTablePanel.refresh();
                    System.out.println(securityGuard.toString());
                }
            }
        });
    }

    private void setupDeleteDialog(){

        this.deleteDialog = new DeleteDialog(MainFrame.this);
        this.deleteDialog.setDialogListener(new DialogListener() {
            @Override
            public void onClickConfirm() {

                if(tabbedPane.getSelectedIndex() == 0){

                    controller.removeLecturer(lecturerTablePanel
                            .getValue(lecturerTablePanel.getSelectedRow()));
                    lecturerTablePanel.refresh();
                }
                if(tabbedPane.getSelectedIndex() == 1){

                    controller.removeSecurityGuard(securityGuardTablePanel
                            .getValue(securityGuardTablePanel.getSelectedRow()));
                    securityGuardTablePanel.refresh();
                }
            }
        });
    }

    private void setupSalaryDialog(){

        this.salaryDialog = new SalaryDialog(MainFrame.this);
        this.salaryDialog.setSalaryDialogListener(new SalaryDialogListener() {
            @Override
            public void calculateSalary(EmployeeType type) {

                if(type.equals(EmployeeType.lecturer)){

                    new SalaryDisplayDialog(MainFrame.this,
                            "Total salary of all Lecturers",
                            controller.calculateLecturerSalary());
                }
                if(type.equals(EmployeeType.securityGuard)){

                    new SalaryDisplayDialog(MainFrame.this,
                            "Total salary of all Security guards",
                            controller.calculateSecurityGuardSalary());
                }
                if(type.equals(EmployeeType.all)){

                    new SalaryDisplayDialog(MainFrame.this,
                            "Total salary of all Employees",
                            (controller.calculateLecturerSalary() +
                                    controller.calculateSecurityGuardSalary()));
                }
            }
        });
    }

    private void setupClosingDialog(){

        this.closingDialog = new WindowClosingDialog(MainFrame.this);
        this.closingDialog.setDialogListener(new DialogListener() {
            @Override
            public void onClickConfirm() {

                controller.exportToDatabase();
                dispose();
            }
        });
    }

    private void startDatabase(){

        try {
            controller.connectToDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Cannot connect to database", "database",
                    JOptionPane.ERROR_MESSAGE);
        }
        controller.importFromDatabase();
    }
}
