import java.util.ArrayList;
import java.util.Scanner;

public class GroceryDatabase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Item> shoppingCart = new ArrayList<>();

        // Initialize inventory
        Food toiletPaper = new Food("Toilet Paper", 2.0, 10);
        Food soup = new Food("Soup", 0.99, 25);
        Food cereal = new Food("Cereal", 4.50, 20);

        FruitVeg apples = new FruitVeg("Apples", 0.35, 30);
        FruitVeg bananas = new FruitVeg("Bananas", 2.50, 35);
        FruitVeg broccoli = new FruitVeg("Broccoli", 5.0, 25);

        Dairy milk = new Milk("Milk", 10.0, 25);
        Dairy eggs = new Dairy("Eggs", 6.0, 20);
        Dairy yogurt = new Dairy("Yogurt", 4.50, 30);

        Meat fish = new Meat("Fish", 9.0, 25);
        Meat chicken = new Meat("Chicken", 5.0, 30);
        Meat beef = new Meat("Beef", 22.0, 45);

        while (true) {
            System.out.println("Choose a category: ");
            System.out.println("1. Food");
            System.out.println("2. FruitVeg");
            System.out.println("3. Dairy");
            System.out.println("4. Meat");
            System.out.println("5. Checkout");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayCategory("Food", toiletPaper, soup, cereal);
                    break;
                case 2:
                    displayCategory("FruitVeg", apples, bananas, broccoli);
                    break;
                case 3:
                    displayCategory("Dairy", milk, eggs, yogurt);
                    break;
                case 4:
                    displayCategory("Meat", fish, chicken, beef);
                    break;
                case 5:
                    checkout(shoppingCart);
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displayCategory(String category, Item... items) {
        System.out.println("Items in " + category + ":");
        for (Item item : items) {
            System.out.println(item.toString());
        }
        System.out.println();
        addToCart(items);
    }

    private static void addToCart(Item... items) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Item> shoppingCart = new ArrayList<>(); // Ensure this is accessible within this method
        for (Item item : items) {
            System.out.println("Enter quantity for " + item.getName() + ": ");
            int quantity = scanner.nextInt();
            if (quantity > 0 && quantity <= item.getInventory()) {
                shoppingCart.add(new Item(item.getName(), item.getPricePerUnit(), quantity));
                item.updateInventory(quantity);
                System.out.println(quantity + " of " + item.getName() + " added to the cart.");
            } else {
                System.out.println("Invalid quantity. Please try again.");
            }
        }
    }

    private static void checkout(ArrayList<Item> shoppingCart) {
        double total = 0.0;

        System.out.println("Your order:");
        for (Item item : shoppingCart) {
            System.out.println(item.getName() + " - $" + item.getPricePerUnit() + " per unit - Quantity: " + item.getInventory());
            total += item.calculateCost(item.getInventory());
        }

        // Calculate taxes (you can customize tax rates as needed)
        double taxRate = 0.1; // 10% tax rate
        double taxes = total * taxRate;

        double grandTotal = total + taxes;

        System.out.println("Total: $" + total);
        System.out.println("Taxes: $" + taxes);
        System.out.println("Grand Total (including taxes): $" + grandTotal);
    }
}
