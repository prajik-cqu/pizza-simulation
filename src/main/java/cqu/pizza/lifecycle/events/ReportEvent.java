package cqu.pizza.lifecycle.events;

import cqu.pizza.lifecycle.Model;
import cqu.pizza.simulator.Event;
import cqu.pizza.simulator.ISchedule;

/**
 * Event that generates the final simulation report.
 * Scheduled to occur at the simulation stop time and is always the last event processed.
 *
 * @author student
 */
public class ReportEvent extends Event {

    /** The duration of the simulation run. */
    private int duration;

    /**
     * Constructs a ReportEvent scheduled at the given duration (stop time).
     *
     * @param duration the simulation stop time
     */
    public ReportEvent(int duration) {
        super(duration);
        this.duration = duration;
    }

    /**
     * Processes the report event by invoking the model's report() method
     * to generate and store the final simulation report.
     *
     * @param m the pizza kitchen model
     * @param s the event scheduler (not used — no new events are scheduled)
     */
    @Override
    public void process(Model m, ISchedule s) {
        m.report(duration);
    }
}
