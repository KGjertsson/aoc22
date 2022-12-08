package day07

import java.io.File

class Node(
    val name: String,
    val parent: Node? = null,
    val children: MutableList<Node> = mutableListOf(),
    var size: Int = 0
)


class Solution {

    private fun addSize(node: Node, size: Int): Node {
        node.size += size
        return if (node.parent != null) {
            addSize(node.parent, size)
        } else {
            node
        }
    }

    private fun traverseTree(nodeList: List<Node>, node: Node): List<Node> {
        return if (node.children.isNotEmpty()) {
            nodeList + listOf(node) + node.children.flatMap { traverseTree(nodeList, it) }
        } else {
            nodeList
        }
    }

    private fun createTree(): Node {
        val root = Node("/")
        var currentNode = root
        File("src/main/kotlin/day07/input.txt").readLines()
            .drop(1)
            .forEach { line ->
                if (line.startsWith("$ ls")) {
                    // do nothing, ls is implicit
                } else if (line.startsWith("$ cd")) {
                    val newDir = line.split(" ").last()
                    currentNode = if (newDir == "..") {
                        currentNode.parent!!
                    } else {
                        currentNode.children.first { it.name == newDir }
                    }
                } else {
                    val split = line.split(" ")
                    val first = split[0]
                    val second = split[1]
                    val newNode = Node(second, currentNode)
                    currentNode.children.add(newNode)
                    if (first != "dir") {
                        addSize(newNode, first.toInt())
                    }
                }
            }

        return root
    }

    fun solveFirst(): Int {
        val root = createTree()

        return traverseTree(emptyList(), root)
            .map { it.size }
            .filter { it < 100000 }
            .sum()
    }

    fun solveSecond(): Int {
        val root = createTree()
        val totalSpace = 70000000
        val requiredSpace = 30000000
        val usedSpace = root.size
        val unusedSpace = totalSpace - usedSpace

        return traverseTree(emptyList(), root)
            .map { it.size }
            .sorted()
            .first { unusedSpace + it > requiredSpace }
    }

}
