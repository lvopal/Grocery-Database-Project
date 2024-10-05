import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create instances of items
        FruitVeg apples = new FruitVeg("Apples", 0.35, 30);
        FruitVeg bananas = new FruitVeg("Bananas", 2.50, 35);
        Dairy milk = new Dairy("Milk", 10.00, 25);
        Meat fish = new Meat("Fish", 9.00, 25);

        // Initialize arrays to store inventory
        ArrayList<Item> groceryInventory = new ArrayList<>();
        groceryInventory.add(new Food("Toilet Paper", 2.00, 10));
        groceryInventory.add(new Food("Soup", 0.99, 25));
        groceryInventory.add(new Food("Cereal", 4.50, 20));
        groceryInventory.add(apples);
        groceryInventory.add(bananas);
        groceryInventory.add(milk);
        groceryInventory.add(fish);

        // Initialize shopping cart array
        ArrayList<Item> shoppingCart = new ArrayList<>();

        // Implement user interaction for adding items to the cart, removing items, and checkout
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add item to cart");
            System.out.println("2. Remove item from cart");
            System.out.println("3. View cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the category (1. Grocery, 2. FruitVeg, 3. Dairy, 4. Meat): ");
                    int categoryChoice = scanner.nextInt();

                    switch (categoryChoice) {
                        case 1:
                            displayItems(groceryInventory.toArray(new Item[0]));
                            addToCart(shoppingCart, groceryInventory.toArray(new Item[0]));
                            break;
                        case 2:
                            displayItems(apples, bananas);
                            addToCart(shoppingCart, apples, bananas);
                            break;
                        case 3:
                            displayItems(milk);
                            addToCart(shoppingCart, milk);
                            break;
                        case 4:
                            displayItems(fish);
                            addToCart(shoppingCart, fish);
                            break;
                        default:
                            System.out.println("Invalid category choice.");
                            break;
                    }
                    break;

                case 2:
                    displayCart(shoppingCart);
                    removeFromCart(shoppingCart);
                    break;

                case 3:
                    displayCart(shoppingCart);
                    break;

                case 4:
                    displayCart(shoppingCart);
                    checkout(shoppingCart);
                    break;

                case 5:
                    // Exit the program
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayItems(Item... items) {
        for (Item item : items) {
            System.out.println(item.toString());
        }
    }

    private static void addToCart(ArrayList<Item> shoppingCart, Item... items) {
        Scanner scanner = new Scanner(System.in);
        for (Item item : items) {
            System.out.println("Enter quantity for " + item.getName() + ": ");
            double quantity = scanner.nextDouble();
            if (quantity > 0 && quantity <= item.getInventory()) {
                // Add the item to the shopping cart
                shoppingCart.add(new Item(item.getName(), item.getPricePerUnit(), (int) quantity));
                // Update the item's inventory
                item.updateInventory((int) quantity);
                System.out.println(quantity + " of " + item.getName() + " added to the cart.");
            } else {
                System.out.println("Item not added or invalid quantity.");
            }
        }
    }

    private static void removeFromCart(ArrayList<Item> shoppingCart) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the index of the item to remove (0 to " + (shoppingCart.size() - 1) + "): ");
        int index = scanner.nextInt();
        if (index >= 0 && index < shoppingCart.size()) {
            Item removedItem = shoppingCart.remove(index);
            System.out.println("Removed: " + removedItem.getName() + " from the cart.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static void displayCart(ArrayList<Item> shoppingCart) {
        if (shoppingCart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            for (int i = 0; i < shoppingCart.size(); i++) {
                Item item = shoppingCart.get(i);
                System.out.println(i + ". " + item.getName() + " - $" + item.getPricePerUnit() + " each - Quantity: " + item.getInventory());
            }
        }
    }

    private static void checkout(ArrayList<Item> shoppingCart) {
        double total = 0;
        System.out.println("Checking out the following items:");
        for (Item item : shoppingCart) {
            double itemTotal = item.getPricePerUnit() * item.getInventory();
            System.out.println(item.getName() + " - $" + item.getPricePerUnit() + " x " + item.getInventory() + " = $" + itemTotal);
            total += itemTotal;
        }

        double taxes = total * 0.1; // Assuming a 10% tax rate
        double grandTotal = total + taxes;

        System.out.println("Total: $" + total);
        System.out.println("Taxes: $" + taxes);
        System.out.println("Grand Total: $" + grandTotal);

        // Reset the shopping cart for a new session
        shoppingCart.clear();
        System.out.println("Thank you for your purchase!");
    }
}