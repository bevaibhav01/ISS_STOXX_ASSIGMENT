package basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArraysAndStrings {
    // Logger instance
    private static final Logger logger = LoggerFactory.getLogger(ArraysAndStrings.class);

    public static void main(String[] args) {
    	
        // Array Example
        logger.info("Initializing the array...");
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Accessing and logging array elements
        logger.info("Accessing and logging array elements...");
        for (int i = 0; i < numbers.length; i++) {
            logger.info("Element at index {}: {}", i, numbers[i]);
        }

        // Update an array element
        logger.info("\nUpdating array element at index 5...");
        numbers[5] = 50; // Updating value at index 5
        logger.info("Updated element at index 5: {}", numbers[5]);

        // Searching for a value in the array
        int target = 50;
        logger.info("\nSearching for value {} in the array...", target);
        boolean found = false;
        for (int num : numbers) {
            if (num == target) {
                logger.info("Found {} in the array.", target);
                found = true;
                break;
            }
        }
        if (!found) {
            logger.warn("Value {} not found in the array.", target);
        }

        // String Example
        logger.info("\nPerforming String operations...");
        String string = "Hello, ISS!";
        logger.info("Original string: {}", string);

        // String length
        logger.info("Length of the string: {}", string.length());

        // String comparison
        String string2 = "hello, Vaibhav!";
        logger.info("Comparing strings: {} and {}.", string, string2);
        if (string.equalsIgnoreCase(string2)) {
            logger.info("The strings are equal ignoring case.");
        } else {
            logger.warn("The strings are not equal.");
        }

        // String concatenation
        String greeting = "Hello";
        String name = "Vaibhav";
        String message = greeting + ", " + name + "!";
        logger.info("Concatenated string: {}", message);

        // String manipulation (substring, replace)
        String substring = string.substring(7, 11); // Extract substring "ISS"
        logger.info("Extracted substring: {}", substring);
        
        String replacedString = string.replace("ISS", "World");
        logger.info("Replaced string: {}", replacedString);

        // Using StringBuilder for mutable strings
        logger.info("\nUsing StringBuilder for mutable strings...");
        StringBuilder stringUsingBuilder = new StringBuilder("Hello");
        stringUsingBuilder.append(", ").append("ISS STOXX");
        logger.info("Appended string: {}", stringUsingBuilder.toString());
        
        stringUsingBuilder.replace(6, 11, "Java");
        logger.info("Replaced part of the string: {}", stringUsingBuilder.toString());
        
        stringUsingBuilder.reverse();
        logger.info("Reversed string: {}", stringUsingBuilder.toString());

        // Conclusion
        logger.info("\nArray and String operations completed successfully.");
    }
}
