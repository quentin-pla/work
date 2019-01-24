#ifndef GAMEMODS_H
#define GAMEMODS_H

/**
 * \file gamemods.h
 *
 * \brief Les différents modes de jeu
 *
 * \author Quentin Pla, Léo Vincent, Emma tarfi, Sirine Achache, Julien Vavrille
 *
 * \date 26 janvier 2018
 *
 */

#include "params2.h"

/*!
 * \fn void GAME_ClassicMod(int & GameChoice, const string & Pseudo)
 * \brief GAME_ClassicMod permet de jouer en mode classique du jeu c'est-à-dire qu'il faut aligner au moins 3 chiffres en ligne ou en colonne
 * \param GameChoice[in] le mode de jeu que l'on a choisi
 * \param Pseudo[in] le pseudo que l'on a rentré
 */
void GAME_ClassicMod(int & GameChoice, const string & Pseudo);

/*!
 * \fn void GAME_DiagonalMod(int & GameChoice, const string & Pseudo)
 * \brief GAME_DiagonalMod permet de jouer en mode diagonal du jeu c'est-à-dire qu'il faut aligner au moins 3 chiffres en diagonale
 * \param GameChoice[in] le mode de jeu que l'on a choisi
 * \param Pseudo[in] le pseudo que l'on a rentré
 */
void GAME_DiagonalMod(int & GameChoice, const string & Pseudo);

#endif // GAMEMODS_H
