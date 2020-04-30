package sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private VBox vbox;

    private int index;
    private List<TextArea> textAreas ;

    Controller(int i){
        this.index=i;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        Messaging msg = new Messaging();
        textAreas = new ArrayList<>();
        for (int i = 0; i < Main.number; i++) {
            TextArea ta = new TextArea("Text Area reserved to person number "+(i+1));
            if (i == this.index){
                ta.textProperty().addListener((observable, oldValue, newValue) -> {
                    msg.send(ta.getText(),this.index);
                } );
                ta.setStyle("-fx-control-inner-background:#00FF00;");
            } else {
                msg.receive(ta,i);
                ta.setEditable(false);
                ta.setStyle("-fx-control-inner-background:#FF0000;");
            }
            textAreas.add(ta);
            vbox.getChildren().add(ta);
        }
    }
}
