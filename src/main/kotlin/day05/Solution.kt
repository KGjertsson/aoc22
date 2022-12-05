package day05

import java.io.File

data class Command(val numberCreates: Int, val getIndex: Int, val putIndex: Int)

class Solution {

    private fun createStacks(stacksRaw: String): List<List<String>> =
        stacksRaw.split("\n")
            .map { it.split(" ") }

    private fun createCommands(commandsRaw: String): List<Command> =
        commandsRaw.trim()
            .split("\n")
            .map { it.split(" ").filter { word -> word != "move" && word != "to" && word != "from" } }
            .map { it.map { command -> command.toInt() } }
            .map { Command(it[0], it[1] - 1, it[2] - 1) }

    private fun resolveCommandFirst(stacks: List<List<String>>, command: Command): List<List<String>> {
        val poppedStacks = stacks[command.getIndex]
            .take(command.numberCreates)
            .reversed()

        return stacks.mapIndexed { index, stack ->
            when (index) {
                command.getIndex -> stack.takeLast(stack.size - command.numberCreates)
                command.putIndex -> poppedStacks + stack
                else -> stack
            }
        }
    }

    private fun resolveCommandSecond(stacks: List<List<String>>, command: Command): List<List<String>> {
        val poppedStacks = stacks[command.getIndex]
            .take(command.numberCreates)

        return stacks.mapIndexed { index, stack ->
            when (index) {
                command.getIndex -> stack.takeLast(stack.size - command.numberCreates)
                command.putIndex -> poppedStacks + stack
                else -> stack
            }
        }
    }

    fun solveFirst(): String {
        val dataRaw = File("src/main/kotlin/day05/input.txt").readText()
            .split("\n\n")

        val stacks = createStacks(dataRaw[0])
        val commands = createCommands(dataRaw[1])

        return commands.fold(stacks) { acc, command -> resolveCommandFirst(acc, command) }
            .map { it.first() }
            .reduce { acc, s -> acc + s }
    }

    fun solveSecond(): String {
        val dataRaw = File("src/main/kotlin/day05/input.txt").readText()
            .split("\n\n")

        val stacks = createStacks(dataRaw[0])
        val commands = createCommands(dataRaw[1])

        return commands.fold(stacks) { acc, command -> resolveCommandSecond(acc, command) }
            .map { it.first() }
            .reduce { acc, s -> acc + s }
    }

}
