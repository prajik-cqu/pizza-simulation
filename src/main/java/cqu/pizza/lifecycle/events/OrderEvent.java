package cqu.pizza.lifecycle.events;

import cqu.pizza.lifecycle.Model;
import cqu.pizza.lifecycle.Order;
import cqu.pizza.lifecycle.data.Request;
import cqu.pizza.simulator.Event;
import cqu.pizza.simulator.ISchedule;

/**
 * Event that models the receipt of a pizza order request.
 * When processed, it actions the order and schedules the next order event.
 * If the pizza is on the menu a preparation event is also scheduled (Phase 3+).
 *
 * @author student
 */
public class OrderEvent extends Event {

    /** The pizza request associated with this order event. */
    private Request request;

    /**
     * Constructs an OrderEvent for the given request.
     * The event is scheduled at the time specified in the request.
     *
     * @param r the pizza request
     */
    public OrderEvent(Request r) {
        super(r.orderTime());
        this.request = r;
    }

    /**
     * Processes the order event:
     * 1. Actions the order via the model.
     * 2. If the order is accepted, schedules a PreparationEvent.
     * 3. Schedules the next OrderEvent for the following request.
     *
     * @param m the pizza kitchen model
     * @param s the event scheduler
     */
    @Override
    public void process(Model m, ISchedule s) {
        // 1. Action the order
        Order order = m.order(request);

        // 2. If order accepted, schedule preparation (added in Phase 3)
        if (order != null) {
            order.stepCompleted(); // step 2: preparation scheduled
            s.schedule(new PreparationEvent(getTime(), order));
        }

        // 3. Schedule next order arrival
        Request next = m.nextRequest();
        s.schedule(new OrderEvent(next));
    }
}
