# LeetCode 9. Palindrome Number

## 題目

[LeetCode 9. Palindrome Number](https://leetcode.com/problems/palindrome-number/)

判斷給定的整數是否為「回文」（所謂「回文」就是正著讀和倒著讀都是一樣的）。

### 原題敘述

Given an integer `x`, return `true` if `x` is palindrome integer.

An integer is a **palindrome** when it reads the same backward as forward. For example, `121` is palindrome while `123` is not.

### 例子

```
Input: x = 121
Output: true
```

```
Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
```

```
Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
```

```
Input: x = -101
Output: false
```

### 限制

- `-2^31 <= x <= 2^31 - 1`

## 解題

### Solution 1 - 雙指針

判斷是否為回文最簡單的方式就是拿字串去判斷。用兩個指針放在頭尾比較，若此字串為回文的話，兩個指針所指的值都會是一樣的。

```
1 2 3 2 1
l -> <- r
|       |
|       right pointer
left pointer
```

如此一來只要將指針往內移動，然後判斷，到他們相遇時兩者比較都相同的話，就可以說此字串是回文，反之亦然。

另外要注意的小地方是給定整數，所以會包含負數，而負數反過來就和原數字不相等了，因此我覺得可以先判斷是否為負數（當然如果轉成字串，前面也會多個負號，再判斷也可以得到非回文的結果）。

我用 JavaScript 的解答如 [solution1.js](solution1.js)

### Solution 2 - 不轉成 String 的解法

LeetCode 9 這題本身就有個 follow up `Could you solve it without converting the integer to a string?`。看來大家最直覺得做法就是轉成 String 再說。

如果不轉成 String 的話，那就得回過頭來看整數的處理了。我的想法是把給定的整數反過來，然後再和原數字比較就可以了，因為回文反過來還會是同樣的數值。

這樣看的話，重點就會是如何把數字「反過來」。

我的想法是用取餘數和除法配合，這樣就能把原數字從最後一個數字開始取出，丟到新變數中

```
1  2  3  4  5
         |  |
         |  - Step 1. 12345 % 10 = 5
         |            12345 / 10 = 1234.5 -- Floor -> 1234
         ---- Step 2. 1234 % 10 = 4
                      1234 / 10 = 123.4 -- Floor -> 123
```

取得最後一個數字如上所示，用 `%` Operator 取餘數，丟到新變數中

```
New variable = 0   // Initial
               5   // Step 1. 0 * 10 + 5 = 5
               54  // Step 2. 5 * 10 + 4 = 54
               ...
               54321
```

從原數字取餘數得到的數字丟入新變數中，丟之前先乘以 10 即可。等到原數字被除成 0 則表示所有位數都已經被取得，再來只要比較原數字和新數字是否相等就好。

我用 JavaScript 的解法如 [solution2.js](solution2.js)

## 總結

時間複雜度沒什麼可以說的，就是 `O(n)` 而已。不用字串的解法比較不直觀，會需要想一下。
