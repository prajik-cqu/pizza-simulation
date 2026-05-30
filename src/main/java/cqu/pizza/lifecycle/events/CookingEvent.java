package cqu.pizza.lifecycle.events;

import cqu.pizza.lifecycle.Model;
import cqu.pizza.lifecycle.Order;
import cqu.pizza.simulator.Event;
import cqu.pizza.simulator.ISchedule;

public class CookingEvent extends Event {

    private Order order;

    public CookingEvent(int time, Order order) {
        super(time);
        this.order = order;
    }

    @Override
    public void process(Model m, ISchedule s) {
        int doneTime = m.cook(getTime(), order);
        order.stepCompleted();
        s.schedule(new BoxingEvent(doneTime, order));
    }
}
