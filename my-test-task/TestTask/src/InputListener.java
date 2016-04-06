
import java.util.Scanner;

public class InputListener {

    private Scanner scanner;
    private String input;
    private AtmControlPanel atmControlPanel;

    public InputListener() {
    }


    /*
    *
    *   Тут ваще ад. Обработка пользовательского ввода
    *   Работает так :
    *   если пользователь вводит строку, в которой содержится put или
    *   get , значит, в этой строке есть цифры, цифры начинаются с 4 позиции.
    *   Я не придумал ничего лучше, кроме как удалить из строки все, что идет до 4 позиции,
    *   спарсить цифры, которые остаются в строке и закинуть их в stash
    *
    * */

    public String getInput() {
        scanner = new Scanner(System.in);
        input = scanner.nextLine();
        atmControlPanel = new AtmControlPanel();

        if (input.contains("put")) {

            input = input.substring(4, input.length());
            int d = Integer.parseInt(input.substring(0, input.indexOf(" ")));
            int count = Integer.parseInt(input.substring(input.indexOf(" ") + 1, input.length()));

            atmControlPanel.put(d, count);

        }

        if (input.contains("get")) {

            input = input.substring(4, input.length());
            int amount = Integer.parseInt(input);

            atmControlPanel.get(amount);

        }

        if (input.equals("dump")) {

            atmControlPanel.dump();

        }

        if (input.equals("state")) {

            System.out.println("Current stash=" + atmControlPanel.state());

        }

        if (input.equals("quit")) {

            atmControlPanel.exit();

        }

        while (!input.equals("quit")) {
            getInput();
        }

        return input;

    }

}