package day01

import java.io.File

class Solution {

    fun solve(): List<Int> =
        File("src/main/kotlin/day01/input.txt").readText()
            .split("\n\n")
            .map { it.split("\n") }
            .map { group -> group.filter { it != "" }.sumOf { it.toInt() } }
            .sortedDescending()
            .take(3)

}
