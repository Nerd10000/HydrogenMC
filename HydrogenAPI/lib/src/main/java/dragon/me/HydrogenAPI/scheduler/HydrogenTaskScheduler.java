package dragon.me.HydrogenAPI.scheduler;

public interface HydrogenTaskScheduler {
    
    /**
     * Run task on the main thread, this is a blocking call so it should be used sparingly.
     * @param runnable the task to run, use lambdas if possible.
     */
    void runTask(Runnable runnable);
    /**
     * Run task off the main thread, this is a non-blocking call so it should be used sparingly.
     * @param runnable The task that will be used, use lambdas if possible.
     */
    void runAsyncTask(Runnable runnable);

    /**
     * Run task later on the main thread, this can be used to schedule tasks.
     * 
     * @param runnable The task, use lambdas if possible.
     * @param delay The delay specifies when the task will ran (specify in ticks)
     */
    void runTaskLater(Runnable runnable, long delay);


    /**
     * Run task later off the main thread, this can be used to schedule tasks.
     * 
     * @param runnable The task, use lambdas if possible.
     * @param delay The delay specifies when the task will ran (specify in ticks)
     */
    void runAsyncTaskLater(Runnable runnable, long delay);

    /**
     * Run task timed on the main thread, this can be used to continuously schedule tasks.
     * 
     * @param runnable The task, use lambdas if possible.
     * @param delay The delay specifies when the task will ran (specify in ticks)
     * @param period The period specifies how often the task will be ran (specify in ticks)
     */
    void runTaskTimer(Runnable runnable, long delay,long period);

    
    /**
     * Run task timed off the main thread, this can be used to continuously schedule tasks.
     * 
     * @param runnable The task, use lambdas if possible.
     * @param delay The delay specifies when the task will ran (specify in ticks)
     * @param period The period specifies how often the task will be ran (specify in ticks)
     */
    void runAsyncTaskTimer(Runnable runnable, long delay,long period);

}
