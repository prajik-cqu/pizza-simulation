package cqu.pizza.lifecycle.data;

/**
 * Record representing a pizza on the menu.
 * Stores the pizza name and its preparation time.
 *
 * @param name            the name of the pizza (e.g. "P&P")
 * @param preparationTime the time (in simulation units) required to prepare this pizza
 */
public record Pizza(String name, int preparationTime) {
}
