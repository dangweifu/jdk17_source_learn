import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> r = list.stream().sorted(Comparator.comparing(Integer::intValue).reversed()).collect(Collectors.toList());
        System.out.println(r);
    }
}