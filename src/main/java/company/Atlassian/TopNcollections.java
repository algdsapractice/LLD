package company.Atlassian;

//cf 1-> 90
//cf2 -> 60
//cf 3-> 30
// collection List<File>

// 2 3 6
// Map<namofCollection , List<file>>
// List<List<File>>

// list1 -> 67
//list -> 65

public class TopNcollections {

//    public static Map<String,List<CollectionFile>> dict;
//
//     public Report getCollection(List<CollectionFile> file)
//     {
//         dictPopulate();
//     }
//
//     public Map<String,Integer> getSizePerCollection(){
//            Map<String,Integer> result = new HashMap<>();
//
//         for (Map.Entry<String, List<CollectionFile>> me : m.entrySet()) {
//             String key = me.getKey();
//             List<CollectionFile> collectionFileList = me.getValue();
//             System.out.println("Key: " + key);
//             System.out.print("Values: ");
//             result.put(key,getTotalSize(collectionFileList));
//         }
//         return result;
//     }
//
//     public Integer getTotalSize(List<CollectionFile> file)
//     {
//         int result=0;
//         for (CollectionFile collectionFile:file) {
//
//             int size=collectionFile.getFileSize();
//             result += size;
//         }
//            return result;
//     }
//
//    private void dictPopulate() {
//        CollectionFile collectionFile = new CollectionFile("file1", 56);
//        CollectionFile collectionFile2= new CollectionFile("file2", 67);
//        CollectionFile collectionFile3= new CollectionFile("file3", 87);
//        CollectionFile collectionFile4= new CollectionFile("file4", 90);
//
//        List<CollectionFile> list1= new ArrayList<>();
//        list1.add(collectionFile);
//        list1.add(collectionFile2);
//        List<CollectionFile> list2= new ArrayList<>();
//        list2.add(collectionFile3);
//        list2.add(collectionFile4);
//
//        dict.put("key1",list1);
//        dict.put("key2",list2);
//    }
////
////    PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(
////             public int compare(Integer a, Integer b) {
////        return b - a;
////        });
////
////
////    public static void main(String[] args) {
////
////    }
}
