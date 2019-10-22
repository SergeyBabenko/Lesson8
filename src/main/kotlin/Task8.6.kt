import kotlinx.coroutines.*

class Pet(private val nickname: String, var mealDuration: Long) {
    suspend fun eat() {
        delay(mealDuration)
        println("$nickname покушал")
    }
}

fun firstFeedingVariation(petsArray: Array<Pet>) {
    runBlocking {
        petsArray.forEach { it.eat() }
        println("Животные накормлены")
    }
}

suspend fun secondFeedingVariation(petsArray: Array<Pet>) {
    val isFeedingOver = GlobalScope.async {
        petsArray.forEach { it.eat() }
        true
    }
    println("Корм дан")
    if (isFeedingOver.await())
        println("Животные накормлены")
}

suspend fun main() {
    val petsArray = arrayOf(
        Pet("Жучка", 1500),
        Pet("Волчок", 2000),
        Pet("Рекс", 1000),
        Pet("Шарик", 3000),
        Pet("Дружок", 2500)
    )
    println("Первый вариант:")
    firstFeedingVariation(petsArray)
    println("\nВторой вариант:")
    secondFeedingVariation(petsArray)
}