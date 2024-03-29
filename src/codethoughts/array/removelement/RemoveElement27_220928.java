package codethoughts.array.removelement;

/**
 * @ClassName RemoveElement
 * @Description
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * @Author XiaoPengCheng
 * @Date 2022-9-28 15:02
 * @Version 1.0
 */
public class RemoveElement27_220928 {

    public int removeElement(int[] nums, int val) {
        int slowIndex = 0;
        int fastIndex;
        for (fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex++] = nums[fastIndex];
            }
        }
        return slowIndex;
    }

}
