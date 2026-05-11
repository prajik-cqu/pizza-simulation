package cqu.pizza.simulator;

import cqu.pizza.lifecycle.Model;

import java.util.ArrayList;

/**
 * Runs the discrete event simulation.
 * Maintains an ordered event queue and processes events in time order
 * until the stop time is reached or the queue is empty.
 *
 * @author student
 */
public class Simulator implements ISchedule {

    /** The ordered list of pending events (ascending by time). */
    private ArrayList<Event> events;

    /** The pizza kitchen model being simulated. */
    private Model model;

    /** The current simulation clock time. */
    private int clock;

    /**
     * Constructs a Simulator with a reference to the pizza kitchen model.
     *
     * @param model the model to simulate
     */
    public Simulator(Model model) {
        this.model = model;
        this.events = new ArrayList<>();
        this.clock = 0;
    }

    /**
     * Initialises the event queue with the first order event and the report event.
     * The report event is always inserted so it occurs at the stop time and is
     * processed last (inserted before any event at the same time).
     *
     * @param start  the first event to process (an OrderEvent at time 0)
     * @param report the report event scheduled at the simulation stop time
     */
    public void initialize(Event start, Event report) {
        events.clear();
        schedule(start);
        schedule(report);
    }

    /**
     * Runs the simulation until the stop time is reached or the event queue is empty.
     * The clock is updated to the time of each event as it is processed.
     * Do NOT modify this method.
     *
     * @param stopTime the simulation time at which to stop
     */
    public void run(int stopTime) {
        if ((events == null) || events.isEmpty()) {
            return;
        }
        Event e = events.remove(0);
        clock = e.getTime();
        // events queue will never become empty as after the first event is
        // added, every arrival event will generate a new arrival event
        // (which may be greater than the stop time)
        while (clock <= stopTime) {
            e.process(model, this); // the this argument means that we are
            // passing a reference to this simulator
            // object to the event's process method –
            // so it will be able to schedule events
            e = events.remove(0);
            clock = e.getTime();
        }
    }

    /**
     * Inserts an event into the event queue in ascending time order.
     * Events with the same time are inserted BEFORE existing events at that time,
     * ensuring the ReportEvent (added first at stop time) is always processed last.
     *
     * @param e the event to add to the queue
     */
    @Override
    public void schedule(Event e) {
        int i = 0;
        while (i < events.size() && events.get(i).getTime() < e.getTime()) {
            i++;
        }
        events.add(i, e);
    }
}
