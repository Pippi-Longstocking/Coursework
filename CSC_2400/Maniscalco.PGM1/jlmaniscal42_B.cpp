/*	
	Name:	Jacob Maniscalco
	Title:	jlmaniscal42_B.cpp
	Date:	09/21/2018
	Purpose:Finds the greatest common denominator of two integer values using Consecutive integer checking algorithm
*/

#include <iostream>
#include "stdlib.h"
using namespace std;

void conIC(int, int);

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

	conIC(num1, num2);

	return 0;
}

void conIC(int num1, int num2)
{
	int oNum1 = num1;
	int oNum2 = num2;
	int t;

	//if either input number is 0, the gcd is undefined
	if (num1 == 0 || num2 == 0)
	{
		cout << "gcd (" << oNum1 << ", " << oNum2;
		cout << ") is undefined.\n";
		return;
	}
	
	//finds the minimum value of the two integers
	if (num1 < num2)
		t = num1;
	else 
		t = num2;
	
	while (t != 1)
	{
		if (num1 % t == 0)
		{
			if (num2 % t == 0)
			{
				cout << "gcd (" << oNum1 << ", " << oNum2;
				cout << ") is " << t << endl;
				return;
			}
		}

		--t;
	}

	//if no gcd can be found, the gcd is undefined
	cout << "gcd (" << oNum1 << ", " << oNum2;
	cout << ") is undefined.\n";
		return;
}