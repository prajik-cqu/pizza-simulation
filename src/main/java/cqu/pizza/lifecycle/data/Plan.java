package cqu.pizza.lifecycle.data;

import java.util.HashMap;

/**
 * Holds the static configuration data for the pizza kitchen simulation.
 * Contains the list of available pizzas and timing constants for cooking and boxing.
 *
 * @author student
 */
public class Plan {

    /** Time (in simulation units) required to cook any pizza. */
    public static final int COOKING_TIME = 5;

    /** Time (in simulation units) required to box any pizza. */
    public static final int BOXING_TIME = 1;

    /** Menu: maps pizza name to Pizza record for fast lookup. */
    private HashMap<String, Pizza> menu;

    /**
     * Array of Pizza records defining all available pizzas.
     * Package-private and static so that UnlimitedUniform (same package) can access via Plan.getPizzas().
     */
    static Pizza[] pizzas = {
        new Pizza("P&P", 5),
        new Pizza("P&O", 4)
    };

    /**
     * Constructs a Plan object and builds the menu HashMap from the pizzas array.
     */
    public Plan() {
        menu = new HashMap<>();
        for (Pizza p : pizzas) {
            menu.put(p.name(), p);
        }
    }

    /**
     * Checks whether a pizza with the given name is on the menu.
     *
     * @param pizzaName the name to look up
     * @return true if the pizza is available, false otherwise
     */
    public boolean onMenu(String pizzaName) {
        return menu.containsKey(pizzaName);
    }

    /**
     * Returns the preparation time for the named pizza.
     *
     * @param pizzaName the name of the pizza
     * @return the preparation time in simulation units, or -1 if not on the menu
     */
    public int getPreparationTime(String pizzaName) {
        Pizza p = menu.get(pizzaName);
        return (p != null) ? p.preparationTime() : -1;
    }

    /**
     * Returns the array of Pizza records.
     * Package-private: intended for use by distribution classes in the same package.
     *
     * @return the pizzas array
     */
    static Pizza[] getPizzas() {
        return pizzas;
    }
}
