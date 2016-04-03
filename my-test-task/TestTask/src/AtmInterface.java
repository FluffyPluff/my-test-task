import java.util.*;

public class AtmInterface {

    private static Map<Integer, Integer> stash = new LinkedHashMap<>();
    private Set entrySet;
    private Iterator iterator;


    public static void initATM() {
        stash.put(5000, 0);
        stash.put(1000, 0);
        stash.put(500, 0);
        stash.put(100, 0);
        stash.put(50, 0);
        stash.put(25, 0);
        stash.put(10, 0);
        stash.put(5, 0);
        stash.put(3, 0);
        stash.put(1, 0);
    }

    public void put(int d, int count) {
        if (stash.containsKey(d)) {
            int newValue =  stash.get(d) + count;
            stash.put(d, newValue);
        } else
            stash.put(d, count);
    }



    public void get(int amount) {

            if (amount > state()) {
                System.out.println("without " + (amount - state()));
                amount = state();
            }

            int key = checkForFullStash(amount);
            if (key != 0) {
                System.out.println(key + "=" + amount / key);
                while (!getInput().equals("quit")) getInput();
            } else
                getForFullStash(amount);
        }



    private void getForFullStash(int amount) {

        Map.Entry stashEntry;
        entrySet = stash.entrySet();
        iterator = entrySet.iterator();

        while (amount != 0) {
            while (iterator.hasNext()) {
                stashEntry = (Map.Entry) iterator.next();
                if ((amount >= (int) stashEntry.getKey()) && ((int) stashEntry.getValue() != 0)) {
                    amount -= (int) stashEntry.getKey();
                    stash.put((int) stashEntry.getKey(), (int) stashEntry.getValue() - 1);
                    System.out.println(stashEntry.getKey() + " ruble bill");
                    get(amount);
                }
            }
        }
    }
    private int checkForFullStash(int amount) {
        entrySet = stash.entrySet();
        iterator = entrySet.iterator();
        Map.Entry stashEntry;
        int myKey = 0;

        while (iterator.hasNext()) {
            stashEntry = (Map.Entry) iterator.next();
            if (((amount % (int) stashEntry.getKey()) == 0 ) && ((int) stashEntry.getValue() != 0)) {
                if (((amount / (int) stashEntry.getKey()) <= (int) stashEntry.getValue())) {
                    myKey = (int) stashEntry.getKey();
                    stash.put(myKey, stash.get(myKey) - amount/myKey);
                    amount = 0;
                }
            }
        }
        return myKey;
    }

    public void dump() {
        entrySet = stash.entrySet();
        iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            Map.Entry stashEntry = (Map.Entry) iterator.next();
            if ((int) stashEntry.getValue() != 0)
                System.out.println(stashEntry.getValue() + " bill to  " + stashEntry.getKey() + "  rubles");
        }
    }


    public int state() {

        int sum = 0;
        entrySet = stash.entrySet();
        iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            Map.Entry stashEntry = (Map.Entry) iterator.next();
            sum += (int) stashEntry.getKey() * (int) stashEntry.getValue();
        }

        return sum;
    }

    public void quit() {
        System.exit(-1);
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();


        // ахахаха тут вообще лютый пиздец
        if (input.contains("put")) {
            input = input.substring(4, input.length());
            int d = Integer.parseInt(input.substring(0, input.indexOf(" ")));
            int count = Integer.parseInt(input.substring(input.indexOf(" ") + 1, input.length()));
            put(d, count);
        } else
        if (input.contains("get")) {
            input = input.substring(4, input.length());
            int amount = Integer.parseInt(input);
            get(amount);

        } else
            switch (input) {
                case "dump" :
                    if (state() == 0) System.out.println("empty");
                    else
                        dump();
                    break;
                case "state" :
                    System.out.print("Current stash = " + state());;
                    break;
                case "quit" :
                    quit();
            }
        return input;
    }

}
