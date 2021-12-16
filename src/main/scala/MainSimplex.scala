import scala.collection.mutable.ListBuffer

object MainSimplex  extends App {

  val dictionary: ListBuffer[ListBuffer[Double]] =
  ListBuffer(
    ListBuffer( 420, 30,   1,  0,  46200 ),   // product (X)
    ListBuffer( 30,   300,  0,  1,  45000 ),  // product (Y)
    ListBuffer( -2,   -3,   0,  0,  0     )   // objective function to maximize (P)
  )

  val ps = new ProcessSimplex(dictionary, false)
  println(ps.launchSimplex)
  

}

