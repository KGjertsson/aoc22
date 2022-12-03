package day2

import java.io.File

data class GameValue(val played: String, val game: String)

class Solution {

    // A = ROCK
    // B = PAPER
    // C = SCISSORS

    // X = ROCK
    // Y = PAPER
    // Z = SCISSORS

    private val played = mapOf("X" to 1, "Y" to 2, "Z" to 3)
    private val game = mapOf(
        "AX" to 3,
        "AY" to 6,
        "AZ" to 0,
        "BX" to 0,
        "BY" to 3,
        "BZ" to 6,
        "CX" to 6,
        "CY" to 0,
        "CZ" to 3
    )

    private val secondGame = mapOf(
        "X" to mapOf(
            "A" to 3,
            "B" to 1,
            "C" to 2
        ),
        "Y" to mapOf(
            "A" to 1,
            "B" to 2,
            "C" to 3
        ),
        "Z" to mapOf(
            "A" to 2,
            "B" to 3,
            "C" to 1
        )
    )
    private val shouldScore = mapOf(
        "X" to 0,
        "Y" to 3,
        "Z" to 6
    )

    fun solveFirst(): Int {
        return File("src/main/kotlin/day2/input.txt").readLines()
            .map { it.split(" ") }
            .map { GameValue(it[1], it[0] + it[1]) }
            .sumOf { played[it.played]!! + game[it.game]!! }

    }

    fun solveSecond(): Int {
        return File("src/main/kotlin/day2/input.txt").readLines()
            .map { it.split(" ") }
            .sumOf { shouldScore[it[1]]!! + secondGame[it[1]]!![it[0]]!! }
    }

}