#include <iostream>

#include "display.h"
#include "ingame.h"
#include "init.h"
#include "canonic.h"
#include "gamemods.h"

using namespace std;

void ClearScreen2 ()
{
#ifdef linux
    cout << "\033[H\033[2J";
#else
    system("cmd /c cls");
#endif
}

void DisplayWindowBorders (unsigned Repeat)
{
    while(Repeat>0)
    {
        cout << "█" << right << setw(WindowSize+1) << "█" << endl; // 1 █ = 2 char
        --Repeat;
    }
}

void DisplayHelpLines (const CMat & Grid)
{
    unsigned Nbcolonne(1);
    cout << BorderColor << "█" << InitialColors << "             ";
    for (; Nbcolonne < Grid.size()+1; ++Nbcolonne)
    {
        cout << Nbcolonne << ' ';
    }
    cout << "       ";
}

void DisplayScore (int & Score)
{
    cout << "Score: " << left << setw(25) << Score << BorderColor << "█" << endl;
}

void DisplayRemainingHits (int & MaximalHits, int & Hits)
{
    cout << right << setw(21) << "Remaining Hits: " << left << setw(16) << (MaximalHits-Hits) << BorderColor << "█";
}

void DisplayLeaderBoard (const int & PlayerPos, const CMatStr & LeaderBoard, int & LevelChoice)
{
    unsigned LeaderBoardLevel((LevelChoice-1)*3);
    string FirstPlayer("1." + LeaderBoard[LeaderBoardLevel][1] + ' ' + LeaderBoard[LeaderBoardLevel][2]);
    string SecondPlayer("2." + LeaderBoard[LeaderBoardLevel+1][1] + ' ' + LeaderBoard[LeaderBoardLevel+1][2]);
    string ThirdPlayer("3." + LeaderBoard[LeaderBoardLevel+2][1] + ' ' + LeaderBoard[LeaderBoardLevel+2][2]);
    if(PlayerPos == 0) cout << "     " << "\033[1;7;37;40m" << "    LEADERBOARD     " << BorderColor << right << setw(15) << "█";
    else if(PlayerPos == 1) cout << "     " << "\033[1;7;37;40m" << " " << "\033[1;27;37;43m" << left << setw(18) << FirstPlayer << "\033[1;7;37;40m" << " " << BorderColor << right << setw(15) << "█";
    else if(PlayerPos == 2) cout << "     " << "\033[1;7;37;40m" << " " <<"\033[1;27;37;47m" << left << setw(18) << SecondPlayer << "\033[1;7;37;40m" << " " << BorderColor << right << setw(15) << "█";
    else if(PlayerPos == 3) cout << "     " << "\033[1;7;37;40m" << " " <<"\033[1;27;37;40m" << left << setw(18) << ThirdPlayer << "\033[1;7;37;40m" << " " << BorderColor << right << setw(15) << "█";
    else if(PlayerPos == 4) cout << "     " << "\033[1;37;40m" << "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█" << BorderColor << right << setw(15) << "█";
}

void DisplayStars (int & ScoreForNextLvl, int & Score)
{
    unsigned i(100);
    string Percentage = (to_string(CalcPercentage(ScoreForNextLvl,Score)) + "%");
    cout << "     " << left << setw(5) << Percentage << "\033[0;33;40m";
    for(; i <= 200; i+=50)
    {
        if(CalcPercentage(ScoreForNextLvl,Score) >= i) cout << "    ★";
        else cout << "    ☆";
    }
    cout << BorderColor << right << setw(15) << "█";
}

void DisplayProgressBar (int & ScoreForNextLvl, int & Score)
{
    const string BackColor("\033[0;32;40m");
    const string FrontColor("\033[1;32;40m");
    for(unsigned i(10); i <= 200; i+=10)
    {
        if(i <= CalcPercentage(ScoreForNextLvl,Score)) cout << FrontColor << "█";
        else if(i > CalcPercentage(ScoreForNextLvl,Score)) cout << BackColor << "█";
    }
    cout << BorderColor << right << setw(15) << "█";
}

