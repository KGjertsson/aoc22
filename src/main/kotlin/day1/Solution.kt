package day1

import java.io.File

class Solution {

    fun solve(): List<Int> {
        return File("src/main/kotlin/one/input.txt").readText()
            .split("\n\n")
            .map { it.split("\n") }
            .map { group -> group.filter { it != "" }.sumOf { it.toInt() } }
            .sortedDescending()
            .take(3)
    }

}
