# LeetCode 26. Remove Duplicates from Sorted Array

## 題目

[LeetCode 26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

在排序好的整數 Array 中移除重複的元素，並回傳不重複的元素個數，且空間複雜度要為 `O(1)`。

<!-- more -->

### 原題敘述

Given an integer array `nums` sorted in **non-decreasing order**, remove the duplicates [in-place](https://en.wikipedia.org/wiki/In-place_algorithm) such that each unique element appears only **once**. The **relative order** of the elements should be kept the **same**.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the **first part** of the array `nums`. More formally, if there are k elements after removing the duplicates, then the first `k` elements of `nums` should hold the final result. It does not matter what you leave beyond the first `k` elements.

Return `k` after placing the final result in the first `k` slots of `nums`.

Do **not** allocate extra space for another array. You must do this by **modifying the input array** [in-place](https://en.wikipedia.org/wiki/In-place_algorithm) with O(1) extra memory.

**Custom Judge:**

The judge will test your solution with the following code:

```
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
```

If all assertions pass, then your solution will be **accepted**.

### 例子

```
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
```

```
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
```

### 限制

- `0 <= nums.length <= 3 * 10^4`
- `-100 <= nums[i] <= 100`
- `nums` is sorted in **non-decreasing** order.

## 解題

原題的敘述蠻長的，但簡單來說就是要在不能多宣告 Array 的情況下，在原 Array 中移除多餘的元素，找出不重複的元素 k 個，並且排在原 Array 的前 k 個。

```
         *   *     *   *   *
Before  [0,0,1,1,1,2,2,3,3,4]
After   [0,1,2,3,4,_,_,_,_,_]
```

由於這是個以 **non-decreasing** 來排序的 Array，也就是說後面一個元素只可能一樣大或是比較大，言下之意就是幫你從小到大排好序。

### Solution

既然是排好序的，思路也就簡單多了，把不相同的元素往前移動就好。

設一個變數 `k`，定義是 index 為 `k` 之前的元素都不重複，初始值為 `1`，因為第 `1` 個元素之前指有 1 個元素，肯定不重複。
然後再設一個變數 `num`，定義是截至目前為止不重複的元素中最大的那個，初始值就是第 `0` 個元素 `nums[0]`。

接著就是 Traverse 整個 Array 了，從 `i = 1` 開始即可，如果發現跑到的元素和 `k` 指向的元素不同，就把 `k` 指向的元素取代掉，然後 `k` 往後走。

```
[0,0,1,1,1,2,2,3,3,4]
   i                  num = 0
   k
[0,0,1,1,1,2,2,3,3,4]
   k i                num = 0 -> num != nums[i] -> replace nums[k] by nums[i]
[0,1,1,1,1,2,2,3,3,4]
     k i              num = 1
[0,1,1,1,1,2,2,3,3,4]
     k   i            num = 1
[0,1,1,1,1,2,2,3,3,4]
     k     i          num = 1 -> num != nums[i] -> replace nums[k] by nums[i]
[0,1,2,1,1,2,2,3,3,4]
       k     i        num = 2
...
```

以此類推，到最後 `k` 就會是不重複的元素的個數，而原本的 Array 也被替換完成了。

再來檢查看看一些 Corner cases，例如沒有元素和 1 個元素的 Array：

```
[0]
 k i   Do nothing because i is out of range
---
[ ]
       Do nothing
```

上面的解法要注意 Array 沒有元素的情況，由於 `k` 初始值是 `1`，不符合 `0` 個元素不重複的情況，所以要特別處理。

我的 Python3 解答如 [solution.py](solution.py)

```py
class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return False

        num = nums[0]
        k = 1
        for i in range(1, len(nums)):
            if num != nums[i]:
                nums[k] = nums[i]
                num = nums[i]
                k += 1

        return k
```

## 總結

這一題看起來簡單，但其實細節處理有點麻煩。要用變數儲存 index、最大的不重複數字等等，有很多種方法可以做，重要的是定義要清楚，多一個少一個都會讓程式跑失敗。

這類型的題目一定要拿 Corner cases 試走一遍看看，沒有想得非常清楚的話，走過一遍很容易就會發現盲點。
