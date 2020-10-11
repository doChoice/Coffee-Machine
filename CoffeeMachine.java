package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Machine machine = new Machine();
        Action state = null;
        while (state != Action.EXIT) {
            state = machine.action();
            machine.choiceOfOption(state);
        }
    }
}

class Machine {
    private static Scanner scanner;
    private static int water;
    private static int milk;
    private static int coffeeBeans;
    private static int disposableCups;
    private static int money;

    Machine() {

        scanner = new Scanner(System.in);
        water = 400;
        milk = 540;
        coffeeBeans = 120;
        disposableCups = 9;
        money = 550;
    }


    static Action action() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        Action state = null;
        String input = scanner.next();
        switch (input) {
            case "buy":
                state = Action.BUY;
                break;
            case "fill":
                state = Action.FILL;
                break;
            case "take":
                state = Action.TAKE;
                break;
            case "remaining":
                state = Action.REMAINING;
                break;
            case "exit":
                state = Action.EXIT;
                break;
        }
        return state;
    }

    static void choiceOfOption(Action option) {
        switch (option) {
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                String choice = scanner.next();
                buy(choice);
                break;
            case FILL:
                fill();
                break;
            case TAKE:
                take();
                break;
            case REMAINING:
                printStatus();
                break;
        }
    }

    private static void buy(String number) {
        Coffee choiceCoffee;
        switch (number) {
            case "1":
                choiceCoffee = Coffee.ESPRESSO;
                makeCoffee(choiceCoffee);
                break;
            case "2":
                choiceCoffee = Coffee.LATTE;
                makeCoffee(choiceCoffee);
                break;
            case "3":
                choiceCoffee = Coffee.CAPPUCCINO;
                makeCoffee(choiceCoffee);
                break;
            case "back":
                break;
        }
    }
    private static void makeCoffee(Coffee coffee) {

        if (water - coffee.getWATER() < 0) {
            System.out.printf("Sorry, not enough water!%n");
        } else if (milk - coffee.getMILK() < 0) {
            System.out.printf("Sorry, not enough milk!%n");
        } else if (coffeeBeans - coffee.getCOFFEE_BEANS() < 0) {
            System.out.printf("Sorry, not enough coffee beans!%n");
        } else if (disposableCups - coffee.getDISPOSABLE_CUPS() < 0) {
            System.out.printf("Sorry, not enough disposable cups!%n");
        } else {
            water -= coffee.getWATER();
            milk -= coffee.getMILK();
            coffeeBeans -= coffee.getCOFFEE_BEANS();
            disposableCups -= coffee.getDISPOSABLE_CUPS();
            money += coffee.getMONEY();
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    private static void fill() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffeeBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        disposableCups += scanner.nextInt();

    }

    private static void take() {
        System.out.printf("I gave you $%s%n", money);
        System.out.println("");
        money = 0;
    }

    private static void printStatus() {
        System.out.printf("The coffee machine has:%n" +
                "%s of water%n%s of milk%n" +
                "%s of coffee beans%n" +
                "%s of disposable cups%n" +
                "$%s of money%n%n", water, milk, coffeeBeans, disposableCups, money);
    }
}

enum Action {
    BUY, FILL, TAKE, REMAINING, EXIT
}

enum Coffee {
    ESPRESSO(250, 0, 16, 1, 4),
    LATTE(350, 75, 20, 1, 7),
    CAPPUCCINO(200, 100, 12, 1, 6);

    private final int WATER;
    private final int MILK;
    private final int COFFEE_BEANS;
    private final int DISPOSABLE_CUPS;
    private final int MONEY;

    Coffee(int WATER, int MILK, int COFFEE_BEANS, int DISPOSABLE_CUPS, int MONEY) {
        this.WATER = WATER;
        this.MILK = MILK;
        this.COFFEE_BEANS = COFFEE_BEANS;
        this.DISPOSABLE_CUPS = DISPOSABLE_CUPS;
        this.MONEY = MONEY;
    }

    public int getWATER() {
        return WATER;
    }

    public int getMILK() {
        return MILK;
    }

    public int getCOFFEE_BEANS() {
        return COFFEE_BEANS;
    }

    public int getDISPOSABLE_CUPS() {
        return DISPOSABLE_CUPS;
    }

