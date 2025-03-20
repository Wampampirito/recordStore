package com.recordstore.auxiliar;

/**
 * Utility class that provides methods for printing messages inside a rectangle with borders.
 * The main method is {@link #flag(String)}, which displays the message inside a bordered rectangle 
 * and handles the case where the message is too long to fit in a single line.
 * 
 * <p>This class is useful for displaying messages in a prominent way on the console, especially when 
 * important or specially formatted messages need to be highlighted.</p>
 * 
 * <p><strong>Features:</strong></p>
 * <ul>
 *   <li>Automatically calculates the width of the rectangle based on the length of the message.</li>
 *   <li>Ensures that the message does not overflow by splitting it into multiple lines if necessary.</li>
 *   <li>Allows customization of the margin and the rectangle border.</li>
 * </ul>
 * 
 * <p><strong>Example usage:</strong></p>
 * <pre>
 * printMsg.flag("This is an important message");
 * </pre>
 */
public class PrintFlag {

    /**
     * Prints a message inside a rectangle with borders. If the message is too long, 
     * it is split into multiple lines to fit inside the rectangle.
     * 
     * @param message The message to be printed inside the rectangle.
     * @throws IllegalArgumentException If the message is null.
     */
    public static void flag(String message) {
        // Calculate the width of the rectangle
        int width = Math.min(message.length() + 10, 100); // Width = length + 10, max 100
        int margin = 5; // Minimum 5 spaces of margin

        // Ensure the width is sufficient for the margins
        if (width < margin * 2 + 2) { // 2 borders + 2 minimum spaces
            width = margin * 2 + 2;
        }

        // Create the borders
        String topBorder = "╔" + repeatChar('═', width) + "╗"; // Top border
        String bottomBorder = "╚" + repeatChar('═', width) + "╝"; // Bottom border
        String sideBorder = "║"; // Side border

        System.out.println(topBorder); // Print top border

        // Calculate the available space for the text (width - borders - margins)
        int textSpace = width - margin * 2;

        // If the message fits in one line, print it without splitting
        if (message.length() <= textSpace) {
            String line = sideBorder + " ".repeat(margin) + message + " ".repeat(textSpace - message.length()) + " ".repeat(margin) + sideBorder;
            System.out.println(line);
        } else {
            // Split the message into lines that do not exceed the available space
            for (String lineMessage : splitMessage(message, textSpace)) {
                String line = sideBorder + " ".repeat(margin) + lineMessage + " ".repeat(textSpace - lineMessage.length()) + " ".repeat(margin) + sideBorder;
                System.out.println(line);
            }
        }

        System.out.println(bottomBorder); // Print bottom border
    }

    /**
     * Splits the message into lines that do not exceed a maximum width.
     * 
     * @param message The message to be split.
     * @param maxWidth The maximum width of each line.
     * @return An array of strings representing the split lines.
     */
    private static String[] splitMessage(String message, int maxWidth) {
        return message.split("(?<=\\G.{" + maxWidth + "})");
    }

    /**
     * Repeats a specified character a given number of times.
     * 
     * @param c The character to repeat.
     * @param n The number of times the character should be repeated.
     * @return A string with the repeated character.
     */
    private static String repeatChar(char c, int n) {
        return new String(new char[n]).replace('\0', c);
    }

    /**
     * Main method that performs tests for the {@link PrintFlag} class.
     * Prints a long and a short message inside a bordered rectangle.
     * 
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        String longText = "This is a very long test message that should be split into multiple lines to fit inside the rectangle.";
        String shortText = "This is a short message.";

        // Test with a long message
        flag(longText);

        // Test with a short message
        flag(shortText);
    }
}
