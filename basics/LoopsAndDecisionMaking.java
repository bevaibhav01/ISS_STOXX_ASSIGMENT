package basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoopsAndDecisionMaking {
    // Logger instance
    private static final Logger logger = LoggerFactory.getLogger(LoopsAndDecisionMaking.class);

    public static void main(String[] args) {
        // Array of numbers to process
        int[] numbers = {5, 12, 19, 25, 30, 45, 60, 3, 2};

        // Example of a FOR loop: Iterating through a fixed range of numbers
        //use case when we know no of iterations need to perform
        logger.info("Using FOR loop to process the numbers...");
        for (int i = 0; i < numbers.length; i++) {
            logger.info("Processing number: {}", numbers[i]);

            // IF-ELSE decision making to categorize numbers as even or odd
            if (numbers[i] % 2 == 0) {
                logger.info("The number {} is even.", numbers[i]);
            } else {
                logger.info("The number {} is odd.", numbers[i]);
            }
        }

        // Example of a WHILE loop: Processing items in a queue or records in a system
        //use case of while loop is when we dont know how many iterations need to be perform we use while loop
        // till our condition is satisfied
        
        logger.info("\nUsing WHILE loop to process numbers until a number greater than 50 is encountered...");
        
        int index = 0;
        while (index < numbers.length) {
            int number = numbers[index];
            logger.info("Processing number: {}", number);

            // Check if number is greater than 50 (this is the stopping condition)
            if (number > 50) {
                logger.info("Stopping the processing as number {} is greater than 50", number);
                break; // Exit the loop when the condition is met
            }

            index++;
        }

        // Example of a DO-WHILE loop: Ensures that we search at least once for the target value
        //use case of do-while is when we want to run a loop atleast ones irrespective of our condition
        //is wrong or right
        logger.info("\nUsing DO-WHILE loop to search for the target value...");
        index = 0;
        boolean found = false;
        
     // Target value to search in the array
        int targetValue = 45;

        // The loop will continue checking until the target value is found or the end of the array is reached
        do {
            logger.info("Checking number at index {}: {}", index, numbers[index]);

            if (numbers[index] == targetValue) {
                logger.info("Target value {} found at index {}.", targetValue, index);
                found = true;
                break; // Exit once the target value is found
            }

            index++;

        } while (index < numbers.length); // Condition to continue as long as we haven't reached the end of the array

        // If the target value is not found after checking all elements
        if (!found) {
            logger.warn("Target value {} not found in the array.", targetValue);
        }
    }
}
