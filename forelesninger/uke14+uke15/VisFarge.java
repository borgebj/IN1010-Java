import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.File;
import java.util.Scanner;

public class VisFarge extends Application {
    @Override
    public void start(Stage teater) {
        int r = 0, g = 0, b = 0;
        File file = new FileChooser().showOpenDialog(teater);
        try {
            Scanner f = new Scanner(file);
            r = f.nextInt();  g = f.nextInt();  b = f.nextInt();
        } catch (Exception e) {}

        Circle fargedemo = new Circle(100);
        fargedemo.setCenterX(100);  fargedemo.setCenterY(100);
        fargedemo.setFill(Color.rgb(r,g,b));

        Pane kulisser = new Pane();
        kulisser.getChildren().add(fargedemo);

        Scene scene = new Scene(kulisser);
        teater.setTitle("RGB-farge");
        teater.setScene(scene);
        teater.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}