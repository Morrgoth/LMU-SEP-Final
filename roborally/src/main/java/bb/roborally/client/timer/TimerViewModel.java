package bb.roborally.client.timer;
import java.util.Timer;
public class TimerViewModel {

    Timer timer = new Timer();
    timer.schedule(new TimerTask() {}, 30);


}