    public int getMONEY() {
        return MONEY;
    }

}
/*public class CoffeeMachine {

    private static int[] ingredientsInTheCoffeeMachine = new int[]{400, 540, 120, 9, 550};
    private static int[] missingIngredients = new int[5];
    private static boolean chekIngredients = true;
    private static final int[] ingredientsForEspresso = new int[]{-250, 0, -16, -1, 4};
    private static final int[] ingredientsForLatte = new int[]{-350, -75, -20, -1, 7};
    private static final int[] ingredientsForCappuccino = new int[]{-200, -100, -12, -1, 6};
    private static final String[] nameOfIngredients = new String[]{"water", "milk", "coffee beans","disposable cups", "money"};

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String option = "start";
        while (!option.equals("exit")) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            option = scanner.nextLine();
            System.out.println("");
            choiceOfOption(option, scanner, ingredientsInTheCoffeeMachine);
        }
    }

    private static void printStatus(int[] ingredients) {
        System.out.printf("The coffee machine has:%n%s of water%n%s of milk%n%s of coffee beans%n%s of disposable cups%n$%s of money%n%n", ingredients[0], ingredients[1], ingredients[2], ingredients[3], ingredients[4]);
    }

    private static void choiceOfOption(String option, Scanner scanner, int[] ingredients) {
        switch (option) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                String choice = scanner.next();
                buy(choice);
                break;
            case "fill":
                fill(scanner);
                break;
            case "take":
                take(ingredients[4]);
                break;
            case "remaining":
                printStatus(ingredients);
                break;
        }
    }

    private static void buy(String number) {

        switch (number) {
            case "1":
                check(ingredientsInTheCoffeeMachine, ingredientsForEspresso);
                if (chekIngredients) {
                    for (int i = 0; i < ingredientsInTheCoffeeMachine.length; i++) {
                       ingredientsInTheCoffeeMachine[i] += ingredientsForEspresso[i];
                    }
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    for (int i = 0; i < nameOfIngredients.length; i++) {
                        if (missingIngredients[i] == 1) {
                            System.out.printf("Sorry, not enough %s!%n", nameOfIngredients[i]);
                        }
                    }
                }
                break;
            case "2":
                check(ingredientsInTheCoffeeMachine, ingredientsForLatte);
                if (chekIngredients) {
                    for (int i = 0; i < ingredientsInTheCoffeeMachine.length; i++) {
                        ingredientsInTheCoffeeMachine[i] += ingredientsForLatte[i];
                    }
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    for (int i = 0; i < nameOfIngredients.length; i++) {
                        if (missingIngredients[i] == 1) {
                            System.out.printf("Sorry, not enough %s!%n", nameOfIngredients[i]);
                        }
                    }
                }
                break;
            case "3":
                check(ingredientsInTheCoffeeMachine, ingredientsForCappuccino);
                if (chekIngredients) {
                    for (int i = 0; i < ingredientsInTheCoffeeMachine.length; i++) {
                        ingredientsInTheCoffeeMachine[i] += ingredientsForCappuccino[i];
                    }
                System.out.println("I have enough resources, making you a coffee!");
                } else {
                    for (int i = 0; i < nameOfIngredients.length; i++) {
                        if (missingIngredients[i] == 1) {
                            System.out.printf("Sorry, not enough %s%n!", nameOfIngredients[i]);
                        }
                    }
                }
                break;
            case "back":
                break;
        }
    }
    private static void check(int[] ingredientsInTheCoffeeMachine, int[] ingredientsForOrder) {
        chekIngredients = true;
        for (int i = 0; i < ingredientsForOrder.length; i++) {
            if (ingredientsInTheCoffeeMachine[i] + ingredientsForOrder[i] < 0) {
                chekIngredients = false;
                missingIngredients[i] = 1;
            }
        }
    }

    private static void fill(Scanner scanner) {
        System.out.println("Write how many ml of water do you want to add:");
        ingredientsInTheCoffeeMachine[0] += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        ingredientsInTheCoffeeMachine[1] += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        ingredientsInTheCoffeeMachine[2] += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        ingredientsInTheCoffeeMachine[3] += scanner.nextInt();
    }

    private static void take(int money) {
        System.out.printf("I gave you $%s%n", money);
        System.out.println("");
        ingredientsInTheCoffeeMachine[4] = 0;
    }
}*/
