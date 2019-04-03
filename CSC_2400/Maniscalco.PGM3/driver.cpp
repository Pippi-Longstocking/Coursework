#include "GraphMatrix.h"
#include "Stack.h"
#include <iostream>
using namespace std;


int main()
{

	GraphMatrix matrix = GraphMatrix(7);

	matrix.printGraph();


/*
	cout << "\n\n" << endl;

	matrix.addEdge(0,1);
	matrix.addEdge(0,2);
	matrix.addEdge(0,3);
	matrix.addEdge(0,0);
	matrix.addEdge(0,4);
	//matrix.addEdge(4,1);
	//matrix.addEdge(0,1);
	//matrix.addEdge(0,1);

	matrix.printGraph();


	cout << "TESTING ISTHEREANEDGE()...\n\n";

	cout << "TESTING 0,0 : \n";
	cout << matrix.isThereAnEdge(0,0);

	cout << "\nTESTING 0, 1: \n";
	cout << matrix.isThereAnEdge(0,1);


	cout << "\n\n";
	matrix.printGraph();
	cout << "TESTING FIRST VERTEX FUNCTION...\n";
	cout << matrix.getFirstVertex() << endl;

*/



	return 0;
}