import Array._

object pl1 {

    def main( args: Array[String] ) : Unit  = {
  
        var _simpleMatrix = ofDim[Double](3,5)

        _simpleMatrix(0)(0) = 420d
        _simpleMatrix(1)(0) = 30d
        _simpleMatrix(2)(0) = -2d

        _simpleMatrix(0)(1) = 30d
        _simpleMatrix(1)(1) = 300d
        _simpleMatrix(2)(1) = -3d

        _simpleMatrix(0)(2) = 1d
        _simpleMatrix(1)(2) = 0d
        _simpleMatrix(2)(2) = 0d

        _simpleMatrix(0)(3) = 0d
        _simpleMatrix(1)(3) = 1d
        _simpleMatrix(2)(3) = 0d
        
        _simpleMatrix(0)(4) = 46200d
        _simpleMatrix(1)(4) = 45000d
        _simpleMatrix(2)(4) = 0d        

        val s = new Simplexe(_simpleMatrix);
        s.process()
        println(s.getResult())
        
        
    }


}

