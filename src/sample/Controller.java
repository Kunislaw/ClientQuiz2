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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Controller{
    private Socket socket;
    private InetSocketAddress address;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    @FXML
    private Button send_button;
    @FXML
    private TextField nick_text;
    @FXML
    private TextField answer_text;
    @FXML
    void initialize() {

    }


    @FXML
    void ActionHandler(ActionEvent actionEvent){
        if(actionEvent.getSource() == send_button) { button(); }
    }
    void button(){
        try {
            socket = new Socket();//tworzymy socket
            address = new InetSocketAddress("localhost", 6000);
            socket.connect(address, 2500);//laczymy sie
            outputStream = socket.getOutputStream();//nowy output stream
            objectOutputStream = new ObjectOutputStream(outputStream); //do przesylania obiektow
            Answer answer = new Answer(nick_text.getText(),answer_text.getText());//tworzymy obiekt klasy Answer
            objectOutputStream.writeObject(answer);//i wysylamy go
            socket.close();//zamykamy polaczenie
        } catch (Exception e) {
            System.out.println("Nie udało się nawiązać połączenia\nPowód: " + e.getMessage());
        }
    }
}
