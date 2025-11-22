package gui;

import javax.swing.*;

public class RegistrationTableController {

    public static void clearFields(JTextField... fields){
        for(JTextField field: fields){
            field.setText("");
        }
    }
}
