package cqu.pizza.lifecycle.events;

import cqu.pizza.lifecycle.Model;
import cqu.pizza.lifecycle.Order;
import cqu.pizza.simulator.Event;
import cqu.pizza.simulator.ISchedule;

/**
 * Event that models a pizza joining the oven queue (Phase 6).
 * If the queue is empty, cooking starts immediately.
 * If the queue is not empty, the pizza waits until it reaches the front.
 *
 * @author student
 */
public class QueuingEvent extends Event {

    /** The order queuing for the oven. */
    private Order order;

    /**
     * Constructs a QueuingEvent scheduled at the given time for the given order.
     *
     * @param time  the simulation time at which queuing occurs
     * @param order the order joining the oven queue
     */
    public QueuingEvent(int time, Order order) {
        super(time);
        this.order = order;
    }

    /**
     * Processes the queuing event:
     * 1. Invokes the model's queue() method.
     * 2. If the returned time is non-zero (queue was empty), schedules a CookingEvent immediately.
     * 3. Records step completion on the order.
     *
     * @param m the pizza kitchen model
     * @param s the event scheduler
     */
    @Override
    public void process(Model m, ISchedule s) {
        int cookTime = m.queue(getTime(), order);
        order.stepCompleted(); // step 4: cooking
        if (cookTime != 0) {
            s.schedule(new CookingEvent(cookTime, order));
        }
    }
}
