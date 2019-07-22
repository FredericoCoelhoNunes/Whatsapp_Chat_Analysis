package frednunes.parsing

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(84); 
  println("Welcome to the Scala worksheet");$skip(102); 
  
	val x : List[Map[String, String]] = List(Map("a"->"b"),Map("a"->"b"),Map("c"->"d"),Map("e"->"f"));System.out.println("""x  : List[Map[String,String]] = """ + $show(x ));$skip(24); 
 	val y = x(0).get("a");System.out.println("""y  : Option[String] = """ + $show(y ))}
	
	
	
}
