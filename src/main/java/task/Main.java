package task;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        if (args.length < 3 || args.length%2==0){
            System.out.println("The number of parameters must be odd and equal to or greater than 3.");
        } else {
            Set<String> items = new TreeSet<>();
            List<String> array = new ArrayList<>();
            for (String s: args){
                array.add(s);
                if(!items.add(s)) {
                    System.out.println("All parameters must be different!");
                    return;
                }
            }
            Game game = new Game(array);
        }
    }
}
