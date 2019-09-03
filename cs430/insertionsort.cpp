/*
 * Name: Khoi Nguyen (w1682559@apps.losrios.edu)
 * Date: September 2, 2019
 * Original author: Dan Ross (rossd@flc.losrios.edu )
 *
 * Insertion sort algorithm to sort an unsortedi one-dimensional
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
void isort(void);

int main()
{
    printf("Original: \n");
    print_array(A, SIZE);

    isort();

    printf("\nSorted: \n");
    print_array(A, SIZE);
}

void isort()
{
    for(int i = 0; i < SIZE; i++) {
        // find insertion spot
        int j = i, temp = A[i];
        while(j > 0 && A[j - 1] > temp) {
            A[j] = A[j - 1];
            j--;
        }
        A[j] = temp;

        // Modification to code: print array
        print_array(A, SIZE);
    }
}

void print_array(int * X, int size)
{
    for(int i=0; i<size; i++) 
        printf("%d \t", X[i]);
    printf("\n\n");
}

