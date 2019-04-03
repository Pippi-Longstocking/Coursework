#include <iostream>
using namespace std;

struct Student
{
public:
	string name;
	double wage;
};

Student* createStudent(string, double);


int main()
{	
	Student* jacob = createStudent("Jacob", 14.0);

	cout << jacob->name << " " << jacob->wage << endl;
}



Student* createStudent(string name, double wage)
{
	Student* newStudent = new Student;

	newStudent->name = name;
	newStudent->wage = wage;

	return newStudent;
}