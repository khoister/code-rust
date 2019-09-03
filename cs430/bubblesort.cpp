/*
 * Name: Khoi Nguyen (w1682559@apps.losrios.edu)
 * Date: September 2, 2019
 * Original author: Dan Ross (rossd@flc.losrios.edu )
 *
 * Bubble sort algorithm to sort an unsortedi one-dimensional
 * array in ascending order.
 *
 * Changes: Added a function call to print_array to print
 * the array after the iteration of the outer loop.
 *
 */

#include <stdio.h>
#define SIZE 9

int A[SIZE] = {24,13,26,1,2,27,38,15,10};

void print_array(int *, int);
void swap(int, int);
void bsort(void);

int main()
{
	printf("Original: \n");
	print_array(A, SIZE);

	bsort();

	printf("\nSorted: \n");
	print_array(A, SIZE);
}

void bsort()
{
    for(int i = 0; i < SIZE; i++) {
        for(int j = SIZE - 1; j > i; j--) {
            if(A[j] < A[j-1]) {
                swap(j, j-1);
            }
        }
        // Modification to original code: print array
        print_array(A, SIZE);
    }
}

void swap(int i, int j)
{
   int temp;

   temp = A[i];
   A[i] = A[j];
   A[j] = temp;
}

void print_array(int * X, int size)
{
	for(int i=0; i<size; i++) 
		printf("%d \t", X[i]);
	printf("\n\n");
}

