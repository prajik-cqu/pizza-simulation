package cqu.pizza.lifecycle.data;

import java.util.HashMap;

public class Plan {

    public static final int COOKING_TIME = 5;
    public static final int BOXING_TIME = 1;

    private HashMap<String, Pizza> menu;

    static Pizza[] pizzas = {
        new Pizza("P&P", 5),
        new Pizza("P&O", 4)
    };

    public Plan() {
        menu = new HashMap<>();
        for (Pizza p : pizzas) {
            menu.put(p.name(), p);
        }
    }

    public boolean onMenu(String pizzaName) {
        return menu.containsKey(pizzaName);
    }

    public int getPreparationTime(String pizzaName) {
        Pizza p = menu.get(pizzaName);
        return (p != null) ? p.preparationTime() : -1;
    }

    static Pizza[] getPizzas() {
        return pizzas;
    }
}
