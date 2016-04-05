import java.util.Map;

public class AtmGetter {

    AtmControlPanel atmControlPanel;
    InputListener inputListener;

    /* summa нужна для того, чтобы хранить сумму выведенных на экран элементов */
    private int summa = 0;

    public AtmGetter() {
        atmControlPanel = new AtmControlPanel();
        inputListener = new InputListener();
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


    public void get(int amount) {

        if (amount > atmControlPanel.state()) {
            System.out.println("without " + (amount - atmControlPanel.state()));
            amount = atmControlPanel.state();
        }

        int key = checkForNominal(amount);
        if (key != 0) {
            summa = 0;
            inputListener.getInput();
        }
        else {
            if (hasElement(amount)) {
                getForAllStash(amount);
            }
            else {
                System.out.println("without " + amount + ", всего " + summa);
                System.out.println("impossible");
                summa = 0;
                inputListener.getInput();
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

        for (Map.Entry<Integer, Integer> stashEntry : atmControlPanel.stash.entrySet()){

            if (((amount %  stashEntry.getKey()) == 0 ) && (stashEntry.getValue() != 0)) {

                if ((amount /  stashEntry.getKey()) <= stashEntry.getValue()) {
                    myKey = stashEntry.getKey();
                    atmControlPanel.stash.put(myKey, atmControlPanel.stash.get(myKey) - amount/myKey);
                    System.out.println(myKey + "=" + amount/myKey + ", " + "всего " + (summa + amount));
                    amount = 0;
                }
            }
        }
        return myKey;
    }


            /*
    *
    * Этот метод проверяет : а можем ли мы выдать amount?
    * Его пришлось ввести из-за того, что наш алгоритм выдает сообщение о том,
    * "что выдать сумму не выйдет", только в том случае, когда amount > state
    * Т.е., например, в банкомате лежит одна купюра 1000 у.е., нам необходимо снять
    * 500 у.е.
    * и, несмотря на то, что amount > state, выдать купюру не выйдет, потому что нам нечем.
    *
    * именно для этого и нужен метод hasElement(), который возвращает Key элемента,
    * который мы должны выдать, если таковой существует.
    *
    *
    *
     */

    private boolean hasElement(int amount) {
        boolean element = false;
        for (Map.Entry<Integer, Integer> stashEntry : atmControlPanel.stash.entrySet()) {
            if ((amount >= stashEntry.getKey()) && (stashEntry.getValue() != 0)) {
                element = true;
            }
        }
        return element;
    }




       /*
    *
    *  А этот код реализует тот алгоритм, который я описал в самом начале:
    *   Пробегаемся по элементам "сверху-вниз", если встречаем, что ключ элемента
     * (номинал) меньше amount, то останавливаемся на этом элементе, берем кол-во купюр
     *  заданного номинала, которые мы можем выдать, "выдаем" их, уменьшаем кол-во
     *  купюр в банкомате на число выданных.
     * все это повторяем до тех пор, пока amount не уменьшится до 0
    *
     */



    private void getForAllStash(int amount) {


        while (amount != 0) {
            for (Map.Entry<Integer, Integer> stashEntry : atmControlPanel.stash.entrySet()) {

                if ((amount >=  stashEntry.getKey()) && ( stashEntry.getValue() != 0)) {

                    /* billsCount -- переменная, отвечающая за кол-во купюр, которые мы можем выдать  */
                    int billsCount = (int) Math.floor(amount / (int) stashEntry.getKey());

                    amount -=  stashEntry.getKey() * billsCount;
                    summa +=  stashEntry.getKey() * billsCount;
                    atmControlPanel.stash.put( stashEntry.getKey(),  stashEntry.getValue() - billsCount);
                    System.out.print(stashEntry.getKey() + "=" + billsCount + ", ");

                    get(amount);
                }

            }

        }
    }


}
