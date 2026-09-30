import java.util.*;

public class Main {

    public static void main(String[] args) {


        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});


        LinkedList<String[]> drinks = new LinkedList<>();
        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});


        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("orders.txt")
        );


        while (scanner.hasNext()) {
            String[] order = new String[4];
            order[0] = scanner.next();
            order[1] = scanner.next();
            order[2] = scanner.next();
            order[3] = scanner.next();

            orders.add(order);
        }

        scanner.close();



        Queue<String[]> queue = new LinkedList<>();
        while (!orders.isEmpty()) {
            queue.add(orders.removeFirst());
        }



        LinkedList<String[]> success = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String food = order[1];
            String drink = order[2];


            int foodStock = 1;
            int drinkStock = 1;

            if (!food.equals("-")) {
                for (String[] item : foods) {
                    if (item[0].equals(food)) {
                        foodStock = Integer.parseInt(item[1]);
                    }
                }
            }

            if (!drink.equals("-")) {
                for (String[] item : drinks) {
                    if (item[0].equals(drink)) {
                        drinkStock = Integer.parseInt(item[1]);
                    }
                }
            }



            if (foodStock > 0 && drinkStock > 0) {
                if (!food.equals("-")) {
                    for (String[] item : foods) {
                        if (item[0].equals(food)) {
                            item[1] = String.valueOf(
                                    Integer.parseInt(item[1]) - 1
                            );
                        }
                    }
                }


                if (!drink.equals("-")) {
                    for (String[] item : drinks) {
                        if (item[0].equals(drink)) {
                            item[1] = String.valueOf(
                                    Integer.parseInt(item[1]) - 1
                            );
                        }
                    }
                }

                success.add(order);
            } else {
                failed.push(order);
            }
        }



        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : success) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }



        System.out.println("=== Remaining Food Stock ===");
        for (String[] food : foods) {
            System.out.println(
                    food[0] + " : " + food[1]
            );
        }



        System.out.println("=== Remaining Drink Stock ===");
        for (String[] drink : drinks) {
            System.out.println(
                    drink[0] + " : " + drink[1]
            );
        }



        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()) {
            String[] order = failed.pop();
            System.out.println(
                    order[0] + " " +
                    order[1] + " " +
                    order[2] + " " +
                    order[3]
            );

        }

    }
}