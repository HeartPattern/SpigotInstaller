package kr.heartpattern.spigotinstaller

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.validate

class App : CliktCommand() {
    val remote by option().validate { str ->
        if (str.count { it == '|' } != 1) {
            fail("Remote must be a format of {id}|{repo}. (central|https://m2.maven.org/")
        }
    }

    override fun run() {
        batchInstaller(remote)
    }
}

fun main(args: Array<String>) = App().main(args)