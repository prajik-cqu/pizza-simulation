package cqu.pizza.lifecycle;

import cqu.pizza.lifecycle.data.IRequestDistribution;
import cqu.pizza.lifecycle.data.Plan;
import cqu.pizza.lifecycle.data.Request;
import cqu.pizza.lifecycle.data.UnlimitedUniform;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Model {

    private IRequestDistribution requests;
    private Plan plan;
    private Report report;
    private ArrayList<Order> allOrders;
    private ArrayList<Order> activeOrders;
    private ArrayList<Order> completedOrders;
    private ArrayList<Order> refusedOrders;
    private int nextIdentifier;
    private LinkedList<Order> queue;

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

    public Request nextRequest() {
        return requests.next();
    }

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

    public int prepare(int time, Order order) {
        int prepTime = plan.getPreparationTime(order.getPizza());
        System.out.printf("t = %d: Order %d preparation will take %d minutes%n",
                time, order.getId(), prepTime);
        return time + prepTime;
    }

    public int cook(int time, Order order) {
        System.out.printf("t = %d: Order %d cooking will take %d minutes%n",
                time, order.getId(), Plan.COOKING_TIME);
        return time + Plan.COOKING_TIME;
    }

    public int box(int time, Order order) {
        System.out.printf("t = %d: Order %d boxing will take %d minute%n",
                time, order.getId(), Plan.BOXING_TIME);
        return time + Plan.BOXING_TIME;
    }

    public void finalise(int time, Order order) {
        order.setFinish(time);
        activeOrders.remove(order);
        completedOrders.add(order);
        System.out.printf("t = %d: Order %d completed%n", time, order.getId());
    }
}
