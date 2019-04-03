#ifndef GRAPHLIST_H
#define GRAPHLIST_H

class GraphList
{
private:
	struct ListNode
	{
		int matrixValue;
		ListNode* next;
	}

	ListNode** headArray;
	int numVertices;
	int numEdges;


public:
	GraphList(int vertices)
	{
		numVertices = vertices;
		numEdges = 0;

		ListNode** array = new ListNode*[vertices];

		//allocating space for all columns
		for (int i = 0; i < numVertices; i++)
		{
			array[i] = new ListNode;
		}


		headArray = array;
		
	}

	~GraphList()
	{
		for (int i = 0; i < numVertices; i++)
		{
			delete headArray[i];
		}
	}


	void addEdge(int vert1, int ver2)
	{
		
	}
}




#endif