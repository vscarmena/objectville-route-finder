import loader.SubwayLoader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import subway.Connection
import subway.Station
import subway.Subway
import java.io.File

class SubwayLoaderTest {

    private val subwayLoader = SubwayLoader()
    private var subway = Subway(mutableListOf<Station>(), mutableListOf<Connection>(), mutableMapOf<Station, MutableList<Station>>())

    @BeforeEach
    fun loadSubwayFromFile() {
        subway = subwayLoader.loadFromFile(File(PATH_FILE))
    }

    @Test
    fun `should add stations to subway`() {
        Assertions.assertEquals(6, subway.stations.size)
        Assertions.assertTrue(subway.hasStation("Ajax Rapids"))
        Assertions.assertTrue(subway.hasStation("UML Walk"))
    }

    @Test
    fun `should add connections to subway`() {
        Assertions.assertEquals(10, subway.connections.size)
        Assertions.assertTrue(subway.hasConnection("Ajax Rapids", "Boards 'R' Us", "Beta Line"))
        Assertions.assertTrue(subway.hasConnection("OOA&D Oval", "Head First Lounge", "Gamma Line"))
    }

    companion object {
        const val PATH_FILE = "src/test/resources/ObjectvilleSubway.txt"
    }
}