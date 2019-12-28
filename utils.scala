package paolo.caveduri.utils

import Array._

object StringUtils {
    implicit class StringImprovements(val s: String) {
        def increment = s.map(c => (c + 1).toChar)
        def decrement = s.map(c => (c - 1).toChar)
    }
}

object MathUtils {
    
    /* Verify the equality of two Double numbers
        according the precision : ie :
        val a = 0.3
        val b = 0.1 + 0.2
        println(~=(a, b, 0.000001)) */
    def ~=(x: Double, y: Double, precision: Double) = {
        if ((x - y).abs < precision) true else false
    }

    /* Build an adjacent matrix */
    def adjacentMatrix(dim:Int) = {

        var matrix = ofDim[Double](dim, dim)

        for {
            i <- 0 until dim
            j <- 0 until dim
        } {
            if( j == i ) {
                matrix(i)(j) = 1.toDouble
            } else {
                matrix(i)(j) = 0.toDouble
            }

        }

        matrix

    }

    /* Search the pivot for the column */
    def findColPivot(arr:Array[Double]):Int = {
        var _colPivot:Int = 0
        var _tmp:Double = 0.0
        for (i <- 0 until arr.length ) {
            //println(_tmp > arr(i))
            if( _tmp > arr(i) ) {
                _tmp = arr(i)
                _colPivot = i
            }
        }
        _colPivot
    }

   /* Search the pivot for the row */
    def findRowPivot(V:Array[Array[Double]],colPivot:Int):Int = {
        var _rowPivot = 0;
        var _valPivot:Double = 1000000.toDouble;
        for( k <- 0 until V.length ){
            if( V(k)(colPivot) > 0 ){
                var _tmp = V(k)(V(k).length-1) / V(k)(colPivot)
                if( _tmp < _valPivot  ){
                    _valPivot = _tmp;
                    _rowPivot = k;
                }  		
            }
        } 
        _rowPivot
    }

    /* Verify if it exists negative values related to 
    the entries of the objective function */
    def findNegative(arr:Array[Double]):Boolean = {
        var _return:Boolean = false
        for (i <- 0 until arr.length ) {
            if( arr(i) < 0.toDouble ) {
                _return = true
            }
        }
        _return
    }

}