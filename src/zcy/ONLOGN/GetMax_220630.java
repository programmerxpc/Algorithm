package zcy.ONLOGN;

/**
 * @ClassName GetMax_220630
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-6-30 20:22
 * @Version 1.0
 */
public class GetMax_220630 {
    public static void main(String[] args) {
        int[] arr = {3,0,1,2,10,27,7,25};
        System.out.println(getMax(arr, 0, arr.length-1));
    }

    private static int getMax(int[] arr, int L, int R){
        if (L == R)
            return arr[L];
        int mid = L + ((R - L) >> 1);
        int leftMax = getMax(arr, L, mid);
        int rightMax = getMax(arr, mid+1, R);
        return Math.max(leftMax, rightMax);
    }
}
