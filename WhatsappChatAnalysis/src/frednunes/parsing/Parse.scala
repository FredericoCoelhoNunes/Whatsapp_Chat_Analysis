package frednunes.parsing

import scala.collection.immutable.ListMap

object Parse {
    
  def main (args:Array[String]) {
    
    // Initialising the parser and the stat calculator
    val chatParser = frednunes.parsing.ChatParser()   
    val chatStats = frednunes.parsing.ChatStats()
    
    // Reading the file (dropping first line since it's a boilerplate message)
    val fileContent = chatParser.read("D:\\Projects\\Whatsapp_chat_analysis\\_private\\chat.txt")
    val boilerPlate = fileContent.head
    val messages = fileContent.tail
    val numberOfMessages = messages.length
    
    // Parsing and filtering the messages (date, time, name, msg)
    val parsedMessages = chatParser.parse(messages)
    val numberOfParserMessages = parsedMessages.length

//    println(f"Failed to parse ${numberOfMessages-numberOfParserMessages} messages.")
    
    // Counting the number of messages by user
    val groupedMessages = chatStats.groupMessagesByKey(parsedMessages, "name")
    val msgCountByUser = chatStats.countMessagesByKey(groupedMessages)
//    for ((k,v) <- msgCountByUser) println(s"$k: $v messages")
    
    // Building vocabulary by user
    val vocabularyByKey = chatStats.getVocabularyByKey(groupedMessages, 3)
    val sortedVocab = vocabularyByKey.mapValues(_.toSeq.sortBy(_._2)) // descending order
    
    for ((k,v) <- sortedVocab) {
      println(s"$k")
      for ((w, c) <- v)
        println(s"\t$w: $c") }
    
//    println(sortedVocab("Mariana Fernandes"))
    
    // todo: ngrams, reply time, msg count over time 
  }
}