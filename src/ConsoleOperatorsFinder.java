import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleOperatorsFinder {
    private static Map<Operator, String> _operators = new HashMap<Operator, String>();
    static {
        _operators.put(Operator.ADDITION,       " + ");
        _operators.put(Operator.SUBTRACTION,    " - ");
        _operators.put(Operator.MULTIPLICATION, " * ");
        _operators.put(Operator.DIVISION,       " / ");
        _operators.put(Operator.CONCATENATION,  "");
    }

    public static void startWorking() {
        boolean flag = true;
        while(flag) {
            Scanner in = new Scanner(System.in);
            System.out.print("Type digits through a space: ");
            String[] strings = in.nextLine().split(" ");
            int[] digits = new int[strings.length];
            for (int i = 0; i < strings.length; i++)
                digits[i] = Integer.parseInt(strings[i]);

            System.out.print("Input a needed sum: ");
            int sum = in.nextInt();

            Calculator calculator = new Calculator(digits);
            List<Operator[]> sequences = calculator.findSequences(sum);

            int count = 1;

            if (sequences.isEmpty())
                System.out.println("No solutions.");
            for (Operator[] operators : sequences) {
                System.out.print(count++ + ":\t");
                System.out.print(preparePresentation(digits, operators));
                System.out.print(" = " + sum);
                System.out.println();
            }
            System.out.print("Again? (any input) ");
            in.next();
            System.out.println();
        }
        System.out.println("Bye!");
    }

    private static String preparePresentation(int[] digits, Operator[] operators) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < digits.length; i++)
            str.append(digits[i] + (i != operators.length ? _operators.get(operators[i]) : ""));
        return str.toString();
    }
}
