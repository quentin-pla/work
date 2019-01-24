#include <iostream>
#include "ingame.h"
#include "canonic.h"
#include "display.h"

using namespace std;

void SetPos (CPosition & Pos, const CMat & Grid, const CMatStr & LeaderBoard, int & ScoreForNextLvl, int & Score, int & LevelChoice, int & MaximalHits, int & Hits)
{
    cout << endl;
    char c;
    unsigned HorizontalValue;
    unsigned VerticalValue;
    cout << "        Horizontal: " << endl;
    while(true)
    {
        set_input_mode ();
        read (STDIN_FILENO, &c, 1);
        if(isdigit(c)) HorizontalValue = ((unsigned)c-48);
        if(HorizontalValue != 0 && HorizontalValue < Grid.size()+1)break;
    }
    DisplayWindow(Grid,LeaderBoard,ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
    cout << endl << "        Horizontal: " << HorizontalValue << endl;
    Pos.second = HorizontalValue;
    --Pos.second;

    cout << "        Vertical: " << endl;
    while(true)
    {
        set_input_mode ();
        read (STDIN_FILENO, &c, 1);
        if(isdigit(c)) VerticalValue = ((unsigned)c-48);
        if(VerticalValue != 0 && VerticalValue < Grid.size()+1)break;
    }
    DisplayWindow(Grid,LeaderBoard,ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
    cout << endl << "        Horizontal: " << HorizontalValue << endl << "        Vertical: " << VerticalValue << endl;
    Pos.first = VerticalValue;
    --Pos.first;
}

void SetDirection (CMat & Grid, CPosition & Pos, char & Direction, bool & NextNumber)
{
    if(NextNumber) cout << "        Direction (z,q,s,d): " << endl;
    char c;
    set_input_mode ();
    read (STDIN_FILENO, &c, 1);
    Direction = tolower(c);
    switch(Direction)
    {
    case 'z':
        if(Pos.first > 0 && Grid[Pos.first-1][Pos.second] != 8)
        {
            swap(Grid[Pos.first-1][Pos.second],Grid[Pos.first][Pos.second]);
            NextNumber = true;
        }
        else NextNumber = false;
        break;
    case 's':
        if(Pos.first < Grid.size()-1 && Grid[Pos.first+1][Pos.second] != 8)
        {
            swap(Grid[Pos.first+1][Pos.second],Grid[Pos.first][Pos.second]);
            NextNumber = true;
        }
        else NextNumber = false;
        break;
    case 'q':
        if(Pos.second > 0 && Grid[Pos.first][Pos.second-1] != 8)
        {
            swap(Grid[Pos.first][Pos.second-1],Grid[Pos.first][Pos.second]);
            NextNumber = true;
        }
        else NextNumber = false;
        break;
    case 'd':
        if(Pos.second < Grid.size()-1 && Grid[Pos.first][Pos.second+1] != 8)
        {
            swap(Grid[Pos.first][Pos.second+1],Grid[Pos.first][Pos.second]);
            NextNumber = true;
        }
        else NextNumber = false;
        break;
    default:
        NextNumber = false;
        break;
    }
}

void MakeAMove (CMat & Grid, CPosition & Pos, char & Direction, bool & NextNumber, const CMatStr & LeaderBoard, int & ScoreForNextLvl, int & Score, int & LevelChoice, int & MaximalHits, int & Hits)
{
    do
        SetPos(Pos, Grid, LeaderBoard, ScoreForNextLvl, Score,LevelChoice,MaximalHits,Hits);
    while(Grid[Pos.first][Pos.second] == 8);
    NextNumber = true;
    do
    {
        SetDirection(Grid, Pos, Direction, NextNumber);
    }
    while(!NextNumber);
    ++Hits;
}

void RemovalInDiagonalNordEst (CMat & Grid, const CPosition & Pos, unsigned & HowMany)
{
    int X = Pos.second;
    int Y = Pos.first;
    for (unsigned i(0); i < HowMany; ++i)
    {
        Grid[Y][X] = KImpossible;
        --X;
        ++Y;
    }
}

bool AtLeastThreeInADiagonalNordEst (const CMat & Grid, CPosition & Pos, unsigned & HowMany)
{
    for (unsigned ColNmber = 0; ColNmber < Grid.size (); ++ColNmber)
    {
        for (unsigned LineNumber = 0; LineNumber < Grid.size (); ++LineNumber)
        {
            Pos.first = LineNumber;
            Pos.second = ColNmber;
            if (KImpossible == Grid[LineNumber][ColNmber]) continue;

            for (HowMany = 1;
                 LineNumber + HowMany < Grid.size () &&
                 ColNmber - HowMany < Grid [LineNumber + HowMany].size () &&
                 Grid[LineNumber][ColNmber] == Grid[LineNumber + HowMany][ColNmber - HowMany]; ++HowMany);
            if (HowMany > 2) return true;
        }
    }
    return false;
}

void RemovalInDiagonalNordOuest (CMat & Grid, const CPosition & Pos, unsigned & HowMany)
{
    int X = Pos.second;
    int Y = Pos.first;
    for (unsigned i(0); i < HowMany; ++i)
    {
        Grid[Y][X] = KImpossible;
        ++X;
        ++Y;
    }
}

bool AtLeastThreeInADiagonalNordOuest (const CMat & Grid, CPosition & Pos, unsigned & HowMany)
{
    for (unsigned ColNmber = 0; ColNmber < Grid.size (); ++ColNmber)
    {
        for (unsigned LineNumber = 0; LineNumber < Grid.size (); ++LineNumber)
        {
            Pos.first = LineNumber;
            Pos.second = ColNmber;
            if (KImpossible == Grid[LineNumber][ColNmber]) continue;

            for (HowMany = 1;
                 LineNumber + HowMany < Grid.size () &&
                 ColNmber  + HowMany < Grid [LineNumber + HowMany].size () &&
                 Grid[LineNumber][ColNmber] == Grid[LineNumber + HowMany][ColNmber + HowMany]; ++HowMany);

            if (HowMany > 2) return true;

        }
    }
    return false;
}

void RemovalInRow (CMat & Grid, const CPosition & Pos, unsigned & HowMany)
{
    int X = Pos.second;
    int Y = Pos.first;
    for (unsigned i(0); i < HowMany; ++i)
    {
        Grid[Y][X] = KImpossible;
        ++X;
    }
}

bool AtLeastThreeInARow (CMat & Grid, CPosition & Pos, unsigned & HowMany, unsigned & SavedNumber)
{
    for (unsigned i=0; i < Grid.size(); ++i)
    {
        SavedNumber = 0;
        HowMany = 1;
        for (unsigned j=0; j < Grid[i].size(); ++j){
            if (Grid[i][j] == 8) SavedNumber = 0;
            else if (Grid[i][j] != 0 && Grid[i][j] != 8)
            {
                if (Grid[i][j] != SavedNumber)
                {
                    SavedNumber = Grid[i][j];
                    Pos.first = i;
                    Pos.second = j;
                    HowMany = 1;
                }
                else if(Grid[i][j] == SavedNumber)++HowMany;
            }
            if(HowMany >= 3 && Grid[i][j+1] != SavedNumber)return true;
        }
    }
    return false;
}

void RemovalInColumn (CMat & Grid, const CPosition & Pos, unsigned & HowMany)
{
    int X = Pos.second;
    int Y = Pos.first;
    for (unsigned i(0); i < HowMany; ++i)
    {
        Grid[X][Y] = KImpossible;
        ++X;
    }
}

bool AtLeastThreeInAColumn (const CMat & Grid, CPosition & Pos, unsigned & HowMany, unsigned & SavedNumber)
{
    for (unsigned i=0; i < Grid.size(); ++i)
    {
        SavedNumber = 0;
        HowMany = 1;
        for (unsigned j=0; j < Grid[i].size(); ++j){
            if (Grid[i][j] == 8) SavedNumber = 0;
            else if (Grid[i][j] != 0 && Grid[i][j] != 8)
            {
                if (Grid[j][i] != SavedNumber)
                {
                    SavedNumber = Grid[j][i];
                    Pos.first = i;
                    Pos.second = j;
                    HowMany = 1;
                }
                else if(Grid[j][i] == SavedNumber)++HowMany;
            }
            if(HowMany >= 3 && (j == Grid.size()-1 || Grid[j+1][i] != SavedNumber))return true;
        }
    }
    return false;
}

void ReplaceKImpossibleNumbers (CMat & Grid)
{
    for (unsigned i(0); i < Grid.size(); ++i)
    {
        for (unsigned j(0); j < Grid[i].size(); ++j)
            if(Grid[i][j]==KImpossible)
            {
                Grid[i][j] = rand()%KNbCandies+1;
            }
    }
}

void UpdateScore (const unsigned & HowMany, int & Score)
{
    unsigned Multiplicator(HowMany-2);
    Score+=Multiplicator*3;
}

unsigned CalcPercentage (int & ScoreForNextLvl, int & Score)
{
    int Divisor(2.5);
    int Percentage(0);
    for(unsigned i(0); i < ProgressBarSize; ++i, Divisor+=2.5)
    {
        Percentage = Score*(ProgressBarSize*10)/(ScoreForNextLvl*2);
    }
    return Percentage;
}

string GameStats(int & ScoreForNextLvl, int & Score, int & MaximalHits,int & Hits)
{
    if((MaximalHits-Hits) == 0 && CalcPercentage(ScoreForNextLvl,Score) < 100) return "lost";
    else if((MaximalHits-Hits) == 0 && CalcPercentage(ScoreForNextLvl,Score) >= 100) return "won";
    else return "continue";
}

void CheckNewRecord(CMatStr & LeaderBoard, int & Score, int & GameChoice, int & LevelChoice, const string & Pseudo)
{
    unsigned LeaderBoardLevel((LevelChoice-1)*3);

    if(Score > stoi(LeaderBoard[LeaderBoardLevel][2]))  //1er meilleur score
    {
        LeaderBoard[LeaderBoardLevel+2][1] = LeaderBoard[LeaderBoardLevel+1][1];
        LeaderBoard[LeaderBoardLevel+2][2] = LeaderBoard[LeaderBoardLevel+1][2];
        LeaderBoard[LeaderBoardLevel+1][1] = LeaderBoard[LeaderBoardLevel][1];
        LeaderBoard[LeaderBoardLevel+1][2] = LeaderBoard[LeaderBoardLevel][2];
        LeaderBoard[LeaderBoardLevel][1] = Pseudo;
        LeaderBoard[LeaderBoardLevel][2] = to_string(Score);
    }
    else if(Score > stoi(LeaderBoard[LeaderBoardLevel+1][2])) //2e meilleur score
    {
        LeaderBoard[LeaderBoardLevel+2][1] = LeaderBoard[LeaderBoardLevel+1][1];
        LeaderBoard[LeaderBoardLevel+2][2] = LeaderBoard[LeaderBoardLevel+1][2];
        LeaderBoard[LeaderBoardLevel+1][1] = Pseudo;
        LeaderBoard[LeaderBoardLevel+1][2] = to_string(Score);
    }
    else if(Score > stoi(LeaderBoard[LeaderBoardLevel+2][2])) //3e meilleur score
    {
        LeaderBoard[LeaderBoardLevel+2][1] = Pseudo;
        LeaderBoard[LeaderBoardLevel+2][2] = to_string(Score);
    }

    ofstream ofs;
    if(GameChoice == 1) ofs.open(LeaderBoardCLASSIC);
    else if(GameChoice == 2) ofs.open(LeaderBoardDIAGONAL);
    for(unsigned level(0), line(0); line < 27; line+=3, ++level)
    {
        ofs << '#' << level+1 << endl;
        for(unsigned data(line); data < line+3; ++data)
            ofs << LeaderBoard[data][1] << " = " << LeaderBoard[data][2] << " ;" << endl;
        ofs << endl;
    }
    ofs.close();
}
