# Simplexe
This program aims to solve the simplexe algorithm
The problem proposed must be PRIMAL because it 's an objective function to
maximize. To minimize a cost, the function objective must be DUAL and requires a
preliminary transformation to propose a new canonical form. ie, the
following matrix format :

    420,	30,		1,		  0,	    46200           //First constraint (X1)
    30,	  300,	0,	    1,	    45000           //Second onstraint (X2)
    -2,	  -3,		0,	    0,		  0               //Objective function to maximize (P)
 
The program will produce : Map(P -> 620.0, X1 -> 100.0, X2 -> 140.0)
