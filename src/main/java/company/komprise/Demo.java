package company.komprise;

public class Demo {
    // array  4 1 3 9 2 10

    // 2 +1 + 4
   // 5+4+2
    public static int getTimeInSecs(int [] arr ,int pos)
    {
            int length=arr.length;
            int sum=0;
            for(int i =0; i<arr.length; i++){
                int internalSum=0;
                int target= arr[pos];
                int current = arr[i];
                if(target>current){
                    internalSum= target-current;
                }

                sum+=internalSum;

            }
            int timeInSecs = pos+1+arr[pos]*length-1-sum;

            return timeInSecs;
    }

    public static void main(String[] args) {
        int[] arr = {4,1,3,9,2,10};
         int secs = getTimeInSecs(arr, 2);
        System.out.println(secs);
    }





}
