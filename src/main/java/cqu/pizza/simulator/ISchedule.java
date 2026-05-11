package cqu.pizza.simulator;

/**
 * Interface for scheduling events in the simulation.
 * Any class implementing this interface must provide an implementation
 * of the schedule method to add events to the event queue.
 *
 * Using an interface here (rather than a concrete Simulator reference) means:
 * 1. Event process() methods are decoupled from the Simulator implementation —
 *    they cannot call run() or other simulator-specific methods.
 * 2. Alternative scheduler implementations can be substituted without changing
 *    any event classes (open/closed principle).
 *
 * @author student
 */
public interface ISchedule {

    /**
     * Schedules the given event by inserting it into the event queue
     * in ascending order of event time.
     *
     * @param e the event to schedule
     */
    public void schedule(Event e);
}
