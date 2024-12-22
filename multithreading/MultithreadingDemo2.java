package multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// JobManager class to manage job completion flag
class JobManager {
    private static final Logger logger = LoggerFactory.getLogger(JobManager.class);

    // Volatile flag to ensure visibility across threads
    private volatile boolean isJobComplete = false;

    // Method to start job and set completion flag
    public void startJob() {
        try {
            Thread.sleep(3000);  // Simulate time taken for job processing
            isJobComplete = true;
            logger.info("Job completed! Flag set to true.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Job interrupted", e);
        }
    }

    // Method to check if the job is complete
    public boolean isJobComplete() {
        return isJobComplete;
    }
}

// Worker thread that checks for job completion status
class Worker implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Worker.class);
    private JobManager jobManager;

    public Worker(JobManager jobManager) {
        this.jobManager = jobManager;
    }

    @Override
    public void run() {
        while (!jobManager.isJobComplete()) {
            logger.info("{}: Waiting for job to complete...", Thread.currentThread().getName());
            try {
                Thread.sleep(500);  // Simulate some other work while waiting
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Worker thread interrupted", e);
            }
        }
        logger.info("{}: Job completed! Proceeding with next steps.", Thread.currentThread().getName());
    }
}

// Main class to run the multithreading example
public class MultithreadingDemo2 {
    private static final Logger logger = LoggerFactory.getLogger(MultithreadingDemo2.class);

    public static void main(String[] args) {
        // Create JobManager instance
        JobManager jobManager = new JobManager();

        // Create worker threads
        Thread worker1 = new Thread(new Worker(jobManager), "Worker1");
        Thread worker2 = new Thread(new Worker(jobManager), "Worker2");

        logger.info("Starting job and worker threads...");

        // Start worker threads
        worker1.start();
        worker2.start();

        // Start job manager thread to simulate the completion of the job
        Thread jobThread = new Thread(() -> jobManager.startJob());
        jobThread.start();

        try {
            jobThread.join();  // Wait for the job thread to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Main thread interrupted", e);
        }

        logger.info("All threads have finished their work.");
    }
}
