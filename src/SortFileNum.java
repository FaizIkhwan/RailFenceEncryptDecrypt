import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SortFileNum
{
    public static void main(String[] args)
    {
//        String n = "1, 2, 3, 4, 5, 6, 7, 19, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 37, 18, 20, 21, 22, 495, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 38, 39, 40, 41, 42, 49, 64, 43, 44, 45, 46, 47, 48";
//
//        String[] splitted = n.split(",");

        List<Integer> listAll = Arrays.asList(205, 207, 206, 261, 208, 209, 210);

//        for(String number: splitted)
//        {
//            listAll.add(Integer.parseInt(number.trim()));
//        }

        Collections.sort(listAll);

        List<String> listDistinct = listAll.stream().map(Object::toString).collect(Collectors.toList());

        String collectDistinct = listDistinct.stream().collect(Collectors.joining(", "));
        System.out.println(collectDistinct); //=> CO2, CH4, SO2
    }
}
