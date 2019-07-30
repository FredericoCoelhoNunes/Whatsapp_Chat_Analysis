package frednunes.parsing

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
	val x : List[Map[String, String]] = List(Map("a"->"b"),Map("a"->"b"),Map("c"->"d"),Map("e"->"f"))
                                                  //> x  : List[Map[String,String]] = List(Map(a -> b), Map(a -> b), Map(c -> d), 
                                                  //| Map(e -> f))
 	val y = x(0).get("a")                     //> y  : Option[String] = Some(b)
	
	val z = List("abc aaa", "def", "hij")     //> z  : List[String] = List(abc aaa, def, hij)
	
	val k = z.flatten                         //> k  : List[Char] = List(a, b, c,  , a, a, a, d, e, f, h, i, j)
	
	val var1 : Float = 2.34566f               //> var1  : Float = 2.34566
	
	println(f"this is a formatted float: $var1%.2f! See?")
                                                  //> this is a formatted float: 2.35! See?
	
	val splitPat = """\W"""                   //> splitPat  : String = \W
	val str1 = List("Hi, I'm fred - how are you doin? I truly, honestly hope you are doing well.", "Hi, I'm fred - how are you doin? I truly, honestly hope you are doing well.")
                                                  //> str1  : List[String] = List(Hi, I'm fred - how are you doin? I truly, honest
                                                  //| ly hope you are doing well., Hi, I'm fred - how are you doin? I truly, hones
                                                  //| tly hope you are doing well.)
	val str2 = str1.map(_.split(splitPat))    //> str2  : List[Array[String]] = List(Array(Hi, "", I, m, fred, "", "", how, ar
                                                  //| e, you, doin, "", I, truly, "", honestly, hope, you, are, doing, well), Arra
                                                  //| y(Hi, "", I, m, fred, "", "", how, are, you, doin, "", I, truly, "", honestl
                                                  //| y, hope, you, are, doing, well))
                                                  
  val var3 : List[String] = List("fred", "tom","fred", "tom", "fred", "tom", "fred", "tom", "fred", "tom")
                                                  //> var3  : List[String] = List(fred, tom, fred, tom, fred, tom, fred, tom, fred
                                                  //| , tom)
  //val var4 = var3.foldLeft(Map[String,Int]() withDefaultValue 0){(m,x) => m + (x -> (1 + m(x)))}
  val var4 = var3.foldLeft(Map[String,Int]() withDefaultValue 0){(m,x) => m + (x -> (1 + m(x)))}
                                                  //> var4  : scala.collection.immutable.Map[String,Int] = Map(fred -> 5, tom -> 5
                                                  //| )
  
  val ttt = Map('a'-> 2, 'b'->1).withDefaultValue(0)
                                                  //> ttt  : scala.collection.immutable.Map[Char,Int] = Map(a -> 2, b -> 1)
	val ty = ttt.toSeq.sortBy(_._1)           //> ty  : Seq[(Char, Int)] = Vector((a,2), (b,1))
	val ttt2 = (1,2,3)                        //> ttt2  : (Int, Int, Int) = (1,2,3)

	println(ttt2._1)                          //> 1
}