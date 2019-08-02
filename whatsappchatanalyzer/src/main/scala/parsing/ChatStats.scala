package parsing

import scala.collection.mutable.ListBuffer
import java.text.Normalizer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

case class ChatStats () {
    
    val splitPattern = """[^\w'’]""" // used to split messages and create the vocabulary count
    val urlPattern = """http.+\.(com|it|pt)[^ ]*""".r  // used to find and remove URLs
    val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    
    def stripAccents(input: String) : String = {
      /** Strips accents from a string. */
      return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
    
    
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
    
    
    def getVocabularyByKey (groupedMessages: Map[String, List[Map[String, String]]], ngram_length: Int) : Map[String, Map[String, Int]] = {
       /**
       * Creates a vocabulary of n-grams for each group of messages (e.g. messages grouped by user "name").
       * 
       * Returns: a list of words used by each user and their count.
       */
      
      val vocabularyByKey = groupedMessages
        .mapValues(
          _.map( // extract messages, lower case, split and trim each word
            _("msg")
            .toLowerCase)
          .filter(!_.contains("media omitted"))
          .map( x => stripAccents(x))
          .map( x => urlPattern.replaceAllIn(x, " ")) // removing hyperlinks
          .map(
            _.split(splitPattern)
            .filter(_.nonEmpty)
            .map(_.trim) // here each value is a list of lists of words
            .sliding(ngram_length) // here we create the n-grams (Sublists) 
            .toList
            .filter(_.size >= ngram_length) // and filter: n-grams < n
            .map(_.mkString(" "))) // and join them
        .flatten // list of all ngrams by each user.
        .foldLeft(scala.collection.immutable.SortedMap[String,Int]().withDefaultValue(0)) {
          (currentMap, word) => currentMap + (word -> (currentMap(word) + 1))
        }) // filter by n-gram count
       
      return vocabularyByKey  
    }
    
    
    def getMsgDatetime(msg: Map[String, String]) : LocalDateTime = {
      /** Gets the date time of a message */
      val msgDateTime = msg("date") + " " + msg("time")
      return LocalDateTime.parse(msgDateTime, dateFormat)
    }
    
    
    def getTimediff(olderDate: LocalDateTime, newerDate: LocalDateTime) : Long = {
      /** Gets the difference in minutes between two dates. */
      val timeDiff =  olderDate.until(newerDate, ChronoUnit.MINUTES)
      return timeDiff
    }
    
    
    def getAvgResponseTimesByUser(parsedMessages: List[Map[String, String]], chatParser: ChatParser) : Map[String, Double] = {
      val datesAndMsgs = for (msg <- parsedMessages) yield (getMsgDatetime(msg), msg("name"))
    
      // Calculating average response time per day
      val participantNames = chatParser.getNames(parsedMessages)
      val responseTimes = participantNames.map(name => name -> ListBuffer[Double]()).toMap
      
      // Using variables because their value changes 
      var prevDate = datesAndMsgs.head._1
      var prevDay = prevDate.getDayOfMonth
      var prevName = datesAndMsgs.head._2
      
      for (((datetime, name), i) <- datesAndMsgs.tail.zipWithIndex) {
        
        // If we are in the same day (assuming the conversation restarts each day)  and the other person replies - add the response time to the cumulative list.
        if (name != prevName && datetime.getDayOfMonth == prevDay) {
          val responseTime = getTimediff(prevDate, datetime)
          responseTimes(name) += responseTime
        }
        
        prevDate = datetime
        prevDay = datetime.getDayOfMonth
        prevName = name
      }
      
      val avgResponseTimes = responseTimes.mapValues(resp => resp.sum / resp.length)
      
      return avgResponseTimes   
    }
    

}