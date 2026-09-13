import java.util.Scanner;

public class Canteen {
    public static void main(String[] args) {
        String[] menu = {
            "Pork Sinigang",
            "Pork Sisig",
            "Fried Chicken",
            "Chicken Tinola",
            "Pinakbet",
            "Laing"
        };

        double[] price = {90.00, 80.00, 65.00, 70.00, 65.00, 60.00};

        System.out.println("=====  Ron Xyver's Eatery  =====");
        for (int i = 0; i < menu.length; i++) {
            System.out.printf("%d. %-20s - $%.2f%n", (i + 1), menu[i], price[i]);
        }

        System.out.println();

        Scanner input = new Scanner(System.in);
        boolean isOrdering = true;
        int totalItems = 0;
        double totalSubtotal = 0.0;
        double totalDiscount = 0.0;

        do {
            System.out.print("Enter item number: ");
            int menuItem = input.nextInt();

            System.out.print("Enter quantity: ");
            int itemQuantity = input.nextInt();

            if (menuItem < 1 || menuItem > menu.length || itemQuantity < 1 || itemQuantity > 10) {
                System.out.println();

                System.out.println("Invalid order! Please enter a valid item and quantity.");
            } else {
                System.out.print("Are you a student? (Y/N): ");
                char aStudent = input.next().charAt(0);

                System.out.println();

                if (aStudent != 'Y' && aStudent != 'N') {
                    System.out.println("Invalid order! Please enter a valid response.");
                } else {
                    double subtotal = price[menuItem - 1] * itemQuantity;

                    double discount = 0.0;
                    if (aStudent == 'Y') {
                        discount = subtotal * (subtotal >= 500 ? 0.15 : 0.10);
                    } else if (subtotal >= 500) {
                        discount = subtotal * 0.05;
                    }

                    System.out.printf("%-12s: $%.2f%n", "Subtotal", subtotal);
                    System.out.printf("%-12s: $%.2f%n", "Discount", discount);
                    System.out.printf("%-12s: $%.2f%n", "Order total", subtotal - discount);

                    totalItems += itemQuantity;
                    totalSubtotal += subtotal;
                    totalDiscount += discount;
                }
            }

            System.out.println();

            System.out.print("Do you want to order again? (Y/N): ");
            char orderingAgain = input.next().charAt(0);
            if (orderingAgain == 'N') {
                isOrdering = false;
            } else if (orderingAgain != 'Y') {
                System.out.println();

                System.out.println("Invalid response! Attempting to order again.");
            }

            System.out.println();
        } while (isOrdering);

        System.out.println("=======  Order Summary  =======");
        System.out.printf("%-22s: %d%n", "Total items", totalItems);
        System.out.printf("%-22s: $%.2f%n", "Total before discount", totalSubtotal);
        System.out.printf("%-22s: $%.2f%n", "Total discount", totalDiscount);
        System.out.printf("%-22s: $%.2f%n", "Final amount", totalSubtotal - totalDiscount);
        System.out.println("Thank you for ordering!");

        input.close();
    }
}