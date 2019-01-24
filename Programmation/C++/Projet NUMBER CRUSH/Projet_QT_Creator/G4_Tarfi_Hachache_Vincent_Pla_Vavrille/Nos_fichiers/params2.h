#ifndef PARAMS2_H
#define PARAMS2_H

/**
 * \file params2.h
 *
 * \brief Changement de couleur des bords à chaque lancement du jeu
 *
 * \author Quentin Pla, Léo Vincent, Emma tarfi, Sirine Achache, Julien Vavrille
 *
 * \date 26 janvier 2018
 *
 */

#include <vector>
#include <string>
#include <ctime>
#include <iomanip>
#include <fstream>
#include <map>
#include "canonic.h"

using namespace std;

typedef vector <unsigned> CVLine; // un type représentant une ligne de la grille
typedef vector <CVLine> CMat; // un type représentant la grille
typedef vector <string> CVLineStr;
typedef vector<CVLineStr> CMatStr;
typedef pair <unsigned, unsigned> CPosition; // une position dans la grille
const int KNbCandies      (7);
const int KColor          (40);
const int KImpossible     (0);
const unsigned WindowSize      (72);
const unsigned ProgressBarSize (20);
const string InitialColors ("\033[1;27;37;40m");
const string ErrorMessage  ("        Erreur, saisie invalide !");
const string LeaderBoardCLASSIC ("../G4_Tarfi_Hachache_Vincent_Pla_Vavrille/Nos_fichiers/leaderboardCLASSIC.txt");
const string LeaderBoardDIAGONAL ("../G4_Tarfi_Hachache_Vincent_Pla_Vavrille/Nos_fichiers/leaderboardDIAGONAL.txt");

/*!
 * \fn string ConstBorderColor()
 * \brief ConstBorderColor génère une couleur de bordure du jeu aléatoire lorsqu'on lance le jeu
 * \return retourne la couleur des bordures générées aléatoirement
 */
string ConstBorderColor();

const string BorderColor = ConstBorderColor();

#endif // PARAMS2_H
