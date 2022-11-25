package codethoughts.array.removelement;

/**
 * @ClassName MoveZero283_220928
 * @Description
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * @Author XiaoPengCheng
 * @Date 2022-9-28 16:06
 * @Version 1.0
 */
public class MoveZero283_220928 {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        int slowIndex = 0;
        int fastIndex;
        for (fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != 0) {
                nums[slowIndex++] = nums[fastIndex];
            }
        }
        // 补0
        for (int i = slowIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

}
