# london-users

Sping Boot application that calls an API and returns all users who are listed as either living in London, or whose current long, lat coordinates are within 50 miles of London.

## Software used

london-users is written in Java 8 using Spring Boot and makes use of [Lombok](https://projectlombok.org/), [Jackson](https://github.com/FasterXML/jackson) and [Lucene](https://lucene.apache.org/core/8_3_0/core/org/apache/lucene/util/SloppyMath.html) libraries.

## Running the application locally

The source code of the application can be directly pulled into an IDE (e.g. IntelliJ, Eclipse) and run locally from inside there.

After the application is running, (assuming the application is running on localhost and port 8080) simply make a GET request to http://localhost:8080/v1/london-users. This will return all users either listed as being from London or whose longitude and latitude place them within 50 miles of London. 


# london-users
