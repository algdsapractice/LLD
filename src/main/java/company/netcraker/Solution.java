package company.netcraker;

//Find most frequently occurring word(s) along with its frequency in given statement
//a.	Example input: "Mithilesh is employee of ABC company, MITHILESH is from blore, MITHILESH! is good in java.“
//b.	Expected output: Words – Mithilesh, is & frequency - 3

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static class CustomeKey {
        String name;
        String label;

        CustomeKey(String name) {
            this.name = name;
            this.label= name.toLowerCase(Locale.ROOT);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CustomeKey that = (CustomeKey) o;

            return label != null ? label.equals(that.label) : that.label == null;
        }

        @Override
        public int hashCode() {
            return label != null ? label.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "CustomeKey{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {

        String data = "Mithilesh is employee of ABC company, MITHILESH is from blore, MITHILESH! is good in java.";
        Map<CustomeKey, Integer> map = findFrequency(data);

        map.forEach((k,v)-> System.out.println(k.label + ": " + v));

//        map.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue())
////                .forEach(System.out::println);
//
//        Map<CustomeKey, Integer> sortedMap = MapUtils.sortByValue(map);
//        sortedMap.forEach((k,v)-> System.out.println(k.label + ":"+v));


        Map<CustomeKey, Integer> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(2)
                .collect(Collectors.toMap(m-> m.getKey(), m-> m.getValue()));
        System.out.println("After Sorting");
        sortedMap.forEach((k,v)-> System.out.println(k.label + ":"+v));

        List<String> list = map.entrySet().stream().sorted((o1,o2)-> o2.getValue().compareTo(o1.getValue())).limit(2).map(o1-> o1.getKey().label).collect(Collectors.toList());
        System.out.println("List Data");
        list.forEach(o-> System.out.println(o));
    }

    private static Map<CustomeKey, Integer>  findFrequency(String data) {
        Map<CustomeKey, Integer> map = new HashMap<CustomeKey,Integer>();


        //System.out.println(data);
        String [] words = data.split(" ");


        for (String word : words) {
            word= word.replaceAll("[^A-Za-z0-9]","");
            CustomeKey customeKey = new CustomeKey(word);
            int count =map.getOrDefault(customeKey, 0);
            map.put(customeKey,++count);

        }

        return map;
    }



}
