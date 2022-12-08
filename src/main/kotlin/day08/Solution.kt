package day08

import java.io.File

class Solution {

    private fun visibleAbove(input: List<List<Int>>): MutableList<MutableList<Int>> {
        val visible = mutableListOf<MutableList<Int>>()

        for ((rowIndex, row) in input.withIndex()) {
            visible.add(row.map { 1 }.toMutableList())

            if (rowIndex > 0) {
                for (r in input.indices.take(rowIndex)) {
                    for (c in input[rowIndex].indices) {
                        if (input[rowIndex][c] <= input[r][c]) {
                            visible[rowIndex][c] = 0

                        }
                    }
                }
            }
        }

        return visible
    }

    private fun visibleLeft(input: List<List<Int>>): MutableList<MutableList<Int>> {
        val visible = mutableListOf<MutableList<Int>>()

        for ((rowIndex, row) in input.withIndex()) {
            visible.add(row.map { 1 }.toMutableList())

            for (columnIndex in visible[rowIndex].indices) {
                if (columnIndex == 0) {
                    visible[rowIndex][columnIndex] = 1
                } else {
                    for (c in input[rowIndex].indices.take(columnIndex)) {
                        val currentStatic = input[rowIndex][columnIndex]
                        val currentDynamic = input[rowIndex][c]
                        if (currentStatic <= currentDynamic) {
                            visible[rowIndex][columnIndex] = 0
                        }
                    }
                }
            }
        }

        return visible
    }

    fun solveFirst(): Int {
        val data = File("src/main/kotlin/day08/input.txt").readText()
            .split("\n")
            .map { it.toCharArray().map { c -> c.toString() }.map { s -> s.toInt() } }

        val fromAbove = visibleAbove(data)
        val fromBelow = visibleAbove(data.reversed()).reversed()
        val fromLeft = visibleLeft(data)
        val fromRight = visibleLeft(data.map { it.reversed() }).map { it.reversed() }

        var sum = 0;
        for (row in data.indices) {
            for (col in data[row].indices) {
                if (fromAbove[row][col] + fromBelow[row][col] + fromLeft[row][col] + fromRight[row][col] > 0) sum +=1
            }
        }

        return sum
    }

    private fun calcScenicScore(input: List<List<Int>>, row: Int, col: Int): Int {
        var downScore = 0
        for (r in row + 1 until input.size) {
            val static = input[row][col]
            val dynamic = input[r][col]
            if (static > dynamic) {
                downScore += 1
            } else {
                downScore += 1
                break
            }
        }

        var upScore = 0
        for (r in row - 1 downTo 0) {
            val static = input[row][col]
            val dynamic = input[r][col]
            if (static > dynamic) {
                upScore += 1
            } else {
                upScore += 1
                break
            }
        }

        var rightScore = 0
        for (c in col + 1 until input[row].size) {
            val static = input[row][col]
            val dynamic = input[row][c]
            if (static > dynamic) {
                rightScore += 1
            } else {
                rightScore += 1
                break
            }
        }

        var leftScore = 0
        for (c in col - 1 downTo 0) {
            val static = input[row][col]
            val dynamic = input[row][c]
            if (static > dynamic) {
                leftScore += 1
            } else {
                leftScore += 1
                break
            }
        }

        return downScore * upScore * rightScore * leftScore
    }


    fun solveSecond(): Int {
        val data = File("src/main/kotlin/day08/input.txt").readText()
            .split("\n")
            .map { it.toCharArray().map { c -> c.toString() }.map { s -> s.toInt() } }

        var highestScore = 0
        for (row in data.indices) {
            if (row > 0) {
                for (col in data[row].indices) {
                    if (col > 0) {
                        val currentScore = calcScenicScore(data, row, col)
                        if (currentScore > highestScore) {
                            highestScore = currentScore
                        }
                    }
                }
            }

        }

        return highestScore
    }

}