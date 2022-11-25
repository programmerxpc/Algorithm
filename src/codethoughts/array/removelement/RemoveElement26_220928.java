package codethoughts.array.removelement;

/**
 * @ClassName RemoveElement26_220928
 * @Description
 * @Author XiaoPengCheng
 * @Date 2022-9-28 15:27
 * @Version 1.0
 */
public class RemoveElement26_220928 {

    public int removeDuplicates(int[] nums) {
        int slowIndex = 0;
        int fastIndex;
        nums[slowIndex++] = nums[0];
        for (fastIndex = 1; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != nums[fastIndex - 1]) {
                nums[slowIndex++] = nums[fastIndex];
            }
        }
        return slowIndex;
    }

}
