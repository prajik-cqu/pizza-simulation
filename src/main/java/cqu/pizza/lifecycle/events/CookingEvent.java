package cqu.pizza.lifecycle.events;

import cqu.pizza.lifecycle.Model;
import cqu.pizza.lifecycle.Order;
import cqu.pizza.simulator.Event;
import cqu.pizza.simulator.ISchedule;

/**
 * Event that models the cooking of a pizza in the oven.
 * Schedules a BoxingEvent on completion.
 *
 * @author student
 */
public class CookingEvent extends Event {

    /** The order being cooked. */
    private Order order;

    /**
     * Constructs a CookingEvent scheduled at the given time for the given order.
     *
     * @param time  the simulation time at which cooking starts
     * @param order the order being cooked
     */
    public CookingEvent(int time, Order order) {
        super(time);
        this.order = order;
    }

    /**
     * Processes the cooking event:
     * 1. Invokes the model's cook() method to get the completion time.
     * 2. Records step completion on the order.
     * 3. Schedules a BoxingEvent at the completion time.
     *
     * @param m the pizza kitchen model
     * @param s the event scheduler
     */
    @Override
    public void process(Model m, ISchedule s) {
        int doneTime = m.cook(getTime(), order);
        order.stepCompleted(); // step 4: boxing
        s.schedule(new BoxingEvent(doneTime, order));
    }
}
