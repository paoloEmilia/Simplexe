import scala.collection.mutable.ListBuffer

object ProcessSimplexe extends App {

  def processSimplexe(_dictionary: ListBuffer[ListBuffer[Double]] ): ListBuffer[ListBuffer[Double]] = {

    // Loop ...
    if(_dictionary.last.exists( x => x < 0 )) {

      // Find the column pivot
      val p = _dictionary.last
      val m = p.min
      val colPivot = p.indexOf(m)

      // Find the row pivot
      val pivot = for {
        l <- 0 until _dictionary.length - 1
        u = _dictionary(l).length - 1
        p = _dictionary(l)(u) / _dictionary(l)(colPivot)
      } yield p

      // Find the pivot value
      val rowPivot = pivot.indexOf(pivot.min)
      val valPivot = (_dictionary(rowPivot)(colPivot)).toDouble

      // Pivotage Gauss/Jordan
      val firstList = ListBuffer[ListBuffer[Double]]()

      for (i <- 0 until _dictionary.length) {
        val valeurPivot = (_dictionary(i)(colPivot)).toDouble / (_dictionary(rowPivot)(colPivot)).toDouble
        val secondList = ListBuffer[Double]()

        for (j <- 0 until _dictionary(i).length) {
          val cell = (_dictionary(i)(j)).toDouble
          var newValue: Double = 0.0;
          if (i != rowPivot) {
            newValue = (cell - (valeurPivot * _dictionary(rowPivot)(j)))
          } else {
            newValue = (cell / valPivot);
          }
          secondList += newValue
        }
        firstList += secondList
      }

      // ... until the best solution was not found
      processSimplexe(firstList)

    } else {

      // Otherwise return the best solution, the algorithm end
      _dictionary
    }

  }

  // ----------------------------------------------------------------------------------
  // --- EXAMPLE 1 --------------------------------------------------------------------
  // ----------------------------------------------------------------------------------

//  val dictionary1: ListBuffer[ListBuffer[Double]] =
//  ListBuffer(
//    ListBuffer( 420,  30,   1,  0,  46200 ),  // product (X)
//    ListBuffer( 30,   300,  0,  1,  45000 ),  // product (Y)
//    ListBuffer( -2,   -3,   0,  0,  0     )   // objective function to maximize (P)
//  )

  //val bestSolution1 = processSimplexe(dictionary1)

  //println(bestSolution1)

  // ListBuffer(1.0, 0.0, 0.002398081534772182,   -2.3980815347721823E-4,   100.0,
  //            0.0, 1.0, -2.3980815347721823E-4, 0.0033573141486810554,    140.0,
  //            0.0, 0.0, 0.00407673860911271,    0.009592326139088728,     620.0)

  // It means that we have to produce 100 of X and 140 of Y for a benefit P = 620


  // ----------------------------------------------------------------------------------
  // --- EXAMPLE 2 --------------------------------------------------------------------
  // ----------------------------------------------------------------------------------
  val dictionary2: ListBuffer[ListBuffer[Double]] =
    ListBuffer(
      ListBuffer( 1,  2,   1,   1,   0,  0,  300 ),  // product (X)
      ListBuffer( 2,  1,   1,   0,   1,  0,  320 ),  // product (Y)
      ListBuffer( 1,  2,   3,   0,   0,  1,  360 ),  // product (Z)
      ListBuffer(-4, -5,  -6,   0,   0,  0,  0  )    // objective function to maximize (P)
    )

  val bestSolution2 = processSimplexe(dictionary2)

  println(bestSolution2)

//  ListBuffer(ListBuffer(0.0, 1.0, 0.0, 0.8333333333333333, -0.3333333333333333, -0.16666666666666663,
//    83.33333333333331), ListBuffer(1.0, 0.0, 0.0, -0.16666666666666666, 0.6666666666666666, -0.16666666666666666,
//    103.33333333333334), ListBuffer(0.0, 0.0, 1.0, -0.4999999999999999, -2.7755575615628914E-17, 0.49999999999999994,
//    30.000000000000014), ListBuffer(0.0, 0.0, 0.0, 0.4999999999999999, 1.0, 1.5000000000000002,
//    1010.0))

  // It means that we have to produce 33.33 of X, 103 of Y and 30 for Z for a benefit P = 1010

}
