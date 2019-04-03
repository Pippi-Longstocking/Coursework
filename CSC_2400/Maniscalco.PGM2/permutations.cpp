#include <iostream>
#include <string>
#include <iomanip>

using namespace std;


void display(bool[], string);
void johnsonTrotter(bool[], string);
int getMobile(bool[], string);
void lexicographic(bool[], string);



bool RIGHT_ARROW = true;
bool LEFT_ARROW = false;

int main()
{

	string userInput;

	cout << "Enter in a string: ";
	getline(cin, userInput);

	int stringSize = userInput.size();

	bool mobility[stringSize];

	//Initialize boolean array to false (arrows point to the left)
	for (int i = 0; i < userInput.size(); i++)
	{
		mobility[i] = LEFT_ARROW;
	}


	johnsonTrotter(mobility, userInput);


	return 0;
}



void display(bool arrows[], string userInput)
{
	for (int i = 0; i < userInput.size(); i++)
	{
		if (arrows[i] == LEFT_ARROW)
			cout << "<- ";
		else
			cout << "->";
	}

	cout << endl;


	cout << userInput.at(0);

	for (int i = 1; i < userInput.size(); i++)
	{
		cout << setw(3) << right << userInput.at(i);
	}

	cout << endl;
}


void johnsonTrotter(bool arrows[], string userInput)
{
	char cArray[] = userInput.c_str();

	for (int i = 0; i < cArray.size(); i++)
	{
		cout << cArray[i];
	}
}
void lexicographic(bool[], string);