package codethoughts.array.binary;

/**
 * @ClassName Main701
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-26 19:18
 * @Version 1.0
 */
public class Main701_220926 {

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        System.out.println(search(nums, 4));
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (target < nums[mid]) { // <
                right = mid - 1;
            } else if (target > nums[mid]) { // >
                left = mid + 1;
            } else { // ==
                return mid;
            }
        }
        return -1;
    }

}
