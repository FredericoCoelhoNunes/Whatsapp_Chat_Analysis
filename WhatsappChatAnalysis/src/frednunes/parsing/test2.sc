package frednunes.parsing

object test2 {
	val p = """www\..+.com[^ ]*""".r          //> p  : scala.util.matching.Regex = www\..+.com[^ ]*
	
  val x = "www.reddit.com /r/eyebleach"           //> x  : String = www.reddit.com /r/eyebleach
  
  val z = p.findFirstIn(x)                        //> z  : Option[String] = Some(www.reddit.com)
}