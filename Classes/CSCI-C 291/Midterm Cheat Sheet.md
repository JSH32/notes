---
created: 2024-10-16T11:44
updated: 2024-10-16T11:46
---
## String Manipulation (stdlib functions)

1. **`strlen(char *str)`**:
   - Returns the length of the string (excluding the null terminator).
   - Example:
     ```c
     char str[] = "Hello";
     int len = strlen(str);  // len will be 5
     ```

2. **`strcpy(char *dest, const char *src)`**:
   - Copies the string from `src` to `dest` (including the null terminator).
   - Example:
     ```c
     char src[] = "World";
     char dest[10];
     strcpy(dest, src);  // dest will now be "World"
     ```

3. **`strncpy(char *dest, const char *src, size_t n)`**:
   - Copies at most `n` characters from `src` to `dest`. Pads with null characters if `src` is shorter than `n`.
   - Example:
     ```c
     char src[] = "Hello";
     char dest[10];
     strncpy(dest, src, 3);  // dest will now be "Hel"
     ```

4. **`strcmp(const char *str1, const char *str2)`**:
   - Compares two strings. Returns 0 if equal, less than 0 if `str1` is lexicographically less than `str2`, and greater than 0 otherwise.
   - Example:
     ```c
     strcmp("abc", "abc");  // Returns 0 (equal)
     ```

5. **`strcat(char *dest, const char *src)`**:
   - Concatenates `src` to the end of `dest` (null terminator included).
   - Example:
     ```c
     char dest[20] = "Hello, ";
     strcat(dest, "World!");  // dest will now be "Hello, World!"
     ```

---

## Boolean Logic (`stdbool.h`)

1. **`bool`**:
   - A boolean type that can hold `true` or `false`.
   - Example:
     ```c
     #include <stdbool.h>
     
     bool is_valid = true;
     ```

2. **`true`** and **`false`**:
   - Represent boolean true and false values.
   - Example:
     ```c
     bool flag = false;
     ```

---

## Input/Output (I/O Operations)

1. **`printf(const char *format, ...)`**:
   - Outputs formatted text to the standard output (typically the console).
   - Example:
     ```c
     int x = 10;
     printf("Value of x: %d\n", x);  // Outputs: Value of x: 10
     ```

2. **`scanf(const char *format, ...)`**:
   - Reads formatted input from the standard input (typically the console).
   - Example:
     ```c
     int num;
     scanf("%d", &num);  // Reads an integer input
     ```

3. **`fgets(char *str, int n, FILE *stream)`**:
   - Reads a line of text from `stream` into `str` (up to `n-1` characters).
   - Example:
     ```c
     char buffer[50];
     fgets(buffer, 50, stdin);  // Reads up to 49 characters from the console
     ```

4. **`fputs(const char *str, FILE *stream)`**:
   - Writes a string to the given file stream.
   - Example:
     ```c
     fputs("Hello, File!", stdout);  // Outputs to the console
     ```

---

## Array Definitions

1. **Single-dimensional array**:
   - Definition:
     ```c
     int arr[5];  // Declares an array of 5 integers
     ```

2. **Multidimensional array**:
   - Definition:
     ```c
     int matrix[3][4];  // Declares a 3x4 array of integers
     ```

3. **Accessing elements**:
   - Example:
     ```c
     int arr[5] = {1, 2, 3, 4, 5};
     int x = arr[2];  // x is now 3 (third element)
     ```

4. **Iterating through arrays**:
   - Example:
     ```c
     for (int i = 0; i < 5; i++) {
         printf("%d ", arr[i]);
     }
     ```

---

## Extracting Individual Digits of a Number

1. **Extract digits using modulus and division**:
   - You can extract digits of a number using `%` (modulus) and `/` (division).
   - Example:
     ```c
     int num = 12345;
     while (num > 0) {
         int digit = num % 10;  // Get the last digit
         printf("%d\n", digit);  // Output the digit
         num /= 10;  // Remove the last digit
     }
     ```

---

## C Study Guide: `printf` and Print Format Specifiers

---

In C, `printf` is a powerful function for formatted output. It allows you to format and print different types of variables (integers, floating-point numbers, characters, etc.) by using format specifiers.

### Common Format Specifiers

1. **`%d` or `%i`** – Integer (signed decimal)
   - Example:
     ```c
     int num = 42;
     printf("Number: %d\n", num);  // Output: Number: 42
     ```

