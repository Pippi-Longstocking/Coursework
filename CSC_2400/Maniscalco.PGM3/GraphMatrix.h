#ifndef GRAPHMATRIX_H
#define GRAPHMATRIX_H

#include <iostream>
using namespace std;

 class GraphMatrix
{

private:
	int** vertexMatrix;
	int numVertices;
	int numEdges;

public: 

	GraphMatrix(int vertices)
	{
		numVertices = vertices;
		numEdges = 0;

		int** verticesMatrix = new int*[numVertices];
		
		//allocating space for all columns
		for (int i = 0; i < numVertices; i++)
		{
			verticesMatrix[i] = new int[numVertices];
		}

		//Initiating all indexes to 0
		for (int i = 0; i < numVertices; i++)
		{
			for (int j = 0; j < numVertices; j++)
				verticesMatrix[i][j] = 0;		
		}

		vertexMatrix = verticesMatrix;
	}


	~GraphMatrix()
	{
		for (int i = 0; i < numVertices; i++)
			delete [] vertexMatrix[i];
		
		delete vertexMatrix;
	}


	void addEdge(int vert1, int vert2)
	{
		vertexMatrix[vert1][vert2] = 1;
		numEdges++;
	}


	void printGraph()
	{
		cout << "Adjacency Matrix:\n";
		for (int i = 0; i < numVertices; i++)
		{
			for (int j = 0; j < numVertices; j++)
				cout << vertexMatrix[i][j] << "\t";
		
			cout << endl;
		}
		
	}


	bool isThereAnEdge(int row, int column)
	{
		if (vertexMatrix[row][column])
			return true;
		else 
			return false;
	}


	int getFirstVertex()
	{
		int vertex = 0;

		for (int i = 0; i < numVertices; i++)
		{
			for (int j = 0; j < numVertices; j++)
			{
				if (vertexMatrix[j][i] == 1)
					break;
				if (j == (numVertices - 1) && vertexMatrix[j][i] == 0)
					return i;
			}
		}
		return vertex;
	}
};

#endif