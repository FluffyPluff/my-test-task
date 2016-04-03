

public class Atm {


    public static void main(String[] args) {
        AtmInterface ai = new AtmInterface();
        ai.initATM();
        while (!ai.getInput().equals("quit")) {
            ai.getInput();
        }
    }

}
