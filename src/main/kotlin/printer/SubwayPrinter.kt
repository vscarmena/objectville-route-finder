package printer

import org.slf4j.LoggerFactory
import subway.Connection

class SubwayPrinter {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun printDirections(route: List<Connection>) {
        var currentLine: String = route[0].lineName
        var previousLine: String = currentLine
        logger.info("Start out at ${route[0].station1.name}.")
        logger.info("Get on the $currentLine heading towards ${route[0].station2.name}")
        for (connection in route) {
            currentLine = connection.lineName
            if (currentLine == previousLine) {
                logger.info("Continue past ${connection.station1.name}")
            } else {
                logger.info("When you get to ${connection.station1.name} get off the $previousLine.")
                logger.info("Switch over to the $currentLine heading towards ${connection.station2.name}")
                previousLine = currentLine
            }
        }
        val lastConnection = route.size - 1
        logger.info("Get off at ${route[lastConnection].station2.name} and enjoy yourself!")
    }

}