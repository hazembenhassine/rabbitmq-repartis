package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Main extends Application {


    final static  int number=4;

    @Override
    public void start(Stage primaryStage) throws Exception{

        List<Controller> controllers= new ArrayList<>();
        List<Stage> stages = new ArrayList<>();
        List<Parent> roots = new ArrayList<>();

        for (int i = 0; i < this.number ; i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Controller c = new Controller(i);
            controllers.add(c);
            Stage stage = new Stage();
            stages.add(stage);

            loader.setController(controllers.get(i));
            roots.add(loader.load()) ;

            stages.get(i).setTitle("Person number "+(i+1));
            stages.get(i).setScene(new Scene(roots.get(i), 300, 275));
            stages.get(i).show();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
