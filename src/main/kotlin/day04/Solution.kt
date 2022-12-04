package day04

import java.io.File

data class Assignment(val lower: Int, val higher: Int)

class Solution {

    private fun overlapFirst(left: Assignment, right: Assignment): Boolean =
        left.lower <= right.lower && left.higher >= right.higher

    private fun overlapSecond(left: Assignment, right: Assignment): Boolean =
        left.lower >= right.lower && left.lower <= right.higher || left.higher >= right.lower && left.higher <= right.higher

    fun solveFirst(): Int =
        File("src/main/kotlin/day04/input.txt").readLines()
            .map { it.split(",") }
            .map { a -> Pair(a[0].split("-").map { it.toInt() }, a[1].split("-").map { it.toInt() }) }
            .map { Pair(Assignment(it.first[0], it.first[1]), Assignment(it.second[0], it.second[1])) }
            .filter { overlapFirst(it.first, it.second) || overlapFirst(it.second, it.first) }
            .size

    fun solveSecond(): Int =
        File("src/main/kotlin/day04/input.txt").readLines()
            .map { it.split(",") }
            .map { a -> Pair(a[0].split("-").map { it.toInt() }, a[1].split("-").map { it.toInt() }) }
            .map { Pair(Assignment(it.first[0], it.first[1]), Assignment(it.second[0], it.second[1])) }
            .filter { overlapSecond(it.first, it.second) || overlapSecond(it.second, it.first) }
            .size
}
