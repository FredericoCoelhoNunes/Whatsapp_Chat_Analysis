package frednunes.parsing

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(84); 
  println("Welcome to the Scala worksheet");$skip(102); 
  
	val x : List[Map[String, String]] = List(Map("a"->"b"),Map("a"->"b"),Map("c"->"d"),Map("e"->"f"));System.out.println("""x  : List[Map[String,String]] = """ + $show(x ));$skip(24); 
 	val y = x(0).get("a");System.out.println("""y  : Option[String] = """ + $show(y ));$skip(41); 
	
	val z = List("abc aaa", "def", "hij");System.out.println("""z  : List[String] = """ + $show(z ));$skip(21); 
	
	val k = z.flatten;System.out.println("""k  : List[Char] = """ + $show(k ));$skip(31); 
	
	val var1 : Float = 2.34566f;System.out.println("""var1  : Float = """ + $show(var1 ));$skip(58); 
	
	println(f"this is a formatted float: $var1%.2f! See?");$skip(27); 
	
	val splitPat = """\W""";System.out.println("""splitPat  : String = """ + $show(splitPat ));$skip(175); 
	val str1 = List("Hi, I'm fred - how are you doin? I truly, honestly hope you are doing well.", "Hi, I'm fred - how are you doin? I truly, honestly hope you are doing well.");System.out.println("""str1  : List[String] = """ + $show(str1 ));$skip(40); 
	val str2 = str1.map(_.split(splitPat));System.out.println("""str2  : List[Array[String]] = """ + $show(str2 ));$skip(158); 
                                                  
  val var3 : List[String] = List("fred", "tom","fred", "tom", "fred", "tom", "fred", "tom", "fred", "tom");System.out.println("""var3  : List[String] = """ + $show(var3 ));$skip(196); 
  //val var4 = var3.foldLeft(Map[String,Int]() withDefaultValue 0){(m,x) => m + (x -> (1 + m(x)))}
  val var4 = var3.foldLeft(Map[String,Int]() withDefaultValue 0){(m,x) => m + (x -> (1 + m(x)))};System.out.println("""var4  : scala.collection.immutable.Map[String,Int] = """ + $show(var4 ));$skip(56); 
  
  val ttt = Map('a'-> 2, 'b'->1).withDefaultValue(0);System.out.println("""ttt  : scala.collection.immutable.Map[Char,Int] = """ + $show(ttt ));$skip(33); 
	val ty = ttt.toSeq.sortBy(_._1);System.out.println("""ty  : Seq[(Char, Int)] = """ + $show(ty ));$skip(20); 
	val ttt2 = (1,2,3);System.out.println("""ttt2  : (Int, Int, Int) = """ + $show(ttt2 ));$skip(19); 

	println(ttt2._1)}
}
