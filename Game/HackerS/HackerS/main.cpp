//
//  main.cpp
//  HackerS
//
//  Created by Corbin Graham on 11/12/21.
//

#include <iostream>

#include "GameEngine.h"

using namespace std;

int main(int argc, const char * argv[]) {
    // insert code here...
    cout << argc << " ";
    /*(for(int i = 0; i < argc; i++){
        cout << argv[i] << "\n";
    } */
    
    cout << "Game Launching...\n";
    
    GameEngine();
    
    return 0;
}
