TEMPLATE = app
CONFIG += console c++11
CONFIG -= app_bundle
CONFIG -= qt
LIBS += -static

SOURCES += main.cpp \
    Correc_prof/gridmanagement.cpp \
    Correc_prof/nsutil.cpp \
    Correc_prof/game.cpp \
    Nos_fichiers/display.cpp \
    Nos_fichiers/canonic.cpp \
    Nos_fichiers/ingame.cpp \
    Nos_fichiers/gamemods.cpp \
    Nos_fichiers/init.cpp \
    Nos_fichiers/params2.cpp

HEADERS += \
    Correc_prof/nsutil.h \
    Correc_prof/type.h \
    Correc_prof/params.h \
    Correc_prof/gridmanagement.h \
    Correc_prof/game.h \
    Nos_fichiers/display.h \
    Correc_prof/type_copy.h \
    Nos_fichiers/canonic.h \
    Nos_fichiers/ingame.h \
    Nos_fichiers/gamemods.h \
    Nos_fichiers/init.h \
    Nos_fichiers/params2.h

QMAKE_MAC_SDK = macosx10.13
