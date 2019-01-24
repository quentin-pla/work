#ifndef CANONIC_H
#define CANONIC_H

/**
 * \file canonic.h
 *
 * \brief Passage du mode canonique à non canonique qui permet de rendre le jeu plus réactif
 *
 * \author Quentin Pla, Léo Vincent, Emma tarfi, Sirine Achache, Julien Vavrille
 *
 * \date 26 janvier 2018
 *
 */

#include <cstdlib>
#include <stdio.h>
#include <stdlib.h>
#include <termios.h>
#include <unistd.h>

using namespace std;

/*!
 * \fn void reset_input_mode (void)
 * \brief reset_input_mode permet le passage au mode canonique
 */
void reset_input_mode (void);

/*!
 * \fn void set_input_mode (void)
 * \brief set_input_mode permet le passage au mode non canonique
 */
void set_input_mode (void);

#endif // CANONIC_H