void DisplayHeader()
{
    cout << BorderColor;
    cout << "█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█" << endl;
    DisplayWindowBorders(1);
    cout << "█" << InitialColors <<"       █▄  █ █  █ █▀▄▀█ █▀▀▄ █▀▀ █▀▀█   █▀▀█ █▀▀█ █  █ █▀▀ █  █       " << BorderColor << "█" << endl;
    cout << "█" << InitialColors <<"       █ █ █ █  █ █ ▀ █ █▀▀▄ █▀▀ █▄▄▀   █    █▄▄▀ █  █ ▀▀█ █▀▀█       " << BorderColor << "█" << endl;
    cout << "█" << InitialColors <<"       █  ▀█  ▀▀▀ ▀   ▀ ▀▀▀  ▀▀▀ ▀ ▀▀   █▄▄█ ▀ ▀▀  ▀▀▀ ▀▀▀ ▀  ▀       " << BorderColor << "█" << endl;
}

void DisplayWindow (const CMat & Grid, const CMatStr & LeaderBoard, int & ScoreForNextLvl, int & Score, int & LevelChoice, int & MaximalHits, int & Hits)
{
    ClearScreen2();
    DisplayHeader();
    DisplayWindowBorders(1);
    unsigned Nbligne(1);
    DisplayHelpLines(Grid);
    DisplayScore(Score);
    for (unsigned i=0; i < Grid.size(); ++i)
    {
        cout << BorderColor << "█" << InitialColors << "           " << Nbligne << ' ';
        for (unsigned j=0; j < Grid[i].size(); ++j){
            if(Grid[i][j] != KImpossible && Grid[i][j] != 8)
            {
                Couleur(to_string((Grid[i][j])+KColor));
                cout << Grid[i][j] << ' ';
            }
            else if (Grid[i][j] == 8)
            {
                cout << "\033[0;30;40m" << "██";
            }
            else if (Grid[i][j] == KImpossible)
            {
                cout << InitialColors << "██";
            }
        }
        cout << InitialColors << ' ' << Nbligne;
        if(Nbligne == 1)DisplayRemainingHits(MaximalHits,Hits);
        else if(Nbligne == 2 || Nbligne == 8) cout << BorderColor << right << setw(40) << "█";
        else if(Nbligne == 3)DisplayLeaderBoard(0,LeaderBoard,LevelChoice);
        else if(Nbligne == 4)DisplayLeaderBoard(1,LeaderBoard,LevelChoice);
        else if(Nbligne == 5)DisplayLeaderBoard(2,LeaderBoard,LevelChoice);
        else if(Nbligne == 6)DisplayLeaderBoard(3,LeaderBoard,LevelChoice);
        else if(Nbligne == 7)DisplayLeaderBoard(4,LeaderBoard,LevelChoice);
        else if(Nbligne == 9)DisplayStars(ScoreForNextLvl,Score);
        ++Nbligne;
        cout << endl;
    }
    DisplayHelpLines(Grid);
    DisplayProgressBar(ScoreForNextLvl,Score);
    cout << endl << BorderColor;
    DisplayWindowBorders(1);
    cout << "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█" << InitialColors << endl;
}

void GameOver(const string & Pseudo)
{
    string LoseColor("\033[1;31;40m");
    ClearScreen2();
    DisplayHeader();
    DisplayWindowBorders(5);
    cout << "█" << LoseColor <<"              █▀▀▀ █▀▀█ █▀▄▀█ █▀▀   █▀▀█ ▀█ █▀ █▀▀ █▀▀█               " << BorderColor << "█" << endl;
    cout << "█" << LoseColor <<"              █ ▀█ █▄▄█ █ ▀ █ █▀▀    █  █  █▄█  █▀▀ █▄▄▀              " << BorderColor << "█" << endl;
    cout << "█" << LoseColor <<"              ▀▀▀▀ ▀  ▀ ▀   ▀ ▀▀▀     ▀▀▀▀   ▀   ▀▀▀ ▀ ▀▀             " << BorderColor << "█" << endl;
    DisplayWindowBorders(5);
    cout << "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█" << endl << InitialColors;
    sleep(3);
    ClearScreen2();
    ShowMenu(Pseudo);
}

