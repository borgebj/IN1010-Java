import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Swastika extends Application {

    @Override
    public void start(Stage stage) {

        StackPane rootPane = new StackPane();

        StackPane stack = new StackPane();
        stack.setStyle("-fx-background-color: #e0372b; -fx-border-style: solid; -fx-border-width: 5; -fx-border-color: black;");
        Pane p = new Pane();

        Circle c = new Circle(300, Color.WHITE);
        stack.getChildren().add(c);

        // main rektangel
        Rectangle r1 = new Rectangle(420, 60);
        r1.setRotate(45);
        stack.getChildren().add(r1);

        Rectangle r2 = new Rectangle(60, 420);
        r2.setRotate(45);
        r2.setY(200);
        stack.getChildren().add(r2);



        // beinene
        Rectangle r3 = new Rectangle(60, 200);
        r3.setX(215);
        r3.setY(10.5);
        r3.setRotate(45);
        p.getChildren().add(r3);

        Rectangle r4 = new Rectangle(60, 200);
        r4.setY(410);
        r4.setX(325);
        r4.setRotate(45);
        p.getChildren().add(r4);

        Rectangle r5 = new Rectangle(60, 200);
        r5.setY(255);
        r5.setX(70);
        r5.setRotate(135);
        p.getChildren().add(r5);

        Rectangle r6 = new Rectangle(60, 200);
        r6.setY(145);
        r6.setX(469.5);
        r6.setRotate(135);
        p.getChildren().add(r6);


        rootPane.getChildren().addAll(stack, p);

        Scene scene = new Scene(rootPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}