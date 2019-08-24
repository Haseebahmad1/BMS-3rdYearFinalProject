package SourceX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.*;
import javafx.scene.control.Button;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Logger;

public class CustomerDashboard implements Initializable {

    File User = new File("src\\FileX\\UserD.txt");
    File UserTransaction = new File ("src\\FileX\\UserTrans.txt");

    String UserName, CustomerName;

    @FXML
    private Label CUserName, AmountMoney;

    @FXML
    private Button LoginBtn;

    @FXML
    private Button Transactions;

    @FXML
    private Button DepositMoney;

    @FXML
    public void SentMoney() throws IOException {
        JFrame frame = new JFrame("Customer Details");

        String AccountNumber = JOptionPane.showInputDialog(frame, "Enter Account Number: ");
        String TotalAmount = JOptionPane.showInputDialog(frame, "Enter the Amount you want to send: ");

        int amount = Integer.parseInt(AmountMoney.getText());
        int amo = Integer.parseInt(TotalAmount);

        int Famo = amount-amo;

        String prevStr = amount+"";
        String fileStr = Famo+"";

        final JPanel panel = new JPanel();

        String outPut=amount+"";

        if (Famo < 0 ){
            JOptionPane.showMessageDialog(panel, "You don't have enough Balance!", "Stop Here!", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            outPut = "";
            outPut = Famo+"";
        }

        File fileToBeModified = new File("src\\\\FileX\\\\UserD.txt");
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();
            while (line != null){
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceAll(prevStr, fileStr);
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                reader.close();
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }



        AmountMoney.setText("");
        AmountMoney.setText(outPut);

        FileWriter fileWriter = new FileWriter(UserTransaction, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("\n"+UserName+" has send Rs: "+amo +" to Account Number: "+AccountNumber);
        printWriter.close();
     }

    @FXML
    public void Transactions() throws IOException {
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
    public void DepositMoney() throws FileNotFoundException {

        JFrame frame = new JFrame("Customer Details");

        String TotalAmount = JOptionPane.showInputDialog(frame, "Enter the Amount you want to Deposit: ");

        int PrevAmount = Integer.parseInt(AmountMoney.getText());
        int NewAmount = Integer.parseInt(TotalAmount);

        int FAmount = PrevAmount+NewAmount;

        final JPanel panel = new JPanel();
        String outPut=PrevAmount+"";

        if (NewAmount < 0 ){
            JOptionPane.showMessageDialog(panel, "Not Possible!", "Stop Here!", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            outPut = "";
            outPut = FAmount+"";
            JOptionPane.showMessageDialog(panel, "Operation Success!", "Message!", JOptionPane.PLAIN_MESSAGE);


            String PS = PrevAmount+"";
            String NS = outPut+"";

            File fileToBeModified = new File("src\\\\FileX\\\\UserD.txt");
            String oldContent = "";
            BufferedReader reader = null;
            FileWriter writer = null;

            try
            {
                reader = new BufferedReader(new FileReader(fileToBeModified));
                String line = reader.readLine();
                while (line != null){
                    oldContent = oldContent + line + System.lineSeparator();
                    line = reader.readLine();
                }
                String newContent = oldContent.replaceAll(PS, NS);
                writer = new FileWriter(fileToBeModified);
                writer.write(newContent);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally
            {
                try {
                    reader.close();
                    writer.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        AmountMoney.setText("");
        AmountMoney.setText(outPut);
    }


    @FXML
    private Button SentMoney;

    public void Logout(){
        LoginScrController.CustomerDash.close();
        Main.LogginPage.show();
    }

    public void setData(String data) throws FileNotFoundException {

        Scanner inputUser = new Scanner(User);
        String money="";

        while (inputUser.hasNext()) {
            String line = inputUser.nextLine();
            if (line.contains(data)) {
                String UserData[] = line.split("#", 0);
                money = UserData[2];
                UserName = UserData[0];

                CustomerName = UserData[0];
            }
        }
        inputUser.close();

        AmountMoney.setText(money);
        CUserName.setText(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
