package parsing

object Parse {

  def printOut (
      numberOfMessages: Int,
      numberOfParsedMessages: Int,
      msgCountByUser: Map[String, Int],
      sortedVocabs: List[Map[String, Seq[(String, Int)]]],
      ngramRange: List[Int],
      avgResponseTimesByUser: Map[String, Double]
      ) {
    
    /** Prints the results */    
    println("Message count by user: \n")
    for ((k,v) <- msgCountByUser) println(s"\t$k: $v messages")
    
    println(f"\nFailed to parse ${numberOfMessages-numberOfParsedMessages} messages.")
    
    println("\nAverage response times:\n")
    for ((name, respTime) <- avgResponseTimesByUser.toSeq) {
      println(f"\t$name: $respTime%.2f minutes.")
    }
    
    println("\nMost used n-grams:")
    for ((vocab, i) <- sortedVocabs.zipWithIndex) {
      println(s"\n\t[${ngramRange(i)}-grams]")
      for ((name,ngrams) <- vocab) {
        println(s"\n\t\t$name")
        for ((ngram, cnt) <- ngrams)
          println(s"\t\t\t$ngram: $cnt") }}  
    
  }
  
  def main (args:Array[String]) {
    
    // Initialising the parser and the stat calculator
    val chatParser = parsing.ChatParser()   
    val chatStats = parsing.ChatStats()
    
    // Reading the file (dropping first line since it's a boilerplate message)
    val fileContent = chatParser.read(args(0))
    
    val messages = fileContent.tail
    val numberOfMessages = messages.length
    
    // Parsing and filtering the messages (date, time, name, msg)
    val parsedMessages = chatParser.parse(messages)
    val numberOfParsedMessages = parsedMessages.length

    // Counting the number of messages by user
    val groupedMessages = chatStats.groupMessagesByKey(parsedMessages, "name")
    val msgCountByUser = chatStats.countMessagesByKey(groupedMessages)
    
    // Building ngram vocabularies by user (n = 2 to 4)
    val topN = 10 // keep only the top n ngrams for each ngram length
    val ngramRange = (2 to 5).toList
    val vocabsByKey = (for (n <- ngramRange) yield chatStats.getVocabularyByKey(groupedMessages, ngram_length=n)).toList
    val sortedVocabs = vocabsByKey.map(
        _.mapValues(
            _.toSeq.sortBy(-_._2).slice(0, topN))
       ) // descending order  
    
    // Getting the average response time by user
    val avgResponseTimesByUser = chatStats.getAvgResponseTimesByUser(parsedMessages, chatParser)
       
    // Printing
    printOut(      
      numberOfMessages,
      numberOfParsedMessages,
      msgCountByUser,
      sortedVocabs,
      ngramRange,
      avgResponseTimesByUser)
         
  }
}