void WinGame(const string & Pseudo)
{
    string WinColor("\033[1;32;40m");
    ClearScreen2();
    DisplayHeader();
    DisplayWindowBorders(5);
    cout << "█" << WinColor <<"                   █  █ █▀▀█ █  █   █   █ █▀▀█ █▀▀▄                   " << BorderColor << "█" << endl;
    cout << "█" << WinColor <<"                   ▀▀▀█ █  █ █  █   █▄█▄█ █  █ █  █                   " << BorderColor << "█" << endl;
    cout << "█" << WinColor <<"                   ▀▀▀▀ ▀▀▀▀  ▀▀▀    ▀ ▀  ▀▀▀▀ ▀  ▀                   " << BorderColor << "█" << endl;
    DisplayWindowBorders(5);
    cout << "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█" << endl << InitialColors;
    sleep(3);
    ClearScreen2();
    ShowMenu(Pseudo);
}

void ShowRules(const string & Pseudo)
{
    const string CLASSICColorBG("\033[1;31;41m");
    const string CLASSICColorFG("\033[1;37;41m");
    const string DIAGONALColorBG("\033[1;32;42m");
    const string DIAGONALColorFG("\033[1;37;42m");
    const string RULESColorBG("\033[1;30;47m");
    const string RULESColorFG("\033[1;37;47m");
    const string CONTROLSColorBG("\033[1;33;43m");
    const string CONTROLSColorFG("\033[1;37;43m");
    const string IndicationsColor("\033[1;30;40m");

    DisplayHeader();
    DisplayWindowBorders(1);
    cout << "█       " << RULESColorBG << left << setw(56) << " GENERAL RULES" << BorderColor << "       █" << endl;
    cout << "█       " << RULESColorFG << left << setw(58) << " ► Create a line of minimum 3 same numbers to get points" << BorderColor << "       █" << endl;
    cout << "█       " << RULESColorFG << left << setw(58) << " ► Game stops when you don't have anymore hits" << BorderColor << "       █" << endl;
    cout << "█       " << RULESColorFG << left << setw(58) << " ► Numbers can be swapped horizontally or vertically" << BorderColor << "       █" << endl;
    cout << "█       " << RULESColorFG << left << setw(58) << " ► To win the game you need to do at least 100%" << BorderColor << "       █" << endl;
    cout << "█       " << RULESColorFG << left << setw(58) << " ► Level difficulty increases for each levels" << BorderColor << "       █" << endl;
    cout << "█       " << RULESColorFG << left << setw(58) << " ► Try to be in the leaderboard to show you're the best" << BorderColor << "       █" << endl;
    DisplayWindowBorders(1);
    cout << "█       " << CLASSICColorBG << left << setw(56) << " CLASSIC MOD" << BorderColor << "       █" << endl;
    cout << "█       " << CLASSICColorFG << left << setw(58) << " ► Align numbers vertically or horizontally" << BorderColor << "       █" << endl;
    DisplayWindowBorders(1);
    cout << "█       " << DIAGONALColorBG << left << setw(56) << " DIAGONAL MOD" << BorderColor << "       █" << endl;
    cout << "█       " << DIAGONALColorFG << left << setw(58) << " ► Align numbers diagonally" << BorderColor << "       █" << endl;
    DisplayWindowBorders(1);
    cout << "█       " << CONTROLSColorBG << left << setw(56) << " CONTROLS" << BorderColor << "       █" << endl;
    cout << "█       " << CONTROLSColorFG << left << setw(58) << " ► Horizontal: select the column (1-9)" << BorderColor << "       █" << endl;
    cout << "█       " << CONTROLSColorFG << left << setw(58) << " ► Vertical: select the line (1-9)" << BorderColor << "       █" << endl;
    cout << "█       " << CONTROLSColorFG << left << setw(58) << " ► z: swap with the top number" << BorderColor << "       █" << endl;
    cout << "█       " << CONTROLSColorFG << left << setw(58) << " ► s: swap with the bottom number" << BorderColor << "       █" << endl;
    cout << "█       " << CONTROLSColorFG << left << setw(58) << " ► q: swap with the left number" << BorderColor << "       █" << endl;
    cout << "█       " << CONTROLSColorFG << left << setw(58) << " ► d: swap with the right number" << BorderColor << "       █" << endl;
    DisplayWindowBorders(1);
    cout << "█                    " << IndicationsColor << "(PRESS ESCAPE TO RETURN BACK)" << BorderColor << "                     █" <<endl;
    cout << "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█" << endl << InitialColors;
    char c;
    do
    {
        set_input_mode ();
        read (STDIN_FILENO, &c, 1);
    }
    while((int)c != 27);
    ClearScreen2();
    ShowMenu(Pseudo);
}

