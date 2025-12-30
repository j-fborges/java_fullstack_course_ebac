package gui;

import javax.swing.text.JTextComponent;

public class RegistrationTableController {

    public static void clearFields(JTextComponent... fields){
        for(JTextComponent field: fields){
            field.setText("");
        }
    }
}
