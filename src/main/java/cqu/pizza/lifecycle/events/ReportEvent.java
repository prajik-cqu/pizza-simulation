package cqu.pizza.lifecycle.events;

import cqu.pizza.lifecycle.Model;
import cqu.pizza.simulator.Event;
import cqu.pizza.simulator.ISchedule;

public class ReportEvent extends Event {

    private int duration;

    public ReportEvent(int duration) {
        super(duration);
        this.duration = duration;
    }

    @Override
    public void process(Model m, ISchedule s) {
        System.out.println("Report to be generated at time " + duration + " - under development");
    }
}
