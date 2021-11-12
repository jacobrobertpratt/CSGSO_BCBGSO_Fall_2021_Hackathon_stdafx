//
//  GameEngine.cpp
//  HackerS
//
//  Created by Corbin Graham on 11/12/21.
//

#include "GameEngine.h"
#include <iostream>
#include <string>

using namespace std;

GameEngine::GameEngine()
{
    cout << "GameEngine On" << endl;
    home();
}

void GameEngine::home()
{
    cout << "Home reached" << getHome();
    
    /*
     Game:
     New Journey
     Introduction
     Setup - >
        Show Health Bar
        Show Name
        Show Stage / Status
     
     Interaction - >
        Ask for decisions and interpret to number or start by asking number
        Roll random for what is going to happen from decision
        Change decisions
        
     
        
     
     GameEngine:
        
     */
    
    
    
}

string GameEngine::getHome()
{
    return "!";
}

void GameEngine::print()
{
    
}
