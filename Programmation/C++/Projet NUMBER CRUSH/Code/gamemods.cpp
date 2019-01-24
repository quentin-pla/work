#include <iostream>

#include "gamemods.h"
#include "display.h"
#include "ingame.h"
#include "init.h"

using namespace std;

void GAME_ClassicMod(int & GameChoice, const string & Pseudo)
{
    int Score(0);
    int Hits            (0);
    int MaximalHits     (5);
    int ScoreForNextLvl(0);
    int LevelChoice     (0);
    unsigned Size = 9;
    unsigned HowMany = 1;
    unsigned SavedNumber;
    char Direction;
    bool NextNumber;
    CMat Grid;
    CMatStr LeaderBoard;
    CPosition Pos;
    ifstream ifs;
    InitGrid2(Grid, Size);
    InitLeaderBoard(ifs, LeaderBoard, GameChoice);
    CheckGrid(Grid);
    ShowLevels(Grid,ScoreForNextLvl,LevelChoice,MaximalHits,Pseudo);
    DisplayWindow(Grid,LeaderBoard,ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
    while(GameStats(ScoreForNextLvl,Score,MaximalHits,Hits) == "continue")
    {
        GameStats(ScoreForNextLvl,Score,MaximalHits,Hits);
        MakeAMove(Grid, Pos, Direction, NextNumber, LeaderBoard, ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
        while(AtLeastThreeInARow(Grid, Pos, HowMany, SavedNumber) || AtLeastThreeInAColumn(Grid, Pos, HowMany, SavedNumber))
        {
            if(AtLeastThreeInARow(Grid, Pos, HowMany, SavedNumber))
            {
                RemovalInRow(Grid, Pos, HowMany);
                DisplayWindow(Grid,LeaderBoard,ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
                ReplaceKImpossibleNumbers(Grid);
                sleep(1);
                UpdateScore(HowMany,Score);
                DisplayWindow(Grid,LeaderBoard,ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
            }
            else if(AtLeastThreeInAColumn(Grid, Pos, HowMany, SavedNumber))
            {
                RemovalInColumn(Grid, Pos, HowMany);
                DisplayWindow(Grid,LeaderBoard,ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
                ReplaceKImpossibleNumbers(Grid);
                sleep(1);
                UpdateScore(HowMany,Score);
                DisplayWindow(Grid,LeaderBoard,ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
            }
        }
        DisplayWindow(Grid,LeaderBoard,ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);

        cout << InitialColors;
    }
    if(GameStats(ScoreForNextLvl,Score,MaximalHits,Hits) == "won")
    {
        CheckNewRecord(LeaderBoard,Score, GameChoice,LevelChoice,Pseudo);
        ResetData(Score, GameChoice,LevelChoice,Hits);
        WinGame(Pseudo);
    }
    else if(GameStats(ScoreForNextLvl,Score,MaximalHits,Hits) == "lost")
    {
        ResetData(Score, GameChoice,LevelChoice,Hits);
        GameOver(Pseudo);
    }
}

void GAME_DiagonalMod(int & GameChoice, const string & Pseudo)
{
    int Score(0);
    int Hits            (0);
    int MaximalHits     (5);
    int ScoreForNextLvl(0);
    int LevelChoice     (0);
    unsigned Size = 9;
    unsigned HowMany = 1;
    char Direction;
    bool NextNumber;
    CMat Grid;
    CMatStr LeaderBoard;
    CPosition Pos;
    ifstream ifs;
    InitGrid2(Grid, Size);
    InitLeaderBoard(ifs,LeaderBoard, GameChoice);
    CheckGrid(Grid);
    ShowLevels(Grid,ScoreForNextLvl,LevelChoice,MaximalHits,Pseudo);
    DisplayWindow(Grid,LeaderBoard,ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
    while(GameStats(ScoreForNextLvl,Score,MaximalHits,Hits) == "continue")
    {
        GameStats(ScoreForNextLvl,Score,MaximalHits,Hits);
        MakeAMove(Grid, Pos, Direction, NextNumber, LeaderBoard, ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
        while(AtLeastThreeInADiagonalNordEst(Grid, Pos, HowMany))
        {
            RemovalInDiagonalNordEst(Grid, Pos, HowMany);
            DisplayWindow(Grid, LeaderBoard, ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
            ReplaceKImpossibleNumbers(Grid);
            sleep(1);
            UpdateScore(HowMany,Score);
            DisplayWindow(Grid, LeaderBoard, ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
        }
        while(AtLeastThreeInADiagonalNordOuest(Grid, Pos, HowMany))
        {
            RemovalInDiagonalNordOuest(Grid, Pos, HowMany);
            DisplayWindow(Grid, LeaderBoard, ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
            ReplaceKImpossibleNumbers(Grid);
            sleep(1);
            UpdateScore(HowMany,Score);
            DisplayWindow(Grid, LeaderBoard, ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);
        }
        DisplayWindow(Grid, LeaderBoard, ScoreForNextLvl,Score,LevelChoice,MaximalHits,Hits);

        cout << InitialColors;
    }
    if(GameStats(ScoreForNextLvl,Score,MaximalHits,Hits) == "won")
    {
        CheckNewRecord(LeaderBoard,Score, GameChoice,LevelChoice,Pseudo);
        ResetData(Score, GameChoice,LevelChoice,Hits);
        WinGame(Pseudo);
    }
    else if(GameStats(ScoreForNextLvl,Score,MaximalHits,Hits) == "lost")
    {
        ResetData(Score, GameChoice,LevelChoice,Hits);
        GameOver(Pseudo);
    }
}
