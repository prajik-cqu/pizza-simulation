package cqu.pizza.lifecycle.data;

/**
 * Generates pizza requests at fixed time intervals, cycling through the available pizzas.
 * Implements a uniform distribution of requests with a fixed interval between them.
 *
 * @author student
 */
public class UnlimitedUniform implements IRequestDistribution {

    /** Time interval between consecutive requests. */
    private int interval;

    /** Time for the next request. */
    private int t;

    /** List of available pizzas, used to cycle through them. */
    Pizza[] pizzas;

    /** Tracks the number of requests generated so far. */
    int n;

    /**
     * Constructs an UnlimitedUniform distribution with the given interval.
     * Requests are generated at fixed intervals cycling through the available pizzas.
     *
     * @param interval the number of simulation time units between requests
     */
    public UnlimitedUniform(int interval) {
        this.interval = interval;
        pizzas = Plan.getPizzas();
        t = 0;
        n = 0;
    }

    /**
     * Returns the next pizza request.
     * Cycles through available pizzas. Request 4 (n==3) is hardcoded as a non-menu pizza
     * to exercise the refusal logic during testing.
     *
     * @return a Request containing the pizza name and the scheduled time
     */
    @Override
    public Request next() {
        Request r = new Request(pizzas[n % pizzas.length].name(), t);
        // add a request for a pizza that is not on the menu
        if (n == 3) {
            r = new Request("LOT", t);
        }
        t += interval;
        n++;
        return r;
    }
}
