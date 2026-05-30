package cqu.pizza.lifecycle.events;

import cqu.pizza.lifecycle.Model;
import cqu.pizza.lifecycle.Order;
import cqu.pizza.lifecycle.data.Request;
import cqu.pizza.simulator.Event;
import cqu.pizza.simulator.ISchedule;

public class OrderEvent extends Event {

    private Request request;

    public OrderEvent(Request r) {
        super(r.orderTime());
        this.request = r;
    }

    @Override
    public void process(Model m, ISchedule s) {
        Order order = m.order(request);
        if (order != null) {
            order.stepCompleted();
            s.schedule(new PreparationEvent(getTime(), order));
        }
        Request next = m.nextRequest();
        s.schedule(new OrderEvent(next));
    }
}
