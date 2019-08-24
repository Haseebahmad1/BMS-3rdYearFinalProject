package SourceX;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AdminDashboard implements Initializable {

    @FXML
    private Label AUserName;

    @FXML
    private Button LoginBtn;

    @FXML
    private Button ShowTransactions;

    @FXML
    private Button TaxRate;

    @FXML
    private Button ShowCustomers;

    @FXML
    private Button AddAdmin;

    File Admin = new File("src\\FileX\\AdminD.txt");

    @FXML
    public void AAdmin() throws IOException {

        JFrame frame = new JFrame("Add Admin Details");

        String FirstName = JOptionPane.showInputDialog(frame, "Enter First Number: ");
        String LastName = JOptionPane.showInputDialog(frame, "Enter Last Number: ");
        String AdminPass = JOptionPane.showInputDialog(frame, "Enter Admin Password: ");

        final JPanel panel = new JPanel();

        if (!FirstName.isEmpty() && !AdminPass.isEmpty()){

            FileWriter fileWriter = new FileWriter(Admin, true); //Set true for append mode
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(FirstName+""+LastName+"#"+AdminPass);  //New line

            JOptionPane.showMessageDialog(panel, "Success!", "Sign Up!", JOptionPane.PLAIN_MESSAGE);
            printWriter.close();
        }
        else{
            JOptionPane.showMessageDialog(panel, "Please fill all fields!", "Incomplete Data", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @FXML
    public void Transactions() throws IOException {
        File UserTransaction = new File("src\\FileX\\UserTrans.txt");

        if(UserTransaction.exists()){

            try{
                Runtime.getRuntime().exec("notepad src\\FileX\\UserTrans.txt");
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    @FXML
    public void Tax(){
        final JPanel panel = new JPanel();
        int CTax = 500;
        JOptionPane.showMessageDialog(panel, "Current Tax Rate is Rs:"+CTax, "Tax Rate!", JOptionPane.PLAIN_MESSAGE);
    }

    @FXML
    public void ShowCustomers() throws FileNotFoundException {

        File file = new File("src\\FileX\\UserD.txt");
        Scanner inp = new Scanner(file);
        int i=1;
        while(inp.hasNext()){
            String line = inp.next();

            String CustoNames[] = line.split("#", 0);
            System.out.println(i+". " +CustoNames[0]);
            i++;
        }
        System.out.println("Currently, There are "+(--i)+" Users Signed Up for Bank Account.");
    }

    public void Logout(){
        LoginScrController.AdminDash.close();
        Main.LogginPage.show();
    }

    public void setData(String data) {
        AUserName.setText(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

