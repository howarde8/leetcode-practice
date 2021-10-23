# LeetCode 35. Search Insert Position

## 題目

[LeetCode 35. Search Insert Position](https://leetcode.com/problems/search-insert-position/)

給定排序過的整數 Array 及 Target 數字，找出 Target 所在的 Index 或該插入的 Index（若 Target 不存在）。

且演算法需以 `O(log n)` 的時間複雜度完成。

<!-- more -->

### 原題敘述

Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with `O(log n)` runtime complexity.

### 例子

```
Input: nums = [1,3,5,6], target = 5
Output: 2
```

```
Input: nums = [1,3,5,6], target = 2
Output: 1
```

```
Input: nums = [1,3,5,6], target = 7
Output: 4
```

```
Input: nums = [1,3,5,6], target = 0
Output: 0
```

```
Input: nums = [1], target = 0
Output: 0
```

### 限制

- `1 <= nums.length <= 10^4`
- `-10^4 <= nums[i] <= 10^4`
- `nums` contains **distinct** values sorted in **ascending** order.
- `-10^4 <= target <= 10^4`

## 解題

### Solution 1 - 迴圈

既然要求在時間複雜度為 `O(log n)` 的情況下完成，最直接想到的就是 Binary Search。也就是在排序的情況下， 在中間切一刀去縮小範圍，直到找到目標為止。

這時就需要先定義一個範圍了，可以先設整個 Array（`nums`）的頭尾 `left`（簡稱 `l`） 及 `right`（簡稱 `r`） Index 為初始範圍，目標會一直在 `l` 及 `r` 之間。

```
1 3 5 6 <-- nums
l     r
```

然後從兩個變數中間切一刀，取一個 `middle`（簡稱 `mid`）Index，比較這個 Index 上的數值和 Target，可以分成以下幾種情況

- nums[mid] > target
- nums[mid] < target
- nums[mid] == target

相等的情況最簡單，因為就找到了，所以只要回傳 mid 即可。剩下兩種情況，根據 Binary Search 的理念，就要縮小範圍

- nums[mid] > target --> 將 `l` 移至 `mid` 處
- nums[mid] < target --> 將 `l` 移至 `mid` 處

例如 Target 是 2，初始的 `mid` 是 `Floor(l + r) / 2 = 1`

```
1 3 5 6
l
   <--r
  m
```

由於 `nums[mid] (3) < target (2)` 則 `r` 就要往中間移動，移到 `mid` 的位置。

接下來以此類推，而終止條件 termination condition 為 `l` 及 `r` 相鄰或重疊的狀態（因此時兩者的中間點就是自己，繼續下去還是會一樣）。

在 `l` 及 `r` 相鄰或重疊時，可能會有以下幾種狀況

```
  l   r
^ ^ ^ ^ ^
1 2 3 4 5
```

這五種狀況及分別如何處理（插入在什麼 Index）

1. `target < nums[l]` --> Return `l`
2. `target == nums[l]` --> Return `l`
3. `target > nums[l] && target < nums[r]` --> Return `r`
4. `target == nums[r]` --> Return `r`
5. `target > nums[l]` --> Return `r + 1`

簡化後可以得到

- if `target <= nums[l]` --> Return `l`
- else if `target <= nums[r]` --> Return `r`
- else --> Return `r + 1`

我的 Python 解答如 [solution1.py](solution1.py)

### Solution 1-2 - 迴圈改進版

Solution 1 有太多條件判斷，以下為簡化版的做法。

關鍵的思想在於 `l` 和 `r` 根據 `mid` 所改變的位置，原本是

- `nums[mid] < target` 時將範圍往右縮小：`l = mid`
- `nums[mid] > target` 時將範圍往左縮小：`r = mid`

但是 `nums[mid]` 已經被比過了，因此範圍可以再多往右一點或多往左一點，變成

- `nums[mid] < target` 時將範圍往右縮小：`l = mid + 1`
- `nums[mid] > target` 時將範圍往左縮小：`r = mid - 1`

但此時就不再是相鄰或重疊而已了，可能會有 `l > r` 的情況存在。

考慮到 Target 可能出現的位置，我們可以做以下 Case by case 的分析，用這個例子來看

```
1 2 6 7 9
```

假如 Target 剛好存在，在縮減範圍時就會找到了。但 Target 不存在的話就有以下可能

- `Target < Smallest Number, e.g., target == 0`
- `Target > Biggest Number, e.g., target == 10`
- `Target is in the range, e.g., target = 3`

如果都用 `l = mid + 1` 和 `r = mid - 1` 的方式縮減範圍，可以得到上面三個 case 在跳出迴圈後（`l > r`），都需要回傳 `l` 的值。

我的 Python 解答如 [solution1-2.py](solution1-2.py)

## 總結

- 注意 Index 和 Value 的不同，寫程式時常忽略此點，變成拿 Index 和 Value 去比較，例如 `target <= l` 應該改成 `target <= nums[l]`
- 還要考慮 Overflow 的問題，假如 `l + r` 很大的話在一些語言可能會溢出，可以改成 `l + (r - l) / 2`
