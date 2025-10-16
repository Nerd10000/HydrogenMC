package dragon.me.HydrogenAPI.scheduler;

public interface HydrogenTask {
    void cancel();
    boolean isCancelled();
}
