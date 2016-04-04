import java.util.*;

public class AtmInterface {

    private static Map<Integer, Integer> stash = new LinkedHashMap<>();

    /*
    *
    *       Этот код -- один сплошной костыль.
    *       Мне за него стыдно, честно
    *       Буду рад услышать любые пожелания и поправки.
    *
    *       Теперь об алгоритме :
    *
    *       Итак, если у нас имеется банкомат, в котором лежат деньги,
    *       то эти деньги нужно где-то хранить (в какой-либо структуре данных)
    *       Я выбрал LinkedHashMap т.к.:
    *           -он сохраняет порядок вставки элементов
    *           -add() и contains() за О(1)
    *       Деньги хранятся в LinkedHashMap'e в таком формате :
    *       ключ -- номинал купюры
    *       значение -- кол-во купюр данного номинала
    *
    *       Какой вообще алгоритм?
    *       банкомат должен реализовать следующие методы:
    *           put()
    *           dump()
    *           state()
    *           get()
    *           quit()
    *
    *       Для метода put(int d, int count) все ясно : мы просто кладем
     *      в банкомат count купюр номинала d
     *
     *      Для метода dump() тоже : мы пробегаемся по всем элементам LinkedHashMap'a,
     *      выводя кол-во купюр каждого номинала
     *
     *      метод state() находит сумму всех элементов, также проходясь по LinkedHashMap'у
     *
     *      Как работает метод get(amount) :
     *
     *      Для начала мы инициализиуем наш LinkedHashMap (далее stash),
     *      наполняя его ключами (номиналами) (5000, 1000, 500, 100, 50, 25, 10, 5, 3, 1)
     *      изначально банкомат пуст => value каждого элемента stash'a изначально равен 0
     *
     *      Теперь представим :
     *      мы кладем в банкомат две купюры по 50р, 1 купюру по 25р, 1 купюру по 10р
     *      Итого у нас в банкомате 135р
     *      теперь мы хотим обналичить эти 135р, для этого вызываем метод get(135);
     *
     *      Нащ stash имеет следующий вид :
     *      [ (5000, 0), (1000, 0), (500, 0), (100, 0), (50, 2), (25, 1), (10, 1), (5, 0), (3, 0), (1, 0) ]
     *      Чтобы получить 135 рублей, нам нужно найти купюры, которыми можно их выдать.
     *      Для этого я не придумал ничего другого, кроме как пробегаться по ключам (номиналам) stash'a до тех пор,
     *      пока мы не встретим ключ, который будет будет меньше, чем amount.
     *      В данном случае этим ключом будет 50. Когда мы встретили 50, мы уменьшаем amount на 50,
     *      (т.е. сумма, которую мы должны выдать уже уменьшена на 50), уменьшаем value элемента с
     *      ключом 50 на 1 и заново выполняем метод get от нового amount -- 135-50 = 85.
     *
     *      Так мы делаем до тех пор, пока amount не будет равен 0.
     *
     *      Но что делать, если, например, нащ stash будет иметь следущий вид :
     *      [ (5000, 0), (1000, 0), (500, 0), (100, 3), (50, 0), (25, 0), (10, 0), (5, 0), (3, 100), (1, 0) ]
    *
    *       Т.е. две банкноты по 100 и 100 банкнот по 3
    *       И мы хотим вызвать get(300)
    *
    *       Код будет пробегаться по элементам stash'a, пока не встретит ключ, меньший, чем amount
    *       Этим ключом будет 100. Тогда код выдаст две купюры по 100, а остаток будет пытаться выдать купюрами
    *       по 3, но сделать этого не сможет.
    *
    *       Так вот, чтобы избежать этого, есть метод checkForNominal(), который проверяет :
    *       можно ли выдать весь amount купюрами одного номинала.
    *       С этим методом программа сразу выдаст ответ "3=100", т.е.
    *       отдаст нам 100 купюр по 3 у.е.
    *
    *
     */


    public AtmInterface() {
        initATM();
        getInput();
        }

