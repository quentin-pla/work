#ifndef INGAME_H
#define INGAME_H

/**
 * \file ingame.h
 *
 * \brief Fonctionnalité du jeu en interaction avec le joueur
 *
 * \author Quentin Pla, Léo Vincent, Emma tarfi, Sirine Achache, Julien Vavrille
 *
 * \date 26 janvier 2018
 *
 */

#include "params2.h"

/*!
 * \fn void SetPos (CPosition & Pos, const CMat & Grid, const CMatStr & LeaderBoard, int & ScoreForNextLvl, int & Score, int & LevelChoice, int & MaximalHits, int & Hits)
 * \brief SetPos permet de sélectionner la position horizontale et verticale de la case que l'on souhaite
 * \param Pos[in,out] Contient la position de la case sélectionnée par l'utilisateur
 * \param Grid[in] Grille contenant les nombres
 * \param LeaderBoard[in] Matrice contenant l'ensemble des scores des joueurs pour chaque niveau
 * \param ScoreForNextLvl[in] Score à atteindre pour gagner le niveau
 * \param Score[in] Score du joueur
 * \param LevelChoice[in] Level choisi par l'utilisateur
 * \param MaximalHits[in] Nombre maximum de coups attribués pour le level
 * \param Hits[in] Nombre de coups utilisés
 */
void SetPos (CPosition & Pos, const CMat & Grid, const CMatStr & LeaderBoard, int & ScoreForNextLvl, int & Score, int & LevelChoice, int & MaximalHits, int & Hits);

/*!
 * \fn void SetDirection (CMat & Grid, CPosition & Pos, char & Direction, bool & NextNumber)
 * \brief SetDirection permet de choisir la directionn souhaitée (haut, bas , gauche, droite)
 * \param Grid[in] Grille contenant les nombres
 * \param Pos[in] Contient la position de la case sélectionnée par l'utilisateur
 * \param Direction[in,out] Caractère contenant la direction choisie
 * \param NextNumber[in,out] Booléen qui vérifie si le nombre a bien été échangé
 */
void SetDirection (CMat & Grid, CPosition & Pos, char & Direction, bool & NextNumber);

/*!
 * \fn void MakeAMove (CMat & Grid, CPosition & Pos, char & Direction, bool & NextNumber, const CMatStr & LeaderBoard, int & ScoreForNextLvl, int & Score, int & LevelChoice, int & MaximalHits, int & Hits)
 * \brief MakeAMove
 * \param Grid[in] Grille contenant les nombres
 * \param Pos[in] Contient la position de la case sélectionnée par l'utilisateur
 * \param Direction[in] Caractère contenant la direction choisie
 * \param NextNumber[in,out] Booléen qui vérifie si le nombre a bien été échangé
 * \param LeaderBoard[in] Matrice contenant l'ensemble des scores des joueurs pour chaque niveau
 * \param ScoreForNextLvl[in] Score à atteindre pour gagner le niveau
 * \param Score[in] Score du joueur
 * \param LevelChoice[in] Level choisi par l'utilisateur
 * \param MaximalHits[in] Nombre maximum de coups attribués pour le level
 * \param Hits[in] Nombre de coups utilisés
 */
void MakeAMove (CMat & Grid, CPosition & Pos, char & Direction, bool & NextNumber, const CMatStr & LeaderBoard, int & ScoreForNextLvl, int & Score, int & LevelChoice, int & MaximalHits, int & Hits);

/*!
 * \fn void RemovalInDiagonalNordEst (CMat & Grid, const CPosition & Pos, unsigned & HowMany)
 * \brief RemovalInDiagonalNordEst
 * \param Grid[in,out] Grille contenant les nombres
 * \param Pos[in] Contient la position de la case sélectionnée par l'utilisateur
 * \param HowMany[in] Variable qui contient le nombre de même nombres alignés[in] Variable qui contient le nombre de même nombres alignés
 */
void RemovalInDiagonalNordEst (CMat & Grid, const CPosition & Pos, unsigned & HowMany);

/*!
 * \fn bool AtLeastThreeInADiagonalNordEst (const CMat & Grid, CPosition & Pos, unsigned & HowMany)
 * \brief AtLeastThreeInADiagonalNordEst
 * \param Grid[in] Grille contenant les nombres
 * \param Pos[in,out] Contient la position de la case sélectionnée par l'utilisateur
 * \param HowMany[in,out] Variable qui contient le nombre de même nombres alignés[in] Variable qui contient le nombre de même nombres alignés
 * \return booléen qui détermine s'il reste encore des séries de nombres alignés
 */
bool AtLeastThreeInADiagonalNordEst (const CMat & Grid, CPosition & Pos, unsigned & HowMany);

/*!
 * \fn void RemovalInDiagonalNordOuest (CMat & Grid, const CPosition & Pos, unsigned & HowMany)
 * \brief RemovalInDiagonalNordOuest
 * \param Grid[in,out] Grille contenant les nombres
 * \param Pos[in] Contient la position de la case sélectionnée par l'utilisateur
 * \param HowMany[in] Variable qui contient le nombre de même nombres alignés
 */
void RemovalInDiagonalNordOuest (CMat & Grid, const CPosition & Pos, unsigned & HowMany);

