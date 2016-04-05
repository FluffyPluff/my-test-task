
public class Atm {
    public static void main(String[] args) {
        AtmControlPanel atmControlPanel = new AtmControlPanel();
        atmControlPanel.init(atmControlPanel.stash);
        atmControlPanel.getUserInput();
    }

}