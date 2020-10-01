import com.mongodb.client.MongoDatabase
import org.litote.kmongo.*
fun main(args: Array<String>) {
    val database: Database = Database()
    val client : MongoDatabase? = database.connect()
    database.fill(client)
    database.readDataToConsole(client)
    database.cleanDatabase(client)
}