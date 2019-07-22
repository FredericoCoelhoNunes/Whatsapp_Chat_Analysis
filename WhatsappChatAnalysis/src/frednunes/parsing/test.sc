package frednunes.parsing

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
	val x : List[Map[String, String]] = List(Map("a"->"b"),Map("a"->"b"),Map("c"->"d"),Map("e"->"f"))
                                                  //> x  : List[Map[String,String]] = List(Map(a -> b), Map(a -> b), Map(c -> d), 
                                                  //| Map(e -> f))
 	val y = x(0).get("a")                     //> y  : Option[String] = Some(b)
	
	
	
}