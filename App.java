import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.MouseButton;

/** Hentet fra
 * https://stackoverflow.com/questions/60012383/mousedragged-detection-for-multiple-nodes-while-holding-the-button-javafx
 * brukt for inspirasjon og hjelp
 */

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setPadding(new Insets(2));
        root.setVgap(2);
        root.setHgap(2);

        // start full press-drag-release gesture
        root.setOnDragDetected(
                event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        event.consume();
                        root.startFullDrag();
                    }
                });

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                Rectangle rect = new Rectangle(50, 50, Color.WHITE);
                rect.setStroke(Color.BLACK);
                root.add(rect, i, j);

                // detect MOUSE_DRAG_ENTERED events
                rect.setOnMouseDragEntered(
                        event -> {
                            event.consume();
                            rect.setFill(Color.BLACK);
                        });
            }
        }

        primaryStage.setTitle("MouseDragEvent Example");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}