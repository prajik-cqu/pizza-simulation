package cqu.pizza.lifecycle.events;

import cqu.pizza.lifecycle.Model;
import cqu.pizza.lifecycle.Order;
import cqu.pizza.simulator.Event;
import cqu.pizza.simulator.ISchedule;

/**
 * Event that models the finalisation of a completed order.
 * Sets the finish time, moves the order from active to completed.
 * No new events are scheduled.
 *
 * @author student
 */
public class FinalisationEvent extends Event {

    /** The order being finalised. */
    private Order order;

    /**
     * Constructs a FinalisationEvent scheduled at the given time for the given order.
     *
     * @param time  the simulation time at which finalisation occurs
     * @param order the order being finalised
     */
    public FinalisationEvent(int time, Order order) {
        super(time);
        this.order = order;
    }

    /**
     * Processes the finalisation event:
     * 1. Invokes the model's finalise() method.
     * 2. Records step completion on the order.
     * No new events are scheduled.
     *
     * @param m the pizza kitchen model
     * @param s the event scheduler (not used)
     */
    @Override
    public void process(Model m, ISchedule s) {
        m.finalise(getTime(), order);
        // No stepCompleted() here: finalise() completes the order.
        // In phase 3-5 the step is 5 (finished) after boxing increments to 5.
    }
}
