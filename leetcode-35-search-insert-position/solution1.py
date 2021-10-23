from typing import List


class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        l = 0
        r = len(nums) - 1

        while l < r - 1:
            mid = int((l + r) / 2)
            if nums[mid] < target:
                l = mid
            elif nums[mid] > target:
                r = mid
            else:
                return mid

        if target <= nums[l]:
            return l
        elif target <= nums[r]:
            return r
        else:
            return r + 1
