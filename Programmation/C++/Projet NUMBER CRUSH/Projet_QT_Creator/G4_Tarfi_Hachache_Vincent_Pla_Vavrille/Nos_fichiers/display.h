#ifndef DISPLAY_H
#define DISPLAY_H

/**
 * \file display.h
 *
 * \brief Affichage du jeu à l'écran
 *
 * \author Quentin Pla, Léo Vincent, Emma tarfi, Sirine Achache, Julien Vavrille
 *
 * \date 26 janvier 2018
 *
 */

#include "params2.h"

/*!
 * \fn void ClearScreen2 ()
 * \brief ClearScreen2 efface l'écran
 */
void ClearScreen2 ();

/*!
 * \fn void DisplayWindowBorders (unsigned Repeat)
 * \brief DisplayWindowBorders affiche les bords de la fenêtre
 * \param Repeat[in] Nombre de répétitions pour l'affichage des bords
 */
void DisplayWindowBorders (unsigned Repeat);

/*!
 * \fn void DisplayHelpLines (const CMat & Grid)
 * \brief DisplayHelpLines affiche les indices autour de la grille (de 1 à 9)
 * \param Grid[in] Grille contenant les nombres
 */
void DisplayHelpLines (const CMat & Grid);

/*!
 * \fn void DisplayScore (int & Score)
 * \brief DisplayScore affiche le score du joueur
 * \param Score[in] Score du joueur
 */
void DisplayScore (int & Score);

/*!
  * \fn void DisplayRemainingHits (int & MaximalHits, int & Hits)
 * \brief DisplayRemainingHits affiche le nombre de coups restant
 * \param MaximalHits[in] Nombre maximum de coups attribués pour le level
 * \param Hits[in] Nombre de coups utilisés
 */
void DisplayRemainingHits (int & MaximalHits, int & Hits);

/*!
 * \fn void DisplayLeaderBoard (const int & PlayerPos, const CMatStr & LeaderBoard, int & LevelChoice)
 * \brief DisplayLeaderBoard affiche le classement des meilleurs scores du 1er au 3e (affiche le nom du joueur et son score)
 * \param PlayerPos[in] Ligne à afficher du classement (de 0 à 4)
 * \param LeaderBoard[in] Matrice contenant l'ensemble des scores des joueurs pour chaque niveau
 * \param LevelChoice[in] Niveau sélectionné
 */
void DisplayLeaderBoard (const int & PlayerPos, const CMatStr & LeaderBoard, int & LevelChoice);

/*!
 * \fn void DisplayStars (int & ScoreForNextLvl, int & Score)
 * \brief DisplayStars affiche le pourcentage et les étoiles au dessus de la barre de progression (1er étoile = score atteind à 100%, 2e à 150% et 3e à 200%)
 * \param ScoreForNextLvl[in] Score à atteindre pour gagner le niveau
 * \param Score[in] Score du joueur
 */
void DisplayStars (int & ScoreForNextLvl, int & Score);

/*!
 * \fn void DisplayProgressBar (int & ScoreForNextLvl, int & Score)
 * \brief DisplayProgressBar affiche la barre de progression
 * \param ScoreForNextLvl[in] Score à atteindre pour gagner le niveau
 * \param Score[in] Score du joueur
 */
void DisplayProgressBar (int & ScoreForNextLvl, int & Score);

/*!
 * \fn void DisplayHeader()
 * \brief DisplayHeader affiche la partie haute de la fenêtre contenant "NUMBER CRUSH"
 */
void DisplayHeader();

/*!
 * \fn void DisplayWindow (const CMat & Grid, const CMatStr & LeaderBoard, int & ScoreForNextLvl, int & Score, int & LevelChoice, int & MaximalHits, int & Hits)
 * \brief DisplayWindow affiche la grille et tout le reste de la fenêtre en appelant chaque fonctions indépendamment
 * \param Grid[in] Grille contenant les nombres
 * \param LeaderBoard[in] Matrice contenant l'ensemble des scores des joueurs pour chaque niveau
 * \param ScoreForNextLvl[in] Score à atteindre pour gagner le niveau
 * \param Score[in] Score du joueur
 * \param LevelChoice[in] Level choisi par l'utilisateur
 * \param MaximalHits[in] Nombre maximum de coups attribués pour le level
 * \param Hits[in] Nombre de coups utilisés
 */
void  DisplayWindow (const CMat & Grid, const CMatStr & LeaderBoard, int & ScoreForNextLvl, int & Score, int & LevelChoice, int & MaximalHits, int & Hits);

/*!
 * \fn void GameOver(const string & Pseudo)
 * \brief GameOver affiche la fenêtre "GAME OVER" lorsque le joueur a perdu
 * \param Pseudo[in] Pseudo du joueur entré au début du jeu
 */
void GameOver(const string & Pseudo);

/*!
 * \fn void WinGame(const string & Pseudo)
 * \brief WinGame affiche la fenêtre "YOU WIN" lorsque le joueur a gagné
 * \param Pseudo[in] Pseudo du joueur entré au début du jeu
 */
void WinGame(const string & Pseudo);

/*!
 * \fn void ShowMenu(const string & Pseudo)
 * \brief ShowMenu affiche le menu de sélection du mode de jeu (choix entre CLASSIC, DIAGONAL, RULES)
 * \param Pseudo[in] Pseudo du joueur entré au début du jeu
 */
void ShowMenu(const string & Pseudo);

/*!
 * \fn void ShowRules(const string & Pseudo)
 * \brief ShowRules affiche les régles des différents modes de jeu ainsi que les actions définies pour chaque touche
 * \param Pseudo[in] Pseudo du joueur entré au début du jeu
 */
void ShowRules(const string & Pseudo);

/*!
 * \fn void Connexion()
 * \brief Connexion affiche la page de connexion où le joueur se connecte avec son pseudo
 */
void Connexion();

/*!
 * \fn void ShowLevels(CMat & Grid, int & ScoreForNextLvl, int & LevelChoice, int & MaximalHits, const string & Pseudo)
 * \brief ShowLevels affiche les différents niveaux disponibles pour chaque mode de jeu
 * \param Grid[in] Grille contenant les nombres
 * \param ScoreForNextLvl[in,out] Score à atteindre pour gagner le niveau
 * \param LevelChoice[in,out] Level choisi par l'utilisateur
 * \param MaximalHits[in,out] Nombre maximum de coups attribués pour le level
 * \param Pseudo[in] Pseudo du joueur entré au début du jeu
 */
void ShowLevels(CMat & Grid, int & ScoreForNextLvl, int & LevelChoice, int & MaximalHits, const string & Pseudo);

#endif // DISPLAY_H
