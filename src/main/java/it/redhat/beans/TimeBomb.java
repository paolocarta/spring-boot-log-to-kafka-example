package it.redhat.beans;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeBomb {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    AtomicInteger count = new AtomicInteger(1);

    public void waitAndExitApplication() {

        logger.error("Sample error message " + count.getAndIncrement());
        try {
            logger.info("Will exit in 60");
            Thread.sleep(60000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            logger.info("Closing application");
            System.exit(0);
        }

    }

}
