package task;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game implements CheckingNumber {

    private void printMenu(List<String> strings) {
        int i = 1;
        System.out.println("Available moves:");
        for (String s : strings) {
            System.out.println(i++ + " - " + s);
        }
        System.out.println("0 - Exit");
        System.out.println("? - Help");
    }

    public int winner(int userMove, int programMove, List<String> moves) {
        System.out.println("Your move:" + moves.get(userMove));
        System.out.println("Computer move:" + moves.get(programMove));
        if (userMove == programMove) return 0;
        for (int i = 0; i < moves.size() / 2; i++) {
            if ((userMove+i+1)%moves.size()==programMove) return 1;
            //if (moves.get((userMove+i)%moves.size()).equals(moves.get(programMove))) return 1;
        }
        return -1;
    }

    public Game(List<String> strings) {
        HMAC hmac = new HMAC(strings);
        System.out.println("HMAC: "+hmac.getHmac());
        printMenu(strings);
        int userMove = Integer.parseInt(checkInt(strings))-1;
        //ASCIITable asciiTable = ASCIITable.createASCIITable(strings);
        int result = winner(userMove, hmac.getMove(), strings);
        if (result > 0) {
            System.out.println("You win!");
        } else if (result < 0) {
            System.out.println("You lose!");
        } else {
            System.out.println("Draw!");
        }
        System.out.println("HMAC key:" + hmac.getHmacKey());
    }

    @Override
    public String checkInt(List<String> strings) {
        Pattern pattern;
        pattern = Pattern.compile("[1-9][0-9]*");
        String line = "";
        Matcher matcher;
        boolean flag = false,
                condition = false;
        do {
            if (flag) printMenu(strings);
            line = in.nextLine();
            if (line.equals("0")) {
                System. exit(0);
            } else if (line.equals("?")) {

            } else {
                matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    if (Integer.parseInt(line) > strings.size())
                        condition = false;
                    else condition = true;
                }
                flag = true;
            }
        }
            while (!condition) ;

        return line;
    }
}


