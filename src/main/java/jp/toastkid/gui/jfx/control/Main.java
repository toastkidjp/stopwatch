package jp.toastkid.gui.jfx.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Stopwatch app.
 *
 * @author Toast kid
 */
public final class Main extends Application {

    /** Application title. */
    private static final String TITLE = "Stopwatch";

    /** Stage. */
    private Stage stage;

    @Override
    public void start(final Stage arg0) throws Exception {
        stage = new Stage(StageStyle.DECORATED);

        final Stopwatch stopwatch = new Stopwatch();
        stopwatch.setTextFill(Color.NAVY);
        stopwatch.setStyle("-fx-font-size: 4em;");

        final Button start = new Button("Start");
        start.setOnAction(eve -> {
            start.setText(stopwatch.isActive() ? "Start" : "Stop");
            stopwatch.start();
        });

        final Button reset = new Button("Reset");
        reset.setOnAction(eve -> {stopwatch.reset();});

        stage.setScene(new Scene(new HBox(stopwatch, new VBox(start, reset)), 320, 100));
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        Application.launch(Main.class);
    }
}