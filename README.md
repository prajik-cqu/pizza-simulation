# PizzaSimulation

A discrete event-driven simulation of a pizza kitchen, built with JavaFX GUI.

Developed as part of COIT20256 Assessment 3 at CQUniversity.

---

## Requirements

- **Java 17** (JDK 17)
- **JavaFX 17**
- **Apache NetBeans** (recommended IDE)
- **Maven** (bundled with NetBeans)

---

## How to Run in Apache NetBeans

1. Open Apache NetBeans.
2. Go to **File > Open Project** and select the `PizzaSimulation` folder.
3. NetBeans will detect it as a Maven project automatically.
4. Right-click the project in the **Projects** panel and choose **Run**.
5. The GUI window titled **Simulation** will open.

---

## How to Run from Terminal (Maven)

```bash
cd PizzaSimulation
mvn javafx:run
```

---

## How to Use the Application

1. Enter a **positive integer** in the **Duration** field (e.g. `20`).
2. Click **Run** to start the simulation.
   - Console output will show the step-by-step trace.
   - The report will appear in the **Report** text area.
3. Enter a **file name/path** in the **File Name** field (e.g. `report.txt`).
4. Click **Save** to save the report to that file.
5. Click **Reset** to clear all fields and the report area.
6. Click **Exit** to close the application.

---

## Project Package Structure

```
cqu.pizza                        - App + Controller (JavaFX entry point)
cqu.pizza.lifecycle              - Model, Order, Report
cqu.pizza.lifecycle.data         - Pizza (record), Request (record), Plan,
                                   IRequestDistribution, UnlimitedUniform
cqu.pizza.lifecycle.events       - OrderEvent, PreparationEvent, QueuingEvent,
                                   CookingEvent, BoxingEvent, FinalisationEvent,
                                   ReportEvent
cqu.pizza.simulator              - ISchedule (interface), Event (abstract),
                                   Simulator
```

---

## Phased Development (Git Branches)

Each phase is developed on its own branch and merged into `main`:

| Branch                   | Description                                      |
|--------------------------|--------------------------------------------------|
| `phase-1-gui`            | JavaFX GUI with Reset and Exit working           |
| `phase-2-simulator`      | Simulator infrastructure and order arrivals      |
| `phase-3-infinite-ovens` | Full pizza lifecycle with infinite ovens         |
| `phase-4-report`         | Report generated and displayed in GUI            |
| `phase-5-save`           | Save report to file                              |
| `phase-6-oven-queue`     | Single oven with oven queue (LinkedList)         |

To checkout a specific phase:
```bash
git checkout phase-1-gui
```

---

## Sample Simulation Data (used in testing)

| Parameter            | Value                  |
|----------------------|------------------------|
| Pizzas on menu       | P&P (prep=5), P&O (prep=4) |
| Cooking time         | 5 time units           |
| Boxing time          | 1 time unit            |
| Request interval     | Every 3 time units     |
| Non-menu pizza       | LOT (refused at t=9)   |

---

## Validation

- Duration must be a **positive integer** — error shown in report area if not.
- Save requires a **file name** to be entered and a **report** to exist.
- File write errors are caught and displayed in the report area.
