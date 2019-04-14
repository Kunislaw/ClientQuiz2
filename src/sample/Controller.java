package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Controller{
    private Socket socket;
    private InetSocketAddress address;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    @FXML
    void initialize() {
        try {
            socket = new Socket();
            address = new InetSocketAddress("localhost", 6000);
            socket.connect(address, 2500);
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            System.out.println("Polaczenie nawiazane");
        } catch (Exception e) {
            System.out.println("Nie udało się nawiązać połączenia\nPowód: " + e.getMessage());
        }
    }

    @FXML
    private Button send_button;
    @FXML
    private TextField nick_text;
    @FXML
    private TextField answer_text;
    @FXML
    void ActionHandler(ActionEvent actionEvent){
        if(actionEvent.getSource() == send_button) { button(); }
    }
    void button(){
        Answer answer = new Answer(nick_text.getText(),answer_text.getText());
        System.out.println(answer);
        try{
            objectOutputStream.writeObject(answer);
        }
        catch(Exception e)
        {
            System.out.println("Bład:\n"+e.getMessage());
        }
    }
}
