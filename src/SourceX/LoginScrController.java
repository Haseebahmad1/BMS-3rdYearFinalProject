package SourceX;

import DataDB.AdminDB;
import DataDB.UsersDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import SourceX.Main;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class LoginScrController implements AdminDB, UsersDB {

    File Admin = new File("src\\FileX\\AdminD.txt");
    File User = new File("src\\FileX\\UserD.txt");

    public static Stage CustomerDash, AdminDash, SignUpPage;

    @FXML
    private TextField AdminId, AdminPass, CustoId, CustoPass;

    @FXML
    private Button AdminSignIn, CustoSignIn, SignUpBtn, AForgetPass, CForgetPass;

    @FXML
    public void ForgetCPass() throws FileNotFoundException {
        Scanner inp = new Scanner(User);

        JFrame frame = new JFrame("User Details");

        String fName = JOptionPane.showInputDialog(frame, "Enter your Name: ");

        final JPanel panel = new JPanel();

        String pass="";

        while(inp.hasNext()){
            String line = inp.next();

            if (line.contains(fName)) {
                String UserData[] = line.split("#", 0);
                pass = UserData[1];
            }
        }
        if (pass == ""){
            JOptionPane.showMessageDialog(panel, "No Customer Found!", "Attention!", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(panel, "Your Password is "+pass, "Password", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @FXML
    public void ForgetAPass() throws FileNotFoundException {
        Scanner inp = new Scanner(Admin);

        JFrame frame = new JFrame("User Details");

        String fName = JOptionPane.showInputDialog(frame, "Enter your Name: ");

        final JPanel panel = new JPanel();

        int APassword=0;
        String pass="";

        while(inp.hasNext()){
            String line = inp.next();

            if (line.contains(fName)) {
                String UserData[] = line.split("#", 0);
                pass = UserData[1];
            }
        }
        if (pass == ""){
            JOptionPane.showMessageDialog(panel, "No Admin Found!", "Attention!", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(panel, "Your Password is "+pass, "Password", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @FXML
    void SignUp(ActionEvent event) throws FileNotFoundException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        SignUpPage = stage;
        stage.show();
    }


    @FXML
    void AdminSignIn(ActionEvent event) throws FileNotFoundException {

        String AdminUserName = AdminId.getText();
        String AdminPassword = AdminPass.getText();

        if ((SearchAdmin(AdminUserName, AdminPassword) == true)) {
            final JPanel panel = new JPanel();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
                Parent root = fxmlLoader.load();

                AdminDashboard controller = fxmlLoader.<AdminDashboard>getController();
                controller.setData(AdminUserName);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                Main.LogginPage.hide();
                AdminDash = stage;

            }
            catch (IOException e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(panel, "YOU ARE SIGNED IN", "Congratulations", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Either Username or Password Incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    void CustomerSignIn(ActionEvent event) throws FileNotFoundException {

        String CustoUserName = CustoId.getText();
        String CustoPassword = CustoPass.getText();

        if (SearchCustomer(CustoUserName, CustoPassword) == true) {

            final JPanel panel = new JPanel();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerDashboard.fxml"));
                Parent root = fxmlLoader.load();

                CustomerDashboard controller = fxmlLoader.<CustomerDashboard>getController();
                controller.setData(CustoUserName);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                Main.LogginPage.hide();
                CustomerDash = stage;
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(panel, "YOU ARE SIGNED IN", "Congratulations", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Either Username or Password Incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    private boolean SearchAdmin(String Username, String Pass) throws FileNotFoundException {

        Scanner inputUser = new Scanner(Admin);
        boolean check = false;

        while (inputUser.hasNext()) {
            String line = inputUser.nextLine();
            if (line.contains(Username)) {

                if (line.contains(Pass)) {
                    check = true;
                }
                else{
                    check = false;
                }
            }
        }
        inputUser.close();
        return check;
    }

    private boolean SearchCustomer(String Username, String Pass) throws FileNotFoundException {

        Scanner inputUser = new Scanner(User);
        boolean check = false;

        while (inputUser.hasNext()) {
            String line = inputUser.nextLine();
            if (line.contains(Username)) {
                if (line.contains(Pass)) {
                    check = true;
                }
                else{
                    check = false;
                }
            }
        }
        inputUser.close();
        return check;
    }

    public LoginScrController() throws FileNotFoundException {
    }
}
