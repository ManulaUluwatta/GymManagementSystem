package lk.gym.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("/lk/gym/ui/fxml/Login.fxml"));
        // primaryStage.setTitle("Hellow");
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Golden Power Gym");
        Image image = new Image("/lk/gym/ui/images/jys.png");
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }
}
