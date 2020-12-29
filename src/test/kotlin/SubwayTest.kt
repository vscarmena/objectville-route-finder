import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import subway.Connection
import subway.Station
import subway.Subway

class SubwayTest {

    private val subway = Subway()

    @Test
    fun `should add stations to subway`() {
        subway.addStation(AJAX_STATION.name)
        Assertions.assertTrue(subway.hasStation(AJAX_STATION.name))
        Assertions.assertEquals(1, subway.stations.size)
    }

    @Test
    fun `should add connection to subway`() {
        subway.addStation(AJAX_STATION.name)
        subway.addStation(BOARDS_STATION.name)
        subway.addConnection(AJAX_STATION.name, BOARDS_STATION.name, BETA_LINE)
        Assertions.assertTrue(subway.hasConnection(AJAX_STATION.name, BOARDS_STATION.name, BETA_LINE))
        Assertions.assertTrue(subway.hasConnection(BOARDS_STATION.name, AJAX_STATION.name, BETA_LINE))
        Assertions.assertEquals(2, subway.connections.size)
    }

    @Test
    fun `should add connection stations to subway network`() {
        subway.addStation(AJAX_STATION.name)
        subway.addStation(BOARDS_STATION.name)
        subway.addStation(UML_STATION.name)
        subway.addConnection(AJAX_STATION.name, BOARDS_STATION.name, BETA_LINE)
        subway.addConnection(BOARDS_STATION.name, UML_STATION.name, BETA_LINE)
        Assertions.assertEquals(3, subway.network.size)
        Assertions.assertEquals(2, subway.network[BOARDS_STATION]?.size)
        Assertions.assertTrue(subway.network[BOARDS_STATION]?.contains(UML_STATION)!!)
        Assertions.assertTrue(subway.network[BOARDS_STATION]?.contains(AJAX_STATION)!!)
    }

    @Test
    fun `should get a connection`() {
        subway.addStation(AJAX_STATION.name)
        subway.addStation(BOARDS_STATION.name)
        subway.addConnection(AJAX_STATION.name, BOARDS_STATION.name, BETA_LINE)
        val connection = subway.getConnection(AJAX_STATION, BOARDS_STATION)
        Assertions.assertNotNull(connection)
        Assertions.assertEquals(BETA_LINE, connection.lineName)
    }

    @Test
    fun `should get directions between two existing stations`() {
        subway.addStation(AJAX_STATION.name)
        subway.addStation(BOARDS_STATION.name)
        subway.addStation(UML_STATION.name)
        subway.addConnection(AJAX_STATION.name, BOARDS_STATION.name, BETA_LINE)
        subway.addConnection(BOARDS_STATION.name, UML_STATION.name, BETA_LINE)
        val connections: List<Connection> = subway.getDirections(AJAX_STATION.name, UML_STATION.name)
        Assertions.assertEquals(2, connections.size)
        Assertions.assertEquals(AJAX_STATION, connections[0].station1)
        Assertions.assertEquals(BOARDS_STATION, connections[0].station2)
        Assertions.assertEquals(BOARDS_STATION, connections[1].station1)
        Assertions.assertEquals(UML_STATION, connections[1].station2)
    }

    companion object {
        private val AJAX_STATION = Station("Ajax Rapids")
        private val BOARDS_STATION = Station("Boards 'R' Us")
        private val UML_STATION = Station("UML Walk")
        private const val BETA_LINE = "Beta Line"
    }
}