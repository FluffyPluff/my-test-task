
public interface IAtm {

    /*  returns current stash */
        int state();

    /*  returns stash in "nominal=count" format */
        void dump();

    /*  gets user's input */
        void getUserInput();

    /*  returns amount in "nominal=count" format */
        void get(int amount);

    /*  puts count-money in d-nominal  */
        void put(int d, int count);

    /*  exit */
        void exit();

}
