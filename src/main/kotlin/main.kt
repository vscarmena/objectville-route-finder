import loader.SubwayLoader
import java.io.File

fun main(args: Array<String>) {
    println("-------------- OBJECTVILLE ROUTE FINDER ------------------")
    val subwayLoader = SubwayLoader()
    subwayLoader.loadFromFile(File("src/main/resources/ObjectvilleSubway.txt"))
}