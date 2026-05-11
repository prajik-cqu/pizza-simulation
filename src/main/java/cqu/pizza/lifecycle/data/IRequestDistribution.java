package cqu.pizza.lifecycle.data;

/**
 * Interface for pizza request distribution strategies.
 * Any class implementing this interface must provide a way to generate the next pizza request.
 *
 * @author student
 */
public interface IRequestDistribution {

    /**
     * Returns the next pizza request.
     *
     * @return a Request record containing the pizza name and the time of the request
     */
    public Request next();
}
