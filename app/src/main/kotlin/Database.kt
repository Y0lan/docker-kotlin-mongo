import com.mongodb.MongoException
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.bson.BsonDocument
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

class Database {
    val HOST = "172.20.69.2"
    val PORT = "27017"
    val STUDENT_TO_ADD = 10

    fun connect() : MongoDatabase? {
        try {
            println("connecting to database...")
            val client : MongoClient = KMongo.createClient("mongodb://$HOST:$PORT")
            print(client)
            return client.getDatabase("demo")
        } catch (error: MongoException) {
            println("error, could not connect to the mongodb database")
            println(error.message)
        }
        return null
    }

    fun fill(database: MongoDatabase?){
        print("\n\n##############################" +
                "\nfilling database with $STUDENT_TO_ADD students" +
                "\n##############################\n")
        val names : ArrayList<String> = arrayListOf<String>("Thomas", "Botan", "Yolan",
            "Isabelle", "Caroline", "Julie", "Renard", "Lucie", "Tom", "Pascal", "Thomas", "Eliza", "Carol", "Damso", "Napoleine", "Dowi")
        for(i in (1..STUDENT_TO_ADD)) {
            val student : Student = Student(names.random(), (1..100).random())
            database?.
            getCollection<Student>("student")?.
            insertOne(student)
            println("$i - insert student: $student")
        }

    }

    fun readDataToConsole(database: MongoDatabase?){
        print("\n\n##############################" +
                "\nreading all students from database" +
                "\n##############################\n")
        val col = database?.getCollection<Student>("student")
        col?.find()?.forEach {
            println("there is a student named ${it.name} with the age of ${it.age}")
        }
    }

    fun cleanDatabase(database: MongoDatabase?){
        print("\n\n##############################" +
                "\ncleaning database from all students" +
                "\n##############################\n")
        val col = database?.getCollection<Student>("student")?.deleteMany(BsonDocument())
        println("deleted ${col?.deletedCount} students from database")
    }
}