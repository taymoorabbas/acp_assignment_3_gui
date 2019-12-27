/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 26-Dec-19
Time: 6:20 PM
Lau ji Ghauri aya fir
*/

package view;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JTextField textField;
    private String type;

    public CustomCellEditor(String type) {
        this.type = type;
        this.textField = new JTextField();
        this.textField.setFont(new Font("SanSerif", Font.ITALIC, 18));

        if(type.equals("float")){

            this.textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') ||(c == '.')||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE))) {

                        if(textField.getText().equals("0")){

                            textField.setText(textField.getText().trim());
                        }
                        e.consume();
                    }
                    if(textField.getText().length() == 0){

                        textField.setText("0");
                    }
                }
            });
        }
        if(type.equals("int")){

            this.textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9')||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE))) {
                        e.consume();
                    }
                    if(textField.getText().length() == 0){

                        textField.setText("0");
                    }
                }
            });
        }
    }

    @Override
    public Object getCellEditorValue() {

        if(type.equals("int")){

            return Integer.parseInt(textField.getText().trim());
        }
        if(type.equals("float")){

            return Float.parseFloat(textField.getText().trim());
        }

        return textField.getText().trim();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        textField.setText(value.toString());
        return textField;
    }

    @Override
    public void cancelCellEditing() {
        super.cancelCellEditing();
    }

    @Override
    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }
}
