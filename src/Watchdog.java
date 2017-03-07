import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by George on 2017-03-06.
 */
public class Watchdog extends TimerTask {

    private Thread threadToWatch;
    private Timer timeoutTimer;

    public Watchdog (Thread target) {
        threadToWatch = target;
        timeoutTimer = new Timer();
    }

    public void startWatch(long timeout) {
        timeoutTimer.schedule(this, timeout);
        threadToWatch.start();
    }

    public void waitOnThread() {
        try {
            threadToWatch.join();
            cancelWatch();
        } catch (ThreadDeath e) {
            throw new TimeoutException();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public void cancelWatch() {
        timeoutTimer.cancel();
    }

    public void run() {
        threadToWatch.stop();
    }
}
