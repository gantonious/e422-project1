import java.util.TimerTask;

/**
 * Created by George on 2017-03-06.
 */
public class Watchdog extends TimerTask {

    private Thread threadToWatch;

    public Watchdog (Thread target) {
        threadToWatch = target;
    }

    public void run() {
        threadToWatch.stop();
    }
}
