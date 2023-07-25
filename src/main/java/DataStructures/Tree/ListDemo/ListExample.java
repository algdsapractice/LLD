package DataStructures.Tree.ListDemo;

import java.util.ArrayList;
import java.util.List;

/// 0 -> ""
// 1-> ""

public class ListExample {


    public static void main(String[] args) {

        // countries
                // India
                // US
                // Japan

        // declaration
        List<String> countries = new ArrayList<>();

        // intialise
        countries.add("India"); //0
        countries.add("US");//1
        countries.add("Japan");//2

       // System.out.println(countries);

        //System.out.println(countries.get(2));

//        for (int i=1; i< countries.size();i++){
//            System.out.println(countries.get(i));
//        }

        for(String country:countries){
            System.out.println(country);
        }




    }
}
