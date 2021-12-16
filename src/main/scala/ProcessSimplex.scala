/** ******************************************************************************************************
 *   AUTHOR:   Paolo Caveduri
 *
 *   FILENAME: ProcessSimplex.scala
 *
 *   PURPOSE:  This program aims to solve the Simplex algorithm
 *   -------   The problem proposed must be PRIMAL because it is an objective function to
 *             maximize. To minimize a cost, the function objective must be DUAL and requires a
 *             preliminary transformation to propose a new canonical form.
 *
 *   VERSION:  0.1
 *
 *   DATE:     14/12/2021
 *
 ** ******************************************************************************************************/
import scala.collection.mutable.ListBuffer

final class ProcessSimplex(_dictionary: ListBuffer[ListBuffer[Double]], _debugMode: Boolean) {
  //
  // Instance variables
  private val dico:ListBuffer[ListBuffer[Double]] = _dictionary
  private val debugMode: Boolean = _debugMode
  //
  // Verify data and the format of the structure before to launch the algorithm
  def launchSimplex: Any = {
    val _simplexe = this.isLauncheAble match {
      case true => this.resolveSimplex(this.dico)
      case false => throw new Exception("The dictionary contains a wrong format or one entry of the objective function is positive .... ")
    }
    // Return the solution
    _simplexe
  }
  //
  // Verify here the conditions and requirements before to launch the algorithm
  def isLauncheAble: Boolean = {
    !this.isObjectiveFunction(this.dico,">") && this.isWellFormatted && !this.dico.isEmpty
  }
  //
  // Verify is the structure ListBuffer[ListBuffer[Double]] data format
  def isWellFormatted: Boolean = {
    this.dico match {
      case l: ListBuffer[ListBuffer[Double]] => true
      case _ => false
    }
  }
  //
  // To know if the best solution was not reached
  def isObjectiveFunction(_dictionary: ListBuffer[ListBuffer[Double]], operator: String ): Boolean = {
    operator match {
      case "<" => this.getObjectiveFunction(_dictionary).exists( x => x < 0 )
      case ">" => this.getObjectiveFunction(_dictionary).exists( x => x > 0 ) // To test if one of the entries is not positive
      case _ => false
    }
  }
  //
  // Return the objective function
  def getObjectiveFunction(_dictionary: ListBuffer[ListBuffer[Double]]): ListBuffer[Double] = {
    _dictionary.last
  }
  //
  // Get the column pivot
  def getColumnPivot(_dictionary: ListBuffer[ListBuffer[Double]]): Int = {
    val p = this.getObjectiveFunction(_dictionary)
    val m = p.min
    p.indexOf(m)
  }
  //
  // Get the row pivot
  def getRowPivot(_dictionary: ListBuffer[ListBuffer[Double]]): Int = {
    val pivot = for {
      l <- 0 until _dictionary.length - 1
      u = _dictionary(l).length - 1
      p = _dictionary(l)(u) / _dictionary(l)(this.getColumnPivot(_dictionary))
    } yield p
    pivot.indexOf(pivot.min)
  }
  //
  // Swiveling Gauss/Jordan
  def swiveling( _dictionary: ListBuffer[ListBuffer[Double]], colPivot:Int, rowPivot: Int) : ListBuffer[ListBuffer[Double]] = {
    // to avoid division by 0
    assert(_dictionary(rowPivot)(colPivot) > 0)
    val valPivot = _dictionary(rowPivot)(colPivot)
    val firstList = ListBuffer[ListBuffer[Double]]()

    for (i <- 0 until _dictionary.length) {
      val valeurPivot = _dictionary(i)(colPivot) / _dictionary(rowPivot)(colPivot)
      val secondList = ListBuffer[Double]()
      for (j <- 0 until _dictionary(i).length) {
        val cell = (_dictionary(i)(j)).toDouble
        var newValue: Double = 0.0;
        if (i != rowPivot) {
          newValue = (cell - (valeurPivot * _dictionary(rowPivot)(j)))
        } else {
          newValue = (cell / valPivot);
        }
        //
        // build the column list
        secondList += newValue
      }
      firstList += secondList
    }

    firstList

  }
  //
  // Solve recursively the Simplex algorithm
  private def resolveSimplex(_dictionary: ListBuffer[ListBuffer[Double]]): ListBuffer[ListBuffer[Double]] = {
    //
    // Trace the intermediates values when debugging
    if(this.debugMode) {
      this.outputPrint(_dictionary)
    }
    //
    // While the best solution was not found
    this.isObjectiveFunction(_dictionary,"<") match {
      case true =>
        this.resolveSimplex(this.swiveling(_dictionary, this.getColumnPivot(_dictionary), this.getRowPivot(_dictionary)))
      case false =>  _dictionary // The best solution has been found
    } // end match
  }
  //
  // Print the raw result
  private def outputPrint(solutionList :ListBuffer[ListBuffer[Double]]): Unit = {
    println('-'.toString() * 100)
    for { i <- 0 until solutionList.length } { println(solutionList(i)) }
  }

}