void ShowMenu(const string & Pseudo)
{
    int GameChoice      (0);
    srand(time(NULL));
    const string CLASSICColorBG("\033[1;31;41m");
    const string CLASSICColorFG("\033[1;37;41m");
    const string DIAGONALColorBG("\033[1;32;42m");
    const string DIAGONALColorFG("\033[1;37;42m");
    const string RULESColor("\033[1;7;30;40m");

    DisplayHeader();
    DisplayWindowBorders(4);
    cout << "█                             " << CLASSICColorBG << " 1 " << CLASSICColorFG << "CLASSIC  " << BorderColor << "                             █" <<endl;
    DisplayWindowBorders(1);
    cout << "█                             " << DIAGONALColorBG << " 2 " << DIAGONALColorFG << "DIAGONAL " << BorderColor << "                             █" <<endl;
    DisplayWindowBorders(1);
    cout << "█                             " << RULESColor << " 3 " << RULESColor << "RULES    " << BorderColor << "                             █" <<endl;
    DisplayWindowBorders(4);
    cout << "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█" << endl << InitialColors;
    char c;
    set_input_mode ();
    read (STDIN_FILENO, &c, 1);
    GameChoice = ((int)c-48);
    switch(GameChoice)
    {
    case 1:
        ClearScreen2();
        GAME_ClassicMod(GameChoice,Pseudo);
        break;
    case 2:
        ClearScreen2();
        GAME_DiagonalMod(GameChoice,Pseudo);
        break;
    case 3:
        ClearScreen2();
        ShowRules(Pseudo);
        break;
    default:
        ClearScreen2();
        ShowMenu(Pseudo);
        break;
    }
}

void Connexion()
{
    string Pseudo;
    char c;
    const string ConnexionColorFG("\033[1;7;30;47m");
    const string ConnexionColorBG("\033[1;30;47m");
    const string IndicationsColor("\033[1;30;40m");

    while(true)
    {
        ClearScreen2();
        DisplayHeader();
        DisplayWindowBorders(5);
        cout << "█                        " << ConnexionColorBG << left << setw(7) << " Login " << ConnexionColorFG << ' ' << left << setw(14) << Pseudo << BorderColor << "                        █" <<endl;
        DisplayWindowBorders(6);
        cout << "█                        " << IndicationsColor << "(PRESS ENTER TO START)" << BorderColor << "                        █" <<endl;
        cout << "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█" << endl << InitialColors;
        set_input_mode ();
        read (STDIN_FILENO, &c, 1);
        if(Pseudo.size() < 12 && (int)c != 127 && (((int)c >= 65 && (int)c <=90) || ((int)c >= 48 && (int)c <=57) || ((int)c >= 97 && (int)c <= 122))) Pseudo+=c;
        else if(Pseudo.size() > 0 && (int)c == 127) Pseudo.erase(Pseudo.size()-1);
        else if(Pseudo.size() > 2 && c == '\n') break;
    }
    ClearScreen2();
    ShowMenu(Pseudo);
}

