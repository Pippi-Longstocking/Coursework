/*	
	Name:	Jacob Maniscalco
	Title:	jlmaniscal42_A.cpp
	Date:	09/21/2018
	Purpose:Finds the greatest common denominator of two integer values using Euclid's Algorithm
*/

#include <iostream>
#include "stdlib.h"
using namespace std;

void gcd(int, int);

int main(int argc, char *argv[])
{
	//Incorrect number of arguements results in error
	if (argc != 3)
	{
		cout << "Usage:\t commandLine.exe m n\n";
		cout << "(where m & n are non-zero, non-negative integers)\n";
		return 1;
	}

	int num1 = atoi(argv[1]);
	int num2 = atoi(argv[2]);

	gcd(num1, num2);

	return 0;
}

 void gcd(int num1, int num2)
{
	int remainder = 0;
	int oNum1 = num1;
	int oNum2 = num2;

	//If either integer is 0, the gcd is undefined
	if (num1 == 0 || num2 == 0)
	{
		cout << "gcd (" << num1 << ", " << num2 << ") is ";
		cout << "undefined.\n";
		return;
	}

	//solves for greatest common denominator
	while (num2 != 0)
	{
		remainder = num1 % num2;
		num1 = num2;
		num2 = remainder; 
	}
	
	//returns undefined if no gcd is found	
	if (num2 == 1)
	{
		cout << "gcd (" << oNum1 << ", " << oNum2 << ") is ";
		cout << "undefined.\n";
	}

	//returns gcd if found 
	else
	{
		cout << "gcd (" << oNum1 << ", " << oNum2 << ") is ";
		cout << num1 << endl;
	}
}