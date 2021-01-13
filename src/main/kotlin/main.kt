import loader.SubwayLoader
import printer.SubwayPrinter
import java.io.File

fun main(args: Array<String>) {
    println("-------------- OBJECTVILLE ROUTE FINDER ------------------")
    val subwayLoader = SubwayLoader()
    val subway = subwayLoader.loadFromFile(File("src/main/resources/ObjectvilleSubway.txt"))
    val route = subway.getDirections("CSS Center", "JSP Junction")
    val subwayPrinter = SubwayPrinter()
    subwayPrinter.printDirections(route)
}