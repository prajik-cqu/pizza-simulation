package cqu.pizza.lifecycle;

/**
 * Represents a single pizza order in the simulation.
 * Tracks the order's identifier, start and finish times, pizza type,
 * and the processing step the order is currently at.
 *
 * @author student
 */
public class Order {

    /** Unique identifier for this order. */
    private int id;

    /** Simulation clock time when the order was received. */
    private int start;

    /** Simulation clock time when the order was completed. -1 while still active. */
    private int finish;

    /** Name of the pizza ordered. */
    private String pizza;

    /** The current processing step for this order. Incremented after each step completes. */
    private int step;

    /**
     * Constructs a new Order with the given details.
     * The finish time is initialised to -1 (not yet finished).
     * The step is initialised to 1 (order actioned).
     *
     * @param id    the unique order identifier
     * @param start the simulation time at which the order was placed
     * @param pizza the name of the pizza ordered
     */
    public Order(int id, int start, String pizza) {
        this.id = id;
        this.start = start;
        this.finish = -1;
        this.pizza = pizza;
        this.step = 1;
    }

    /**
     * Returns the order's unique identifier.
     *
     * @return the order id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the simulation clock time when the order was placed.
     *
     * @return the start time
     */
    public int getStart() {
        return start;
    }

    /**
     * Returns the simulation clock time when the order was completed.
     * Returns -1 if the order has not yet been completed.
     *
     * @return the finish time, or -1 if still active
     */
    public int getFinish() {
        return finish;
    }

    /**
     * Returns the name of the pizza for this order.
     *
     * @return the pizza name
     */
    public String getPizza() {
        return pizza;
    }

    /**
     * Returns the current processing step for this order.
     *
     * @return the step number
     */
    public int getStep() {
        return step;
    }

    /**
     * Increments the step counter to indicate that the order has progressed
     * to the next processing step.
     */
    public void stepCompleted() {
        step++;
    }

    /**
     * Sets the finish time for this order when it is completed or refused.
     *
     * @param finish the simulation clock time at which the order finished
     */
    public void setFinish(int finish) {
        this.finish = finish;
    }

    /**
     * Returns a string representation of this order in the format:
     * {@code <id,start,finish,pizza,step>}
     *
     * @return formatted order string
     */
    @Override
    public String toString() {
        return String.format("<%d,%d,%d,%s,%d>", id, start, finish, pizza, step);
    }
}
