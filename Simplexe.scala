/** ******************************************************************************************************
*   AUTHOR:   Paolo Caveduri
*  
*   FILENAME: Simplexe.scala
*  
*   PURPOSE:  This program aims to solve the simplexe algorithm
*   -------   The problem proposed must be PRIMAL because it 's an objective function to
*             maximize. To minimize a cost, the function objective must be DUAL and requires a
*             preliminary transformation to propose a new canonical form. ie, the
*             following matrix format :
*             420,	30,		1,		0,	    46200           //First constraint (X1)
*             30,	300,	0,	    1,	    45000           //Second onstraint (X2)
*             -2,	-3,		0,	    0,		0               //Objective function to maximize (P)
*    
*             The program will produce : Map(P -> 620.0, X1 -> 100.0, X2 -> 140.0)
*
*   VERSION:  0.1
*
*   DATE:     28/12/2019
*
** ******************************************************************************************************/
import paolo.caveduri.utils.MathUtils._
import scala.collection.mutable.Map
import Array._

class Simplexe ( aMatrix:Array[Array[Double]] ) {

    // CONSTANTS
    private val VHB = List("X1","X2","X3","X4","X5","X6","X7","X8","X9","X10")  // Variables out of the basis
    private val VB  = List("U1","U2","U3","U4","U5","U6","U7","U8","U9","U10")  // Variables of the basis
    private val _rows:Int = _matrix.length                                      // To get the number of rows
    private val _cols:Int = _matrix(0).asInstanceOf[Array[Double]].size         // To get the number of columns
    
    // VARIABLES
    private var _map = Map[String, Double]()                                    // To store the basis variables and the best solution for each variabl
    private var _colPivot = 0                                                   // To define wich variable enter in the basis
    private var _rowPivot = 0                                                   // To define the pivot row
    private var _valPivot:Double = 0d                                           // To store the value of the pivot
    private var _matrix:Array[Array[Double]] = aMatrix                          // The Simplexe matrix

    // PROCESS THE SIMPLEXE ALGORITHM
    def process():Unit = {


        // Start the Simplexe processing in order
        // to get the best solution, process until the
        // variables of the economic function are < 0
        while( findNegative(_matrix(_rows-1)) ) {

            // GET THE PIVOT and the variable to enter into the basis
            _colPivot = findColPivot(_matrix(_rows-1))
            _rowPivot = findRowPivot(_matrix,_colPivot)
            _valPivot = _matrix(_rowPivot)(_colPivot);

            // Memorize the variable wich enter in the basis
            _map += (VHB(_colPivot) -> _rowPivot.toDouble)

            // SWIVELING GAUSS/JORDAN (pivotage Gauss/Jordan)
            for( i <- 0 until _rows ){
                var valeurPivot = _matrix(i)(_colPivot)/_matrix(_rowPivot)(_colPivot);
                for( j <- 0 until _cols ){
                    if( i != _rowPivot ){
                        _matrix(i)(j) =  ( _matrix(i)(j) - ( valeurPivot * _matrix(_rowPivot)(j)));
                    } else {
                        _matrix(i)(j) =  ( _matrix(i)(j) / _valPivot );
                    }
                }
            }

        } //End while

        // Register the economic function value according
        // the last element of the matrix
        _map += ("P" -> (_rows - 1).toDouble)

        // Update values according the saved rows
        _map.keys.foreach{ i =>
            _map(i) = _matrix(_map(i).toInt)(_cols-1)
        }

    }   // end process

    // RETURN A MAP CONTAINING THE VARIABLES 
    // AND THEIRS BEST VALUES FOR EACH OF THEM
    def getResult():Map[String, Double] = {
        _map
    }


}