#include <iostream>
#include "params2.h"

using namespace std;

string ConstBorderColor()
{
    srand(time(NULL));
    return ("\033[1;27;" + to_string(rand()%KNbCandies+31) + ";40m");
}

