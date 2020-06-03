package kashyap.`in`.androidbestpractices.learning.kotlin

fun String.removeFirstLastChar(): String = this.substring(1, this.length - 1)

fun main() {
    extensionFunctions()
}

fun extensionFunctions() {
    val string: String = "ABCDE"
    print(string.removeFirstLastChar())
}