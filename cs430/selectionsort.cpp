/*
 * Name: Khoi Nguyen (w1682559@apps.losrios.edu)
 * Date: September 2, 2019
 * Original author: Dan Ross (rossd@flc.losrios.edu )
 *
 * Selection sort algorithm to sort an unsortedi one-dimensional
 * array in ascending order.
 *
 * Changes: Added a function call to print_array to print
 * the array after every iteration of the outer loop.
 *
 */
#include <stdio.h>
#define SIZE 9

int A[SIZE] = {24,13,26,1,2,27,38,15,10};

void print_array(int *, int);
void ssort(void);
void swap(int i, int j);

int main()
{
	printf("Original: \n");
	print_array(A, SIZE);

	ssort();

	printf("\nSorted: \n");
	print_array(A, SIZE);
}

void ssort()
{
   for (int i = SIZE-1; i >= 0; i--) {
      // find biggest unsorted element
      int max = 0;
      for (int j = 0; j <= i; j++) {
         if(A[j] > A[max]) 
            max = j;
      }

      // put biggest at end
      swap(i, max);

      // Modification to code
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

