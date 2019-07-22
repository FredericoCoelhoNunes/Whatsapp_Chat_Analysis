package frednunes.parsing

object Parse {
    
  def main (args:Array[String]) {
    
    // Initialising the parser and the stat calculator
    val chatParser = frednunes.parsing.ChatParser()   
    val chatStats = frednunes.parsing.ChatStats()
    
    
    // Reading the file (dropping first line since it's a boilerplate message)
    val messages = chatParser.read("D:\\Projects\\Whatsapp_chat_analysis\\_private\\chat.txt").drop(1)
    val numberOfMessages = messages.length
    
    // Parsing and filtering the messages (date, time, name, msg)
    val parsedMessages = chatParser.parse(messages)
    val numberOfParserMessages = parsedMessages.length

    println(f"Failed to parse ${numberOfMessages-numberOfParserMessages} messages.")
    
    // Counting the number of messages by user
    val msgCountByUser = chatStats.countMessagesByUser(parsedMessages)
    
    // 
    for ((k,v) <- msgCountByUser) println(s"${k.get}: $v messages")

  }
}