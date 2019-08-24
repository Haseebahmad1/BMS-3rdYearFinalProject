package SourceX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.*;

public class SignUp {

    File User = new File("src\\FileX\\UserD.txt");

    @FXML
    private TextField Lname;

    @FXML
    private PasswordField SignUpPass;

    @FXML
    private TextField Fname;

    @FXML
    private Button RegisterNow;

    @FXML
    void Register(ActionEvent event) throws IOException {

        String firstName = Fname.getText();
        String lastName = Lname.getText();
        String password = SignUpPass.getText();

        final JPanel panel = new JPanel();

        if (!firstName.isEmpty() && !password.isEmpty()){

            FileWriter fileWriter = new FileWriter(User, true); //Set true for append mode
            PrintWriter printWriter = new PrintWriter(fileWriter);
            System.out.print("\n");
            printWriter.print(firstName+""+lastName+"#"+password+"#0");  //New line

            LoginScrController.SignUpPage.close();
            JOptionPane.showMessageDialog(panel, "Success!", "Sign Up!", JOptionPane.PLAIN_MESSAGE);
            printWriter.close();
        }
        else{
            JOptionPane.showMessageDialog(panel, "Please fill all fields", "Error!", JOptionPane.PLAIN_MESSAGE);
        }

    }

}
