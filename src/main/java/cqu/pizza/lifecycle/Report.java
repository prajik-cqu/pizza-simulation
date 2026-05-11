package cqu.pizza.lifecycle;

import java.util.List;

/**
 * Holds the final simulation report text.
 * Built from completed, refused and active order lists at the end of a simulation run.
 * Will be completed fully in Phase 4.
 *
 * @author student
 */
public class Report {

    /** The full report text. */
    private String text;

    /**
     * Constructs the report from simulation results.
     * Uses a StringBuilder to build the report text incrementally.
     *
     * @param duration  the total duration of the simulation run
     * @param completed list of completed orders
     * @param refused   list of refused orders
     * @param active    list of still-active orders
     */
    public Report(int duration, List<Order> completed, List<Order> refused, List<Order> active) {
        StringBuilder sb = new StringBuilder();

        sb.append("""
                Statistics
                ==========
                """);
        sb.append(String.format("Duration of run = %d%n", duration));
        sb.append(String.format("Orders completed = %d%n", completed.size()));
        sb.append(String.format("Orders refused = %d%n", refused.size()));
        sb.append(String.format("Orders active = %d%n", active.size()));

        sb.append("""

                Completed orders
                ================
                """);
        for (Order o : completed) {
            sb.append(o.toString()).append("\n");
        }

        sb.append("""

                Refused orders
                ==============
                """);
        for (Order o : refused) {
            sb.append(o.toString()).append("\n");
        }

        sb.append("""

                Active orders
                =============
                """);
        for (Order o : active) {
            sb.append(o.toString()).append("\n");
        }

        text = sb.toString();
    }

    /**
     * Returns the report text.
     *
     * @return the full report as a String
     */
    public String getText() {
        return text;
    }

    /**
     * Saves the report to the specified file using a Formatter.
     * Throws a ReportException if the file cannot be opened or written.
     *
     * @param fileName the path/name of the file to save the report to
     * @throws ReportException if an error occurs accessing the file
     */
    public void save(String fileName) throws ReportException {
        try (java.util.Formatter formatter = new java.util.Formatter(fileName)) {
            formatter.format("%s", text);
        } catch (Exception e) {
            throw new ReportException("Error accessing " + fileName);
        }
    }
}
