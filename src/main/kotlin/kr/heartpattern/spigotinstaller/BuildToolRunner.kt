package kr.heartpattern.spigotinstaller

import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels

fun runBuildTool(version: String, directory: File) {
    directory.mkdirs()
    val buildToolFile = File(directory, "buildtool.jar")
    val readChannel = Channels.newChannel(URL(buildTool).openStream())
    val writeChannel = FileOutputStream(buildToolFile).channel
    writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE)

    val process = ProcessBuilder()
        .directory(directory)
        .command(
            "java",
            "-jar",
            "buildtool.jar",
            "--rev",
            version,
            "--compile",
            "NONE"
        )
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()

    process.waitFor()
}