    private static void initATM() {
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


    /*
    *
    *  реализуем метод put() :
    *  если уже имеется элемент с ключом, который мы кладем в LinkedHashMap,
    *  то этот элемент заменит старый элемент с тем же ключом, а метод put
    *  должен увеличивать число банкнот номинала d на count единиц,
    *
    *  поэтому мы заменяем элемент с ключом d на элемент с тем же ключом, но с
    *  count = кол-во предыдущих банкнот + кол-во банкнот, которые мы кладем в банкомат методом put
    *
    *
     */

    private void put(int d, int count) {
        stash.put(d, stash.get(d) + count);
    }


    /*
    *
    * Здесь мы реализуем метод get(), с помощью двух методов --
    * getForAllStash() -- если мы не можем выдать amount купюрами одного номинала
    * checkForNominal() -- проверяем, можем ли мы выдать amount купюрами одного номинала
    *
    * Так же, если кол-во купюр в банкомате меньше, чем мы просим выдать,
    * То мы выполняем метод get(state()), т.е. get от количества всех купюр банкомата.
    *
     */


    private void get(int amount) {


        if (amount > state()) {
            System.out.println("without " + (amount - state()));
            amount = state();
        }


        int key = checkForNominal(amount);

        if (key != 0) {
            getInput();
        } else {
            if (hasElement(amount)) {
                getForAllStash(amount);
            } else
                System.out.println("impossible");
            getInput();
        }
    }

    private boolean hasElement(int amount) {
        boolean element = false;
        for (Map.Entry stashEntry : stash.entrySet()) {
            if ((amount >= (int) stashEntry.getKey()) && ((int) stashEntry.getValue() != 0)) {
                element = true;
            }
        }
        return element;
    }

    /*
    *
    *  А этот код реализует тот алгоритм, который я описал в самом начале:
    *   Пробегаемся по элементам "cнизу-вверх", если встречаем, что ключ элемента
     * (номинал) меньше amount, то останавливаемся на этом элементе, берем одну купюру
     * (уменьшаем value на 1), уменьшаем amount на размер номинала и заново пробегаемся
     * по всем элементам,
     * все это повторяем до тех пор, пока amount не уменьшится до 0
    *
     */


    private void getForAllStash(int amount) {

        while (amount != 0) {
            for (Map.Entry stashEntry : stash.entrySet()) {
                if ((amount >= (int) stashEntry.getKey()) && ((int) stashEntry.getValue() != 0)) {
                    amount -= (int) stashEntry.getKey();
                    stash.put((int) stashEntry.getKey(), (int) stashEntry.getValue() - 1);
                    System.out.println(stashEntry.getKey() + "=1");
                    get(amount);
                }
            }
        }
    }


    /*
    *
    *  Этот метод проверяет, нельзя вернуть всю сумму (amount) купюрами одного номинала
    *  Пробегаясь по LinkedHashMap'у, он смотрит ключ объекта.
    *
    *  Если весь amount можно выдать купюрами одного номинала, то amount
    *  должен делиться без остатка на номинал, т.е. на ключ объекта.
    *
    *  И если мы, пробегаясь по LinkedHashMap'у находим такой элемент,
    *  купюрами которого можно выдать всю сумму, то возвращаем его ключ.
    *
     */

    private int checkForNominal(int amount) {

        int myKey = 0;

        for (Map.Entry stashEntry : stash.entrySet()){
            if (((amount % (int) stashEntry.getKey()) == 0 ) && ((int) stashEntry.getValue() != 0)) {
                if (((amount / (int) stashEntry.getKey()) <= (int) stashEntry.getValue())) {
                    myKey = (int) stashEntry.getKey();
                    stash.put(myKey, stash.get(myKey) - amount/myKey);
                    System.out.println(myKey + "=" + amount/myKey);
                    amount = 0;
                }
            }
        }
        return myKey;
    }

     /*
     пробегаемся по всем элементам, и если Value (кол-во купюр) не равно 0
     , то выводим его на экран
      */

    private void dump() {


        for (Map.Entry stashEntry : stash.entrySet()) {
            if ((int) stashEntry.getValue() != 0)
                System.out.println(stashEntry.getValue() + " bill to  " + stashEntry.getKey() + "  rubles");
        }
    }


    /*
    не нашел ничего лучше, чем пробежаться по всем элементам linkedhashmap'a
     */

    private int state() {

        int sum = 0;

        for (Map.Entry stashEntry : stash.entrySet()) {
            sum += (int) stashEntry.getKey() * (int) stashEntry.getValue();
        }

        return sum;
    }

    private void quit() {
        System.out.print("EXIT");
        System.exit(-1);
    }


     private String getInput() {


        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();


        /*
        *
        *  За эту часть кода мне стыдно еще больше, чем за все остальные.
        *  Работает он так:
        *  создаем сканнер и читаем с него input, дальше смотрим этот input
        *  , проверяя методом contains его содержимое.
        *  Если input.contains("put") или input.contains("get")
        *  То в input'e по-любому есть цифры, которые мы даем как аргумент
        *  Эти цифры содержатся в input'e, начиная с 4 позиции, в итоге
        *  мы парсим input, забирая из него все числа и отправляя их в
        *  нашу структуру данных
        *
         */


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
                    System.out.println("Current stash = " + state());
                    break;
                case "quit" :
                    quit();
            }

         while (!input.equals("quit")) getInput();

        return input;
    }

}
