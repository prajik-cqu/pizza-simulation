package cqu.pizza.lifecycle.events;

import cqu.pizza.lifecycle.Model;
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
        m.order(request);
        Request next = m.nextRequest();
        s.schedule(new OrderEvent(next));
    }
}
