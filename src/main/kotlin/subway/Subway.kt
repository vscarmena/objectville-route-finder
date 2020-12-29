package subway

import org.slf4j.LoggerFactory
import java.util.*
import kotlin.collections.HashMap

data class Subway(
    var stations: MutableList<Station>,
    var connections: MutableList<Connection>,
    var network: MutableMap<Station, MutableList<Station>>
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    constructor() : this(mutableListOf<Station>(), mutableListOf<Connection>(), HashMap())

    fun addStation(stationName: String) {
        if (!hasStation(stationName)) {
            stations.add(Station(stationName))
        }
    }

    fun hasStation(stationName: String): Boolean {
        return stations.contains(Station(stationName))
    }

    fun addConnection(station1Name: String, station2Name: String, lineName: String) {
        if (hasStation(station1Name) && hasStation(station2Name)) {
            val station1 = Station(station1Name)
            val station2 = Station(station2Name)
            connections.add(Connection(Station(station1Name), Station(station2Name), lineName))
            connections.add(Connection(Station(station2Name), Station(station1Name), lineName))
            addToNetwork(station1, station2)
            addToNetwork(station2, station1)
        } else {
            throw RuntimeException("Invalid connection!")
        }
    }

    fun hasConnection(station1Name: String, station2Name: String, lineName: String): Boolean {
        val station1 = Station(station1Name)
        val station2 = Station(station2Name)
        for (connection in connections) {
            if (connection.lineName.equals(lineName, true)) {
                if (connection.station1 == station1 && connection.station2 == station2) {
                    return true
                }
            }
        }
        return false
    }

    private fun addToNetwork(station1: Station, station2: Station) {
        if (network.containsKey(station1)) {
            var connectingStations: MutableList<Station> = network.getValue(station1)
            if (!connectingStations.contains(station2)) {
                connectingStations.add(station2)
            }
        } else {
            var connectingStation = LinkedList<Station>()
            connectingStation.add(station2)
            network[station1] = connectingStation
        }
    }

    fun getDirections(startStationName: String, endStationName: String): List<Connection> {
        if (!this.hasStation(startStationName) || !this.hasStation(endStationName)) {
            throw RuntimeException("Stations entered do not exist on this subway")
        }
        val startStation = Station(startStationName)
        val endStation = Station(endStationName)
        var route = LinkedList<Connection>()
        var reachableStations = LinkedList<Station>()
        var previousStations = HashMap<Station, Station>()
        var neighbors: MutableList<Station>? = network[startStation]


        for (station in neighbors!!) {
            if (station == endStation) {
                route.add(getConnection(startStation, endStation))
                return route
            } else {
                reachableStations.add(station)
                previousStations[station] = startStation
            }
        }

        var nextStations = LinkedList<Station>()
        nextStations.addAll(neighbors)
        var currentStation = startStation

        searchLoop@ for (station in stations) {
            var tmpStations = LinkedList<Station>()
            for (nextStation in nextStations) {
                reachableStations.add(nextStation)
                currentStation = nextStation
                var currentNeighbors = network[currentStation]
                for (currentNeighbor in currentNeighbors!!) {
                    if (currentNeighbor == endStation) {
                        reachableStations.add(currentNeighbor)
                        previousStations[currentNeighbor] = currentStation
                        break@searchLoop
                    } else if (!reachableStations.contains(currentNeighbor)) {
                        reachableStations.add(currentNeighbor)
                        tmpStations.add(currentNeighbor)
                        previousStations[currentNeighbor] = currentStation

                    }
                }
            }
            nextStations = tmpStations
        }

        var keepLooping: Boolean = true
        var keyStation = endStation
        var station: Station

        while (keepLooping) {
            station = previousStations[keyStation]!!
            logger.info("added route -> $station - $keyStation")
            route.add(0, getConnection(station, keyStation))
            if (startStation == station) {
                keepLooping = false
            }
            keyStation = station
        }

        logger.info(route.toString())
        return route
    }

    fun getConnection(station1: Station, station2: Station): Connection {
        return connections.stream()
            .filter { connection -> connection.station1 == station1 && connection.station2 == station2 }
            .findFirst().orElseThrow { RuntimeException("Connection not found") }

    }

}