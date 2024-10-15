package company.coursera;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryString {

    public static  String getLargestString ( String s ){

        return getLargestStringHelper( s);
    }

    private static String getLargestStringHelper(String s) {

        int count =0;
        int start=0;

        List<String>  substrings = new ArrayList<>();


        for(int i =0; i< s.length(); i++){

            if(s.charAt(i)=='1'){
                count++;
            }else{
                count --;
            }

            if(count==0){
                String inner = getLargestStringHelper(s.substring(start+1,i));

                substrings.add("1"+inner+"0");
                start=i+1;
            }
        }

        Collections.sort(substrings,Collections.reverseOrder());
        StringBuilder result = new StringBuilder();
        for(String substring : substrings){
                result.append(substring);
        }

        return  result.toString();
    }


}
