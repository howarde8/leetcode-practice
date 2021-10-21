class Solution {
  public static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i != arr.length - 1) System.out.print(',');
      else System.out.println();
    }
  }

  public void selectionSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int minIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[minIndex]) minIndex = j;
      }

      // Swap i and min value
      int temp = arr[i];
      arr[i] = arr[minIndex];
      arr[minIndex] = temp;
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();

    int[] arr = { 1, 3, -4, 5, 9, 14, -2, 0, 5, 12, 7, 3 };
    printArray(arr);
    
    solution.selectionSort(arr);
    printArray(arr);
  }
}