# Simplexe
Simplexe.scala aims to solve the simplexe algorithm.

The problem proposed must be PRIMAL because it is an objective function to
maximize. 

To minimize a cost, the function objective must be DUAL and requires a
preliminary transformation to propose a new canonical form.

Example:
--------

The folowing program aims to maximize : P = 2X1 + 3X2

if 420X1 + 30X2 <= 46200 
   30X1 + 300X2 <= 45000

with X1, X2 >= 0


           
 420,  	30,  	1,    0,   46200          
 30,   300,  	0,    1,   45000          
 -2,    -3,	 	0 ,   0,       0          

 
The program will produce : Map(P -> 620.0, X1 -> 100.0, X2 -> 140.0)

It means that we have to produce 100 of X1 and 140 of X2 for a profit P = 620