/*!
 * \fn bool AtLeastThreeInADiagonalNordOuest (const CMat & Grid, CPosition & Pos, unsigned & HowMany)
 * \brief AtLeastThreeInADiagonalNordOuest
 * \param Grid[in] Grille contenant les nombres
 * \param Pos[in,out] Contient la position de la case sélectionnée par l'utilisateur
 * \param HowMany[in,out] Variable qui contient le nombre de même nombres alignés
 * \return booléen qui détermine s'il reste encore des séries de nombres alignés
 */
bool AtLeastThreeInADiagonalNordOuest (const CMat & Grid, CPosition & Pos, unsigned & HowMany);

/*!
 * \fn void emovalInRow (CMat & Grid, const CPosition & Pos, unsigned & HowMany)
 * \brief RemovalInRow
 * \param Grid[in,out] Grille contenant les nombres
 * \param Pos[in] Contient la position de la case sélectionnée par l'utilisateur
 * \param HowMany[in] Variable qui contient le nombre de même nombres alignés
 */
void RemovalInRow (CMat & Grid, const CPosition & Pos, unsigned & HowMany);

/*!
 * \fn bool AtLeastThreeInARow (CMat & Grid, CPosition & Pos, unsigned & HowMany, unsigned & SavedNumber)
 * \brief AtLeastThreeInARow
 * \param Grid[in] Grille contenant les nombres
 * \param Pos[in,out] Contient la position de la case sélectionnée par l'utilisateur
 * \param HowMany[in,out] Variable qui contient le nombre de même nombres alignés
 * \param SavedNumber[in,out] Nombre sauvegardé à chaque début de série puis remplacé en fin de série par le prochain nombre différent de celui sauvegardé[in,out] Nombre sauvegardé à chaque début de série puis remplacé en fin de série par le prochain nombre différent de celui sauvegardé
 * \return booléen qui détermine s'il reste encore des séries de nombres alignés
 */
bool AtLeastThreeInARow (CMat & Grid, CPosition & Pos, unsigned & HowMany, unsigned & SavedNumber);

/*!
 * \fn void RemovalInColumn (CMat & Grid, const CPosition & Pos, unsigned & HowMany)
 * \brief RemovalInColumn
 * \param Grid[in,out] Grille contenant les nombres
 * \param Pos[in] Contient la position de la case sélectionnée par l'utilisateur
 * \param HowMany[in] Variable qui contient le nombre de même nombres alignés
 */
void RemovalInColumn (CMat & Grid, const CPosition & Pos, unsigned & HowMany);

/*!
 * \fn bool AtLeastThreeInAColumn (const CMat & Grid, CPosition & Pos, unsigned & HowMany, unsigned & SavedNumber)
 * \brief AtLeastThreeInAColumn
 * \param Grid[in] Grille contenant les nombres
 * \param Pos[in,out] Contient la position de la case sélectionnée par l'utilisateur
 * \param HowMany[in,out] Variable qui contient le nombre de même nombres alignés
 * \param SavedNumber[in,out] Nombre sauvegardé à chaque début de série puis remplacé en fin de série par le prochain nombre différent de celui sauvegardé
 * \return booléen qui détermine s'il reste encore des séries de nombres alignés
 */
bool AtLeastThreeInAColumn (const CMat & Grid, CPosition & Pos, unsigned & HowMany, unsigned & SavedNumber);

/*!
 * \fn void ReplaceKImpossibleNumbers (CMat & Grid)
 * \brief ReplaceKImpossibleNumbers
 * \param Grid[in,out] Grille contenant les nombres
 */
void ReplaceKImpossibleNumbers (CMat & Grid);

/*!
 * \fn void UpdateScore (const unsigned & HowMany, int & Score)
 * \brief UpdateScore
 * \param HowMany[in] Variable qui contient le nombre de même nombres alignés
 * \param Score[in,out] Score du joueur
 */
void UpdateScore (const unsigned & HowMany, int & Score);

/*!
 * \fn string GameStats(int & ScoreForNextLvl, int & Score, int & MaximalHits,int & Hits)
 * \brief GameStats
 * \param ScoreForNextLvl[in] Score à atteindre pour gagner le niveau
 * \param Score[in] Score du joueur
 * \param MaximalHits[in] Nombre maximum de coups attribués pour le level
 * \param Hits[in] Nombre de coups utilisés
 * \return string qui permet de savoir si le joueur à perdu, gagné, ou s'il peut toujours continuer à jouer
 */
string GameStats(int & ScoreForNextLvl, int & Score, int & MaximalHits,int & Hits);

/*!
 * \fn void CheckNewRecord(CMatStr & LeaderBoard, int & Score, int & GameChoice, int & LevelChoice, const string & Pseudo)
 * \brief CheckNewRecord
 * \param LeaderBoard[in,out] Matrice contenant l'ensemble des scores des joueurs pour chaque niveau
 * \param Score[in] Score du joueur
 * \param GameChoice[in] Mode de jeu choisi par l'utilisateur
 * \param LevelChoice[in] Level choisi par l'utilisateur
 * \param Pseudo[in] Pseudo du joueur entré au début du jeu
 */
void CheckNewRecord(CMatStr & LeaderBoard, int & Score, int & GameChoice, int & LevelChoice, const string & Pseudo);

/*!
 * \fn unsigned CalcPercentage (int & ScoreForNextLvl, int & Score)
 * \brief CalcPercentage
 * \param ScoreForNextLvl[in] Score à atteindre pour gagner le niveau
 * \param Score[in] Score du joueur
 */
unsigned CalcPercentage (int & ScoreForNextLvl, int & Score);

#endif // INGAME_H
