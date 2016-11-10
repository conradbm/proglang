//  Cmd.cpp

#include "Cmd.h"

Cmd::Cmd()
{  }
Cmd::~Cmd()
{  }

void Cmd::setCmdName(string cmdName1)
{
     cmdName=cmdName1;
}

string Cmd::getCmdName()
{
     return cmdName;
}

void Cmd::setCmdNr(int cmdNr1)
{
     cmdNr=cmdNr1;
}

int Cmd::getCmdNr()
{
     return cmdNr;
}
