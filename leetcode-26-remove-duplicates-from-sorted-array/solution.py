from typing import List


class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 0

        num = nums[0]
        k = 1
        for i in range(1, len(nums)):
            if num != nums[i]:
                nums[k] = nums[i]
                num = nums[i]
                k += 1

        return k
