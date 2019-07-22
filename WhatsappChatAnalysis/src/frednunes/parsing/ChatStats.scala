package frednunes.parsing

case class ChatStats () {
  
    def countMessagesByUser (lines: List[Map[String, String]]) : Map[Option[String], Int] = {
      /**
       * Counts the number of messages by user.
       */
      
      val groupedMessages = lines.groupBy(_.get("name")).mapValues(_.length) 
      
      return groupedMessages
    }
}