---
created: 2024-10-16T11:59
updated: 2024-10-16T12:01
---
## C Programming Study Guide

### 1. **Print Pyramid**

**Description:**
Write a function `void printPyramid(char *c, int n)` that prints a pyramid of characters starting from `c`.

**Example:**
```
char c = 'A';
printPyramid(&c, 5);
```

**Output:**
```
A
BB
CCC
DDDD
EEEEE
```

**Code:**
```c
#include <stdio.h>

void printPyramid(char *c, int n);

int main(void){
  char c = 'A';
  printPyramid(&c, 5);
}

void printPyramid(char *c, int n){
  for(int i=0; i<n; i++){
    for(int j=0; j<=i; j++){
      printf("%c", *c+i);
    }
    printf("\n");
  }
}
```

---

### 2. **Print Reverse**

**Description:**
Write a function `void printReverse(const char * s)` that prints the string `s` in reverse order.

**Example:**
```
char *str = "fear inoculum";
printReverse(str);
```

**Output:**
```
muluconi raef
```

**Code:**
```c
#include <stdio.h>

void printReverse(const char * s);

int main(void){
  char *str = "fear inoculum";
  printReverse(str);
}

void printReverse(const char * s){
  int l = 0;
  for(int i=0; s[i] != '\0'; i++){
      l++;
  }

  for(int i=l; i>=0; i--){
      printf("%c", s[i]);
  }
  puts("");
}
```

---

### 3. **Print Unique Elements**

**Description:**
Write a function `void printUnique(int a[], int n)` that prints the unique elements in an array `a[]` of length `n`.

**Example:**
```
int a[] = {1, 2, 2, 3, 4, 5};
printUnique(a, 6);
```

**Code:**
```c
#include <stdio.h>
#include <stdbool.h>
#define SIZE 6

void printUnique(int a[], int n);

int main(void){
    int a[SIZE] = {1 ,2 ,2 ,3 ,4 ,5};
    printUnique(a, SIZE);
}

void printUnique(int a[], int n){
  for (int i = 0; i < n; i++) {
    bool unique = true;

    for (int j = 0; j < n; j++) {
      if (a[i] == a[j] && i != j) {
        unique = false;
      }
    }

    if (unique) {
      printf("%d ", a[i]);
    }
  }
}
```

---

### 4. **Share Digit**

**Description:**
Write a function `int shareDigit(int num1, int num2)` that returns 1 if `num1` and `num2` share any digit, 0 otherwise.

**Example:**
```
shareDigit(24, 46); -> 1
shareDigit(24, 56); -> 0
```

**Code:**
```c
#include <stdio.h>

int shareDigit(int num1, int num2);

int main(void){
  int num1, num2;

  puts("Enter two numbers:");
  scanf("%d %d", &num1, &num2);

  int s = shareDigit(num1, num2);
  
  if(s == 1)
    printf("%d and %d share a digit\n", num1, num2);
  else
    printf("%d and %d do not share any digit\n", num1, num2);
}

int shareDigit(int num1, int num2){
  int lastDigit1, lastDigit2;

  if(num1 == num2)
    return 1;

  while(num1 > 0){
    lastDigit1 = num1 % 10;

    while(num2 > 0){
      lastDigit2 = num2 % 10;

      if(lastDigit1 == lastDigit2)
        return 1;

      num2 /= 10;
    }

    num1 /= 10;
  }

  return 0;
}
```

---

### 5. **Find Maximum Subarray Sum**

**Description:**
Write a function `findMaxSubarraySum` that returns the maximum subarray sum and prints the starting and ending indices of the subarray.

**Example:**
```
int arr[] = {1, -2, 3, -1, 2};
findMaxSubarraySum(arr, 5);
```

