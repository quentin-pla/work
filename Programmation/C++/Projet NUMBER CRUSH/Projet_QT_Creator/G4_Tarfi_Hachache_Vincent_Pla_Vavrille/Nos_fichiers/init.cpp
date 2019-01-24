#include <iostream>
#include "init.h"

using namespace std;

void ResetData(int & Score, int & GameChoice, int & LevelChoice, int & Hits)
{
    Score = 0;
    Hits = 0;
    GameChoice = 0;
    LevelChoice = 0;
}

void InitGrid2 (CMat & Grid, const unsigned & Size)
{
    Grid.resize(Size, CVLine(Size));
    srand(time(NULL));
    for (unsigned i(0); i < Grid.size(); ++i)
        for (unsigned j(0); j < Grid[i].size(); ++j)
            Grid[i][j] = rand()%KNbCandies+1;
}

void InitLeaderBoard (ifstream & ifs, CMatStr & LeaderBoard, int & GameChoice)
{
    LeaderBoard.resize(27, CVLineStr(3));
    if(GameChoice == 1) ifs.open(LeaderBoardCLASSIC);
    else if(GameChoice == 2) ifs.open(LeaderBoardDIAGONAL);
    if(ifs.is_open())
    {
        string word;
        char carac;
        unsigned level(0);
        unsigned line(0);
        while(!ifs.eof())
        {
            ifs >> carac;
            if(carac == '#')
            {
                ifs >> carac;
                for(unsigned i(level); i < level+3; ++i)
                {
                    LeaderBoard[i][0] = carac;
                }
                level+=3;
            }
            else if(carac == '=')
            {
                LeaderBoard[line][1] = word;
                word = "";
            }
            else if(carac == ';')
            {
                LeaderBoard[line][2] = word;
                word = "";
                ++line;
            }
            else
            {
                word+=carac;
            }
            if(line > 26)break;
        }

    }
    ifs.close();
}

void CheckGrid (CMat & Grid)
{
    for (unsigned i(0); i < Grid.size(); ++i)
        for (unsigned j(0); j < Grid[i].size(); ++j)
        {
            if(j > 0)
                while(Grid[i][j] == Grid[i][j-1])
                    Grid[i][j] = rand()%KNbCandies+1;
            if(i > 0)
                while(Grid[i][j] == Grid[i-1][j])
                    Grid[i][j] = rand()%KNbCandies+1;
        }
}

void Couleur (const string & coul)
{
    cout << "\033[1;37;" << coul <<"m";
}
