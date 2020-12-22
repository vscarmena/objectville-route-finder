package subway

data class Subway(var stations: MutableList<Station>, var connections: MutableList<Connection>) {

    constructor() : this(mutableListOf<Station>(), mutableListOf<Connection>())

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
            connections.add(Connection(Station(station1Name), Station(station2Name), lineName))
            connections.add(Connection(Station(station2Name), Station(station1Name), lineName))
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

}