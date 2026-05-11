package cqu.pizza.lifecycle.events;

import cqu.pizza.lifecycle.Model;
import cqu.pizza.lifecycle.Order;
import cqu.pizza.simulator.Event;
import cqu.pizza.simulator.ISchedule;

/**
 * Event that models the preparation of a pizza.
 * Schedules a QueuingEvent (Phase 6) or CookingEvent (Phase 3-5) on completion.
 *
 * @author student
 */
public class PreparationEvent extends Event {

    /** The order being prepared. */
    private Order order;

    /**
     * Constructs a PreparationEvent scheduled at the given time for the given order.
     *
     * @param time  the simulation time at which preparation starts
     * @param order the order being prepared
     */
    public PreparationEvent(int time, Order order) {
        super(time);
        this.order = order;
    }

    /**
     * Processes the preparation event:
     * 1. Invokes the model's prepare() method to get the completion time.
     * 2. Records step completion on the order.
     * 3. Schedules a CookingEvent at the completion time (Phase 3-5)
     *    or a QueuingEvent (Phase 6).
     *
     * @param m the pizza kitchen model
     * @param s the event scheduler
     */
    @Override
    public void process(Model m, ISchedule s) {
        int doneTime = m.prepare(getTime(), order);
        order.stepCompleted(); // step 3: queuing
        // Phase 6: schedule queuing event (pizza must wait for the oven)
        s.schedule(new QueuingEvent(doneTime, order));
    }
}
