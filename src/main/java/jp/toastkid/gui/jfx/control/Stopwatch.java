package jp.toastkid.gui.jfx.control;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * Stop watch powered by JavaFX.
 *
 * <pre>
 * final Stopwatch stopwatch = new Stopwatch();
 * stopwatch.setTextFill(Color.NAVY);
 * stopwatch.setStyle("-fx-font-size: 2em;");
 * final Button reset = new JFXButton("Reset");
 * reset.setOnAction(eve -> {stopwatch.reset();});
 * new HBox().getChildren().addAll(stopwatch, reset);
 * </pre>
 *
 * @author Toast kid
 *
 */
public class Stopwatch extends Button {

    /** timeline. */
    private Timeline timeline;

    /** String property. */
    private final StringProperty timeSeconds = new SimpleStringProperty();

    /** contanis duration. */
    private Duration time = Duration.ZERO;

    /** this timer is active. */
    private boolean active;

    /**
     * initialize this component.
     */
    public Stopwatch() {
        this.textProperty().bind(timeSeconds);
        this.setOnAction(eve -> {start();});
        reset();
    }

    /**
     * start count up.
     */
    public void start() {
        if (active) {
            timeline.stop();
            active = false;
            timeSeconds.set(makeText(time));
            return;
        }

        active = true;
        if (timeline == null) {
            timeline = new Timeline(
                    new KeyFrame(Duration.millis(100),
                    e2 -> {
                        if (!active) {
                            return;
                        }
                        final Duration duration = ((KeyFrame) e2.getSource()).getTime();
                        time = time.add(duration);
                        timeSeconds.set(makeText(time));
                    }
                )
            );
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * duration to text.
     * @param duration
     * @return
     */
    private String makeText(final Duration duration) {
        return String.format("%02d:%02d",
                (long) Math.floor(duration.toMinutes() % 60.0),
                (long) Math.floor(duration.toSeconds() % 60.0)
                )
                + (active ? "▶" : "■");
    }

    /**
     * reset timer.
     */
    public void reset() {
        time = Duration.ZERO;
        timeSeconds.set(makeText(time));
    }

    /**
     * get is active.
     * @return active(boolean)
     */
    public boolean isActive() {
        return active;
    }

}
