package frednunes.parsing

object test2 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(75); 
	val p = """www\..+.com[^ ]*""".r;System.out.println("""p  : scala.util.matching.Regex = """ + $show(p ));$skip(42); 
	
  val x = "www.reddit.com /r/eyebleach";System.out.println("""x  : String = """ + $show(x ));$skip(30); 
  
  val z = p.findFirstIn(x);System.out.println("""z  : Option[String] = """ + $show(z ))}
}