void ShowLevels(CMat & Grid, int & ScoreForNextLvl, int & LevelChoice, int & MaximalHits, const string & Pseudo)
{

    const string IndicationsColor("\033[1;30;40m");
    const string SimpleColorBG("\033[1;32;42m");
    const string SimpleColorFG("\033[1;37;42m");
    const string MediumColorBG("\033[1;33;43m");
    const string MediumColorFG("\033[1;37;43m");
    const string HardColorBG("\033[1;31;41m");
    const string HardColorFG("\033[1;37;41m");


    DisplayHeader();
    DisplayWindowBorders(4);
    cout << "█           " << SimpleColorBG << " 1 " << SimpleColorFG << "LEVEL 1 " << InitialColors;
    cout << "       " << MediumColorBG << " 4 " << MediumColorFG << "LEVEL 4 " << InitialColors;
    cout << "       " << HardColorBG << " 7 " << HardColorFG << "LEVEL 7 " << BorderColor << "            █" <<endl;
    DisplayWindowBorders(1);
    cout << "█           " << SimpleColorBG << " 2 " << SimpleColorFG << "LEVEL 2 " << InitialColors;
    cout << "       " << MediumColorBG << " 5 " << MediumColorFG << "LEVEL 5 " << InitialColors;
    cout << "       " << HardColorBG << " 8 " << HardColorFG << "LEVEL 8 " << BorderColor << "            █" <<endl;
    DisplayWindowBorders(1);
    cout << "█           " << SimpleColorBG << " 3 " << SimpleColorFG << "LEVEL 3 " << InitialColors;
    cout << "       " << MediumColorBG << " 6 " << MediumColorFG << "LEVEL 6 " << InitialColors;
    cout << "       " << HardColorBG << " 9 " << HardColorFG << "LEVEL 9 " << BorderColor << "            █" <<endl;
    DisplayWindowBorders(3);
    cout << "█                    " << IndicationsColor << "(PRESS ESCAPE TO RETURN BACK)" << BorderColor << "                     █" <<endl;
    cout << "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█" << endl << InitialColors;
    char c;
    set_input_mode ();
    read (STDIN_FILENO, &c, 1);
    if((int)c == 27)
    {
        ClearScreen2();
        ShowMenu(Pseudo);
    }
    LevelChoice = ((int)c-48);
    switch(LevelChoice)
    {
    case 1:
        MaximalHits = 16;
        ScoreForNextLvl = 50;
        break;
    case 2:
        MaximalHits = 16;
        ScoreForNextLvl = 50;
        Grid[4][4] = 8;
        break;
    case 3:
        MaximalHits = 16;
        ScoreForNextLvl = 50;
        Grid[1][1] = 8;
        Grid[4][4] = 8;
        Grid[7][7] = 8;
        break;
    case 4:
        MaximalHits = 17;
        ScoreForNextLvl = 50;
        Grid[1][1] = 8;
        Grid[4][4] = 8;
        Grid[7][7] = 8;
        Grid[7][1] = 8;
        Grid[1][7] = 8;
        break;
    case 5:
        MaximalHits = 15;
        ScoreForNextLvl = 50;
        Grid[3][4] = 8;
        Grid[4][4] = 8;
        Grid[5][4] = 8;
        Grid[4][3] = 8;
        Grid[4][5] = 8;
        Grid[0][0] = 8;
        Grid[8][8] = 8;
        Grid[0][8] = 8;
        Grid[8][0] = 8;
        break;
    case 6:
        MaximalHits = 18;
        ScoreForNextLvl = 50;
        Grid[2][2] = 8;
        Grid[5][5] = 8;
        Grid[3][2] = 8;
        Grid[4][2] = 8;
        Grid[2][3] = 8;
        Grid[2][4] = 8;
        Grid[6][5] = 8;
        Grid[7][5] = 8;
        Grid[5][6] = 8;
        Grid[5][7] = 8;
        break;
    case 7:
        MaximalHits = 17;
        ScoreForNextLvl = 50;
        for (unsigned i(0); i < 9; ++i)
        {
            Grid[4][i]=8;
        }
        Grid[3][4] = 8;
        Grid[5][4] = 8;
        break;
    case 8:
        MaximalHits = 18;
        ScoreForNextLvl = 50;
        Grid[1][3] = 8;
        Grid[2][2] = 8;
        Grid[3][1] = 8;
        Grid[5][1] = 8;
        Grid[6][2] = 8;
        Grid[7][3] = 8;
        Grid[7][5] = 8;
        Grid[6][6] = 8;
        Grid[5][7] = 8;
        Grid[3][7] = 8;
        Grid[2][6] = 8;
        Grid[1][5] = 8;
        break;
    case 9:
        MaximalHits = 34;
        ScoreForNextLvl = 100;
        for (unsigned i(0); i < 9; ++i)
        {
            Grid[i][4]=8;
        }
        for (unsigned i(0); i < 9; ++i)
        {
            Grid[4][i]=8;
        }
        break;
    default:
        ClearScreen2();
        ShowLevels(Grid, ScoreForNextLvl, LevelChoice, MaximalHits,Pseudo);
        break;
    }
}