2. **`%u`** – Unsigned Integer (unsigned decimal)
   - Example:
     ```c
     unsigned int num = 42;
     printf("Unsigned number: %u\n", num);  // Output: Unsigned number: 42
     ```

3. **`%f`** – Floating-point number
   - Example:
     ```c
     float num = 3.14;
     printf("Float: %f\n", num);  // Output: Float: 3.140000
     ```

4. **`%.nf`** – Floating-point with `n` decimal precision
   - Example:
     ```c
     float num = 3.14159;
     printf("Float with 2 decimals: %.2f\n", num);  // Output: Float with 2 decimals: 3.14
     ```

5. **`%c`** – Single character
   - Example:
     ```c
     char c = 'A';
     printf("Character: %c\n", c);  // Output: Character: A
     ```

6. **`%s`** – String (array of characters)
   - Example:
     ```c
     char str[] = "Hello, World!";
     printf("String: %s\n", str);  // Output: String: Hello, World!
     ```

7. **`%p`** – Pointer (memory address)
   - Example:
     ```c
     int num = 42;
     printf("Pointer: %p\n", (void*)&num);  // Output: Pointer: 0x... (memory address)
     ```

8. **`%x` or `%X`** – Hexadecimal representation
   - Example:
     ```c
     int num = 255;
     printf("Hexadecimal: %x\n", num);  // Output: Hexadecimal: ff
     ```

9. **`%o`** – Octal representation
   - Example:
     ```c
     int num = 16;
     printf("Octal: %o\n", num);  // Output: Octal: 20
     ```

10. **`%e` or `%E`** – Scientific notation
    - Example:
      ```c
      double num = 1234.5678;
      printf("Scientific notation: %e\n", num);  // Output: Scientific notation: 1.234568e+03
      ```

---

### Length Modifiers (to Handle Different sizes)

1. **`%ld` or `%li`** – Long integer
   - Example:
     ```c
     long int num = 100000L;
     printf("Long integer: %ld\n", num);  // Output: Long integer: 100000
     ```

2. **`%lu`** – Unsigned long integer
   - Example:
     ```c
     unsigned long num = 100000UL;
     printf("Unsigned long: %lu\n", num);  // Output: Unsigned long: 100000
     ```

3. **`%lld` or `%lli`** – Long long integer
   - Example:
     ```c
     long long int num = 9223372036854775807LL;
     printf("Long long integer: %lld\n", num);  // Output: Long long integer: 9223372036854775807
     ```

4. **`%llu`** – Unsigned long long integer
   - Example:
     ```c
     unsigned long long num = 18446744073709551615ULL;
     printf("Unsigned long long: %llu\n", num);  // Output: Unsigned long long: 18446744073709551615
     ```

5. **`%lf`** – Double precision floating-point number
   - Example:
     ```c
     double num = 3.141592653589793;
     printf("Double: %lf\n", num);  // Output: Double: 3.141593
     ```

6. **`%Lf`** – Long double
   - Example:
     ```c
     long double num = 3.141592653589793238462643;
     printf("Long double: %Lf\n", num);  // Output: Long double: 3.141593
     ```

---

### Width and Precision Modifiers

1. **Width**: You can specify a minimum field width, which is the number of characters used to display the value.
   - Example:
     ```c
     int num = 42;
     printf("Number with padding: %5d\n", num);  // Output: Number with padding:    42
     ```

2. **Precision for floating-point numbers**: You can control the number of digits after the decimal point.
   - Example:
     ```c
     float num = 3.14159;
     printf("Float with 3 decimals: %.3f\n", num);  // Output: Float with 3 decimals: 3.142
     ```

3. **Zero-padding**: You can pad with zeros instead of spaces by using `0`.
   - Example:
     ```c
     int num = 42;
     printf("Zero-padded: %05d\n", num);  // Output: Zero-padded: 00042
     ```

4. **Left alignment**: Use a `-` to left-align the output.
   - Example:
     ```c
     int num = 42;
     printf("Left-aligned: %-5d!\n", num);  // Output: Left-aligned: 42   !
     ```

---

### Escape Characters

1. **`\n`** – New line
2. **`\t`** – Tab
3. **`\\`** – Backslash
4. **`%%`** – To print a literal percent symbol
