#ifndef INIT_H
#define INIT_H

/**
 * \file init.h
 *
 * \brief Fonctions d'initialisation
 *
 * \author Quentin Pla, Léo Vincent, Emma tarfi, Sirine Achache, Julien Vavrille
 *
 * \date 26 janvier 2018
 *
 */

#include "params2.h"

/*!
 * \fn void ResetData(int & Score, int & GameChoice, int & LevelChoice, int & Hits)
 * \brief ResetData reinitialise les données
 * \param Score[in,out] Score du joueur
 * \param GameChoice[in,out] Mode de jeu choisi par l'utilisateur
 * \param LevelChoice[in,out] Level choisi par l'utilisateur
 * \param Hits[in,out] Nombre de coups utilisés
 */
void ResetData(int & Score, int & GameChoice, int & LevelChoice, int & Hits);

/*!
 * \fn void InitGrid2 (CMat & Grid, const unsigned & Size)
 * \brief InitGrid2 génère des nombres aléatoires dans la grille
 * \param Grid[in,out] Grille contenant les nombres
 * \param Size[in] Taille de la grille
 */
void InitGrid2 (CMat & Grid, const unsigned & Size);

/*!
 * \fn void InitLeaderBoard (ifstream & ifs, CMatStr & LeaderBoard, int & GameChoice)
 * \brief InitLeaderBoard en fonction du mode de jeu lit le fichier texte associé afin de remplir la matrice LeaderBoard avec le nom du joueur et son score
 * \param ifs[in] flux d'entrée permettant de lire les données d'un fichier
 * \param LeaderBoard[in,out] Matrice contenant l'ensemble des scores des joueurs pour chaque niveau
 * \param GameChoice[in] Mode de jeu choisi par l'utilisateur
 */
void InitLeaderBoard (ifstream & ifs, CMatStr & LeaderBoard, int & GameChoice);

/*!
 * \fn void CheckGrid (CMat & Grid)
 * \brief CheckGrid vérifie que la grille ne contienne pas 3 mêmes chiffres d'affilé
 * \param Grid[in,out] Grille contenant les nombres
 */
void CheckGrid (CMat & Grid);

/*!
 * \fn void Couleur (const string & coul)
 * \brief Couleur détermine la couleur du fond de la case en fonction du chiffre
 * \param coul[in] nombre permettant de définir la couleur de fond
 */
void Couleur (const string & coul);

#endif // INIT_H