**Code:**
```c
#include <stdio.h>

// Function to find the maximum subarray sum and its indices
int findMaxSubarraySum(int arr[], int n, int *startIdx, int *endIdx) {
    if (n <= 0) {
        return 0;
    }

    int maxSum = arr[0];
    int currentSum = arr[0];
    int currentStartIdx = 0;

    *startIdx = 0;
    *endIdx = 0;

    for (int i = 1; i < n; i++) {
        if (currentSum < 0) {
            currentSum = arr[i];
            currentStartIdx = i;
        } else {
            currentSum += arr[i];
        }

        if (currentSum > maxSum) {
            maxSum = currentSum;
            *startIdx = currentStartIdx;
            *endIdx = i;
        }
    }

    return maxSum;
}

int main() {
    int arr[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    int n = sizeof(arr) / sizeof(arr[0]);
    int startIdx, endIdx;

    int maxSum = findMaxSubarraySum(arr, n, &startIdx, &endIdx);

    printf("Maximum subarray sum: %d\n", maxSum);
    printf("Starting index: %d\n", startIdx);
    printf("Ending index: %d\n", endIdx);

    return 0;
}
```

---

### 6. **Swap Two Integers**

**Description:**
Write a function `swapIntegers` to swap two integers using pointers.

**Example:**
```
Before: a = 5, b = 10
After: a = 10, b = 5
```

**Code:**
```c
#include <stdio.h>

// Function to swap two integers using pointers
void swapIntegers(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int main() {
    int num1, num2;

    printf("Enter the first integer: ");
    scanf("%d", &num1);

    printf("Enter the second integer: ");
    scanf("%d", &num2);

    printf("Before swapping:\n");
    printf("num1 = %d\n", num1);
    printf("num2 = %d\n", num2);

    // Call the swapIntegers function to swap the values
    swapIntegers(&num1, &num2);

    printf("After swapping:\n");
    printf("num1 = %d\n", num1);
    printf("num2 = %d\n", num2);

    return 0;
}
```

---

### 7. **Transpose A Matrix**

**Description:**
Write a function `void transpose(int a[][])` that prints the transpose of a 2D matrix.

**Example:**
```
int a[3][3] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
transpose(a);
```

**Code:**
```c
#include <stdio.h>
#define NROWS 3
#define NCOLS 3

void transpose(int a[NROWS][NCOLS]);

int main(void){
    int a[NROWS][NCOLS] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    transpose(a);
}

void transpose(int a[NROWS][NCOLS]){
  int t[NROWS][NCOLS];

  for(int i=0; i<NROWS; i++)
    for(int j=0; j<NCOLS; j++)
      t[j][i] = a[i][j];

  for(int i=0; i<NROWS; i++){
    for(int j=0; j<NCOLS; j++)
      printf("%d ", t[i][j]);
    printf("\n");
  }
}
```

---

### 8. **Find Second Smallest Element**

**Description:**
Write a function `findSecondSmallest` that returns the second smallest element in an array.

**Example:**
```
int arr[] = {12, 45, 1, 7, 23, 9, 45, 6};
findSecondSmallest(arr, 8);
```

**Output:**
```
The second smallest element is: 6
```

**Code:**
```c
#include <stdio.h>
#include <limits.h>

// Function to find the second smallest element in an array
int findSecondSmallest(int arr[], int n) {
    if (n < 2) {
        return -1; // There is no second smallest element
    }

    int smallest = INT_MAX;
    int secondSmallest = INT_MAX;

    for (int i = 0; i < n; i++) {
        if (arr[i] < smallest) {
            secondSmallest = smallest;
            smallest = arr[i];
        } else if (arr[i] < secondSmallest && arr[i] != smallest) {
            secondSmallest

 = arr[i];
        }
    }

    if (secondSmallest == INT_MAX) {
        return -1; // No distinct second smallest element
    }

    return secondSmallest;
}

int main() {
    int arr[] = {12, 45, 1, 7, 23, 9, 45, 6};
    int n = sizeof(arr) / sizeof(arr[0]);

    int result = findSecondSmallest(arr, n);

    if (result != -1) {
        printf("The second smallest element is: %d\n", result);
    } else {
        printf("There is no distinct second smallest element in the array.\n");
    }

    return 0;
}
```

---

This study guide provides a comprehensive look at common C problems and their solutions, helping you practice concepts like loops, arrays, pointers, and more.