import java.util.ArrayList;
import java.util.Scanner;

class Item {
    private String name;
    private double pricePerUnit;
    private int inventory;

    public Item(String name, double pricePerUnit, int inventory) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public int getInventory() {
        return inventory;
    }

    public void updateInventory(int quantity) {
        inventory -= quantity;
    }

    @Override
    public String toString() {
        return name + " - $" + pricePerUnit + " per unit - Inventory: " + inventory;
    }

    public double calculateCost(int quantity) {
        return pricePerUnit * quantity;
    }
}
