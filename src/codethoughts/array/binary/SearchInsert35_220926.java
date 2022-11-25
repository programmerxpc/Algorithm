package codethoughts.array.binary;

/**
 * @ClassName SearchInsert_220926
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-26 20:29
 * @Version 1.0
 */
public class SearchInsert35_220926 {
    public static void main(String[] args) {

    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return right + 1;
    }
}
