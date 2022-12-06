package day06

import java.io.File

class Solution {

    fun solveFirst(): Int {
        val text = File("src/main/kotlin/day06/input.txt").readText().toCharArray()

        var nbrProcessed = 0;
        var code = emptyList<Char>()
        for (letter in text) {
            nbrProcessed += 1;
            if (code.size == 4) {
                code = code.takeLast(3) + letter
                if (code.toSet().size == 4) {
                    return nbrProcessed
                }
            } else {
                code = code + letter
            }
        }

        return nbrProcessed
    }

    fun solveSecond(): Int {
        val text = File("src/main/kotlin/day06/input.txt").readText().toCharArray()

        var nbrProcessed = 0;
        var code = emptyList<Char>()
        for (letter in text) {
            nbrProcessed += 1;
            if (code.size == 14) {
                code = code.takeLast(13) + letter
                if (code.toSet().size == 14) {
                    return nbrProcessed
                }
            } else {
                code = code + letter
            }
        }

        return nbrProcessed
    }

}
