---
created: 2024-10-16T11:59
updated: 2024-10-16T12:09
---
## Remove Element from Array
```c
void removeElement(int arr[], int *n, int index) { 
	// Check if index is within the bounds of the array
	if (index < 0 || index >= *n) {
		printf("Index out of bounds!\n");
		return;
	} 
	
	// Shift elements to the left to fill the gap 
	for (int i = index; i < *n - 1; i++) { 
		arr[i] = arr[i + 1]; 
	} 
	
	// Decrease the size of the array
	(*n)--;
}
```