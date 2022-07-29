package bb.roborally.client.timer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
/**
 * @author Tolga Engin
 * @author Bence Ament
 */
public class TimerViewModel {

    private final BooleanProperty timerRunning;
    private TimerView view;

    public TimerViewModel(BooleanProperty timerRunning) {
        this.timerRunning = timerRunning;
    }

    public void connect(TimerView timerView) {
        this.view = timerView;
        observeModelAndUpdate();
    }

    private void observeModelAndUpdate() {
        timerRunning.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    view.start();
                } else {
                    view.reset();
                }
            }
        });
    }
}
