class Milk extends Dairy {
    public Milk(String name, double pricePerUnit, int inventory) {
        super(name, pricePerUnit, inventory);
    }

    @Override
    public String toString() {
        return "Milk: " + getName() + " - $" + getPricePerUnit() + " - Inventory: " + getInventory();
    }
}
