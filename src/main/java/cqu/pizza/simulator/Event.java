package cqu.pizza.simulator;

import cqu.pizza.lifecycle.Model;

/**
 * Abstract base class for all simulation events.
 * Each event has a scheduled time and must implement a process() method
 * that executes the event's behaviour and schedules successor events.
 *
 * Concrete event subclasses are placed in cqu.pizza.lifecycle.events
 * because they are model-specific. This abstract class is in the simulator
 * package as it is part of the generic simulation infrastructure.
 *
 * @author student
 */
public abstract class Event {

    /** The simulation clock time at which this event is scheduled to occur. */
    private int time;

    /**
     * Constructs an Event scheduled to occur at the given time.
     *
     * @param time the simulation clock time for this event
     */
    public Event(int time) {
        this.time = time;
    }

    /**
     * Returns the simulation clock time at which this event is scheduled.
     *
     * @return the event time
     */
    public int getTime() {
        return time;
    }

    /**
     * Processes this event: performs the model step, updates order state,
     * and schedules any successor events.
     *
     * @param m the pizza kitchen model
     * @param s the scheduler used to add new events to the event queue
     */
    public abstract void process(Model m, ISchedule s);
}
