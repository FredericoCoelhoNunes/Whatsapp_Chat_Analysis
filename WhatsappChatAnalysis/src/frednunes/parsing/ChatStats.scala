package frednunes.parsing

case class ChatStats () {
    
    val splitPattern = """\W""" // used to split messages and create the vocabulary count
    
    val urlPattern = """www\..+.com[^ ]*""".r  // used to find and remove URLs
    
    
    def groupMessagesByKey (messages: List[Map[String, String]], key: String) : Map[String, List[Map[String, String]]] = {
      /** Groups the messages by a given key (e.g. user name) */
      
      return messages.groupBy(_(key))
    }
  
    
    def countMessagesByKey (groupedMessages: Map[String, List[Map[String, String]]]) : Map[String, Int] = {
      /**
       * Counts the number of messages in each group (e.g. messages grouped by user "name").
       */
      
      val countedMessages = groupedMessages.mapValues(_.length) 
      
      return countedMessages 
    }
    
    
    def getVocabularyByKey (groupedMessages: Map[String, List[Map[String, String]]], n: Int) : Map[String, Map[String, Int]] = {
       /**
       * Creates a vocabulary of n-grams for each group of messages (e.g. messages grouped by user "name").
       * 
       * Returns: a list of words used by each user and their count.
       */
      
      val vocabularyByKey = groupedMessages
        .mapValues(
          _.map( // extract messages, lower case, split and trim each word
            _("msg")
            .toLowerCase))
        .mapValues(
          _.map( x => urlPattern.replaceAllIn(x, " "))) // removing hyperlinks
        .mapValues(
            _.map(
              _.split(splitPattern)
              .filter(_.nonEmpty)
              .map(_.trim) // here each value is a list of lists of words
            .sliding(n) // here we create the n-grams (Sublists) 
            .toList
            .filter(_.size >= n) // and filter: n-grams < n, websites
            .map(_.mkString(" "))) // and join them
          .flatten // list of all words by each user.
          .foldLeft(scala.collection.immutable.SortedMap[String,Int]().withDefaultValue(0)) {
          (currentMap, word) => currentMap + (word -> (currentMap(word) + 1))
        }) // count words
       
      return vocabularyByKey  
    }
}