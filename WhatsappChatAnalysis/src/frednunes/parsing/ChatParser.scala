package frednunes.parsing

import scala.io.Source

case class ChatParser () { //case class: the constructor is specified after the class name
  
  // Regular expression to parse a WhatsApp message.
  val parseExpression = """(.+?), (.+?) - (.+?):(.+)""".r
  
  def read (filePath: String) : List[String] = {
    /** Reads a WhatsApp chat file into a list of strings. */
    
    return Source.fromFile(filePath, "UTF-8").getLines.toList 
  }
  
  
  def parse (lines: List[String]) : List[Map[String, String]] = {
    /** Parses a list of messages
     *  Input:
     *  	List of strings corresponding to WhatsApp messages
     *  Output:
     *  	List of lists each containing the parsed groups.  
     */

    val parsedLines : List[Map[String, String]] = lines.collect(x => x match {
      case parseExpression(date, time, name, msg) => Map(
          "date" -> date,
          "time" -> time,
          "name" -> name,
          "msg" -> msg)
      })
     
    return parsedLines
  }
  
}