import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ListBuffer

class SimplexSuite extends AnyFunSuite{
  //
  val dictionary: ListBuffer[ListBuffer[Double]] =
    ListBuffer(
      ListBuffer( 420, 30,   1,  0,  46200 ),  // product (X)
      ListBuffer( 30,   300,  0,  1,  45000 ),  // product (Y)
      ListBuffer( -2,   -3,   0,  0,  0     )   // objective function to maximize (P)
    )
  //
  val ps = new ProcessSimplex(dictionary, true)
  //
  test("the dictionary should be well formatted") {
    assert(ps.isWellFormatted == true)
  }
  //
  test("the objective function should be negative") {
    val of: ListBuffer[Double] =  ListBuffer( -2, -3, 0, 0, 0 )
    assert(ps.getObjectiveFunction(dictionary) == of)
  }
  //
  test("the objective function should support negative operator before to start") {
    assert( ps.isObjectiveFunction(dictionary,"<") === true  )
  }
  //
  test("the objective function should support only positive or negative operators") {
    assert( ps.isObjectiveFunction(dictionary,"/") === false  )
  }
  //
  test("get the column pivot") {
    assert( ps.getColumnPivot(dictionary) === 1  )
  }
  //
  test("the row pivot should be") {
    assert( ps.getRowPivot(dictionary) === 1  )
  }

}
