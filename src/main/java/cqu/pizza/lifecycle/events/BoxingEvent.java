package cqu.pizza.lifecycle.events;

import cqu.pizza.lifecycle.Model;
import cqu.pizza.lifecycle.Order;
import cqu.pizza.simulator.Event;
import cqu.pizza.simulator.ISchedule;

/**
 * Event that models the boxing of a cooked pizza.
 * Schedules a FinalisationEvent on completion.
 *
 * @author student
 */
public class BoxingEvent extends Event {

    /** The order being boxed. */
    private Order order;

    /**
     * Constructs a BoxingEvent scheduled at the given time for the given order.
     *
     * @param time  the simulation time at which boxing starts
     * @param order the order being boxed
     */
    public BoxingEvent(int time, Order order) {
        super(time);
        this.order = order;
    }

    /**
     * Processes the boxing event:
     * 1. Invokes the model's box() method to get the completion time.
     * 2. Records step completion on the order.
     * 3. Schedules a FinalisationEvent at the completion time.
     *
     * @param m the pizza kitchen model
     * @param s the event scheduler
     */
    @Override
    public void process(Model m, ISchedule s) {
        // Phase 6: remove the cooked order from the oven queue
        m.remove(getTime());

        // Box the order
        int doneTime = m.box(getTime(), order);
        order.stepCompleted(); // step 5: boxing done, next is finalisation

        // If another order is waiting at the front of the queue, start cooking it now
        Order next = m.peek();
        if (next != null) {
            s.schedule(new CookingEvent(getTime(), next));
        }

        // Schedule finalisation for this order
        s.schedule(new FinalisationEvent(doneTime, order));
    }
}
