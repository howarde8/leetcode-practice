# Selection Sort

## 題目

Selection Sort，選擇排序，將一組整數組成的 Array 由小排到大（ascending order）。

### 例子

即一般的排序。

### 限制

[Selection Sort](https://en.wikipedia.org/wiki/Selection_sort) 是一種 in-place 的排序法，也就是說不用額外空間儲存，要完成排序。

## 解題

### Solution

做法是從最左邊開始選擇一個元素，和右邊的所有數字比較，將最小的和「被選擇的」數字交換，直到「被選擇」的數字被選到最右邊的。

例如一組數字 `[2, 3, 5, 4, 1]` 做排序：

先選擇要被比較的數字，從最左邊開始，固定一個數字和其右邊數字比較，找出最小的和自己交換，已經固定過的數字就是排好序的了，不用再比。

```
2 3 5 4 1
i <--   fixed index, compare 2 with 3 5 4 1, pick the smallest 1, swap 2 and 1
1|3 5 4 2
  i <-- fixed index, compare 3 with 5 4 2  , pick the smallest 2, swap 3 and 2
1 2|5 4 3
1 2 3|4 5
1 2 3 4|5
1 2 3 4 5
```

我的 Java 解答如 [solution.java](solution.java)

## 總結

非常容易實做的一個 Sorting algorithm，但效果也不佳，時間複雜度達到 `O(n^2)`。

計算時間複雜度的方法可以這樣想：固定第一個數字後比較右邊所有數字，要跑 `n` 次、第二個數字要跑 `n-1` 次。以此類推，跑到最後一個數字跑 `1` 次，這樣加總起來就是 `n + n-1 + n-2 + ... + 1`。

求等差級數之和為 `[(n + 1) * n] / 2 = O(n^2)`，得到時間複雜度。

而空間複雜度，由於 Selection sort 是 in-place algorithm，不需要額外空間，那也就是 `O(1)`。
