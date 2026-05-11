package cqu.pizza.lifecycle.data;

/**
 * Record representing a customer pizza request.
 * Stores the type of pizza requested and the time the request was made.
 *
 * @param pizza     the name of the pizza requested
 * @param orderTime the simulation clock time at which the request was made
 */
public record Request(String pizza, int orderTime) {
}
