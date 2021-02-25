import java.util.*;
public class Calculator {
    public List<Integer> digits = new ArrayList<>();

    public Calculator(int... digits) {
        for(int digit : digits)
            this.digits.add(digit);
    }

    public List<Operator[]> findSequences(int sum) {
        List<Operator[]> result = new ArrayList<>();
        List<Operator[]> sequences = getAllSequences();
        for (Operator[] sequence : sequences) {
            if(doesMatch(sequence, sum))
                result.add(sequence);
        }
        return result;
    }

    private List<Operator[]> getAllSequences() {
        List<Operator[]> sequences = new ArrayList<>();
        Operator[] operators = new Operator[digits.size() - 1];
        for(int i = 0; i < operators.length; i++)
            operators[i] = Operator.CONCATENATION;
        getAllAllocations(sequences, 0, operators);
        return sequences;
    }

    private static void getAllAllocations(List<Operator[]> sequences, int index, Operator[] operators) {
        for (Operator operator : Operator.values()) {
            operators[index] = operator;
            if(index < operators.length - 1)
                getAllAllocations(sequences, index + 1, operators);
            else
                sequences.add(Arrays.copyOf(operators, operators.length));
        }
    }

    private boolean doesMatch(Operator[] operators, int sum) {
        int i = 0;

        List<Operator> ops = new ArrayList<>();
        for (Operator op : operators)
            ops.add(op);
        List<Double> list = new ArrayList<>();
        for (int digit : this.digits)
            list.add((double) digit);
        getStep(list, ops, new Operator[] { Operator.CONCATENATION });
        getStep(list, ops, new Operator[] { Operator.MULTIPLICATION, Operator.DIVISION });
        getStep(list, ops, new Operator[] { Operator.ADDITION, Operator.SUBTRACTION });

        return list.get(0) == sum;
    }

    private void getStep(List<Double> result, List<Operator> operators, Operator[] available) {
        Operator current;
        double res;
        int size = operators.size();
        for (int i = 0; isAvailable(operators, available); i++)
            if (Arrays.asList(available).contains(current = operators.get(i))) {
                res = current.action(result.get(i), result.get(i + 1));
                result.remove(i);
                result.remove(i);
                result.add(i, res);
                operators.remove(current);
                i--;
            }
    }

    private boolean isAvailable(List<Operator> operators, Operator[] available){
        boolean res = false;
        for(Operator operator : operators)
            res |= Arrays.asList(available).contains(operator);
        return res;
    }
}
