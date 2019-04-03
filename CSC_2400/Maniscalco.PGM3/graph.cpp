#include "GraphMatrix.h"
#include "Stack.h"
#include <iostream>
#include <fstream>

using namespace std;

int main()
{


	string fileName;
	ifstream inputFile;
	int numVertices;
	int vert1;
	int vert2;
	do 
	{
		cout << "Enter the name of your file that contains the graph vertices: ";
		getline(cin, fileName);
		inputFile.open(fileName.c_str());

	} while(inputFile.fail());

	inputFile >> numVertices;

	GraphMatrix matrix(numVertices);

	while(inputFile >> vert1)
	{
		inputFile >> vert2;
		matrix.addEdge(vert1, vert2);
	}


	matrix.printGraph();

	


	return 0;
}