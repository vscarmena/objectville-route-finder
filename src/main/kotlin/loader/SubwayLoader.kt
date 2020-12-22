package loader

import org.slf4j.LoggerFactory
import subway.Subway
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class SubwayLoader {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val subway: Subway = Subway()

    fun loadFromFile(subwayFile: File): Subway {
        logger.info("###### LOADING FILE : $subwayFile")
        val reader = BufferedReader(FileReader(subwayFile))
        loadStations(subway, reader)

        val iterator = reader.lineSequence().iterator()
        var lineName: String = iterator.next()
        while (lineName.isNotEmpty()) {
            loadLine(subway, iterator, lineName)
            lineName = if (iterator.hasNext()) iterator.next() else ""
        }
        logger.info("SUMMARY: stations -> ${subway.stations.size}, connections -> ${subway.connections.size}")
        logger.info("###### FILE LOADED")
        return subway
    }

    private fun loadStations(subway: Subway, reader: BufferedReader) {
        var currentLine: String = reader.readLine()
        while (currentLine.isNotEmpty()) {
            subway.addStation(currentLine)
            logger.debug("station added: $currentLine")
            currentLine = reader.readLine()
        }
    }

    private fun loadLine(subway: Subway, iterator: Iterator<String>, lineName: String) {
        var station1Name: String = iterator.next()
        var station2Name: String = iterator.next()
        while (station2Name.isNotEmpty()) {
            subway.addConnection(station1Name, station2Name, lineName)
            logger.debug("connection added: $station1Name -> $station2Name in line: $lineName")
            station1Name = station2Name
            station2Name = if (iterator.hasNext()) iterator.next() else ""
        }
    }
}
