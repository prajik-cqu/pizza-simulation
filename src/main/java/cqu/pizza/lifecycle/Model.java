package cqu.pizza.lifecycle;

import cqu.pizza.lifecycle.data.IRequestDistribution;
import cqu.pizza.lifecycle.data.Plan;
import cqu.pizza.lifecycle.data.Request;
import cqu.pizza.lifecycle.data.UnlimitedUniform;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Models the pizza kitchen being simulated.
 * Tracks all orders, active orders, completed orders, refused orders,
 * and the oven queue. Also drives each processing step of an order.
 *
 * @author student
 */
public class Model {

    /** The request distribution strategy used to generate pizza requests. */
    private IRequestDistribution requests;

    /** The plan holding the menu and timing constants. */
    private Plan plan;

    /** The simulation report generated at the end of the run. */
    private Report report;

    /** All orders received during the simulation (accepted and refused). */
    private ArrayList<Order> allOrders;

    /** Orders currently being processed (not yet completed or refused). */
    private ArrayList<Order> activeOrders;

    /** Orders that have been completed successfully. */
    private ArrayList<Order> completedOrders;

    /** Orders that were refused because the pizza was not on the menu. */
    private ArrayList<Order> refusedOrders;

    /** The next identifier to assign to a new order. Starts at 1. */
    private int nextIdentifier;

    /** The oven queue: the first entry is the pizza currently in the oven. */
    private LinkedList<Order> queue;

    /**
     * Constructs the Model, initialising all lists and creating the plan and request distribution.
     * The interval (3) passed to UnlimitedUniform controls how often requests arrive.
     * Change this value here to test different intervals.
     */
    public Model() {
        plan = new Plan();
        requests = new UnlimitedUniform(3);
        allOrders = new ArrayList<>();
        activeOrders = new ArrayList<>();
        completedOrders = new ArrayList<>();
        refusedOrders = new ArrayList<>();
        queue = new LinkedList<>();
        nextIdentifier = 1;
    }

    /**
     * Gets the next pizza request from the distribution.
     *
     * @return the next Request
     */
    public Request nextRequest() {
        return requests.next();
    }

    /**
     * Creates and records a new Order for the given request.
     * If the pizza is not on the menu the order is refused: it is added to the
     * refused list, its finish time is set and null is returned.
     * If the pizza is on the menu the order is added to active and all orders lists.
     *
     * @param r the request to action
     * @return the new Order, or null if the pizza is not on the menu
     */
    public Order order(Request r) {
        Order o = new Order(nextIdentifier, r.orderTime(), r.pizza());
        nextIdentifier++;
        allOrders.add(o);

        if (!plan.onMenu(r.pizza())) {
            System.out.printf("t = %d: Order %d for %s refused%n",
                    r.orderTime(), o.getId(), r.pizza());
            o.setFinish(r.orderTime());
            refusedOrders.add(o);
            return null;
        }

        System.out.printf("t = %d: Order %d for %s actioned%n",
                r.orderTime(), o.getId(), r.pizza());
        activeOrders.add(o);
        return o;
    }

    /**
     * Prepares the pizza for the given order.
     * Prints a trace message and returns the time preparation will be completed.
     *
     * @param time  the current simulation clock time
     * @param order the order being prepared
     * @return the simulation time when preparation will be finished
     */
    public int prepare(int time, Order order) {
        int prepTime = plan.getPreparationTime(order.getPizza());
        System.out.printf("t = %d: Order %d preparation will take %d minutes%n",
                time, order.getId(), prepTime);
        return time + prepTime;
    }

    /**
     * Cooks the pizza for the given order.
     * Prints a trace message and returns the time cooking will be completed.
     *
     * @param time  the current simulation clock time
     * @param order the order being cooked
     * @return the simulation time when cooking will be finished
     */
    public int cook(int time, Order order) {
        System.out.printf("t = %d: Order %d cooking will take %d minutes%n",
                time, order.getId(), Plan.COOKING_TIME);
        return time + Plan.COOKING_TIME;
    }

    /**
     * Boxes the pizza for the given order.
     * Prints a trace message and returns the time boxing will be completed.
     *
     * @param time  the current simulation clock time
     * @param order the order being boxed
     * @return the simulation time when boxing will be finished
     */
    public int box(int time, Order order) {
        System.out.printf("t = %d: Order %d boxing will take %d minute%n",
                time, order.getId(), Plan.BOXING_TIME);
        return time + Plan.BOXING_TIME;
    }

    /**
     * Finalises a completed order: sets its finish time, moves it from
     * active to completed, and prints a trace message.
     *
     * @param time  the current simulation clock time (finish time)
     * @param order the order being finalised
     */
    public void finalise(int time, Order order) {
        order.setFinish(time);
        activeOrders.remove(order);
        completedOrders.add(order);
        System.out.printf("t = %d: Order %d completed%n", time, order.getId());
    }

    /**
     * Adds an order to the oven queue.
     * If the queue is empty, the order goes straight to the front (0 ahead)
     * and cooking can start immediately — returns the current time.
     * If the queue is not empty, the order joins the end and returns 0
     * to indicate it must wait.
     *
     * @param time  the current simulation clock time
     * @param order the order to queue
     * @return the time cooking should start (current time if queue was empty), or 0 if queued
     */
    public int queue(int time, Order order) {
        int ahead = queue.size();
        System.out.printf("t = %d: Order %d has %d orders ahead of it in the queue%n",
                time, order.getId(), ahead);
        queue.add(order);
        if (ahead == 0) {
            return time;
        }
        return 0;
    }

    /**
     * Peeks at the order at the front of the oven queue without removing it.
     *
     * @return the order at the front of the queue, or null if empty
     */
    public Order peek() {
        return queue.peek();
    }

    /**
     * Removes the order at the front of the oven queue (it has finished cooking).
     * Prints a trace message.
     *
     * @param time the current simulation clock time
     * @return the order removed from the front of the queue
     */
    public Order remove(int time) {
        Order o = queue.remove();
        System.out.printf("t = %d: Order %d removed from oven%n", time, o.getId());
        return o;
    }

    /**
     * Generates the simulation report and stores it in the report instance variable.
     *
     * @param duration the total duration of the simulation run
     */
    public void report(int duration) {
        System.out.printf("t = %d: Simulation stopped%n", duration);
        this.report = new Report(duration, completedOrders, refusedOrders, activeOrders);
    }

    /**
     * Returns the simulation report.
     *
     * @return the Report object, or null if the simulation has not been run
     */
    public Report getReport() {
        return report;
    }
}
