package kr.heartpattern.spigotinstaller

import kotlinx.coroutines.runBlocking
import kr.heartpattern.spigotversions.getSpigotVersion
import kr.heartpattern.spigotversions.getSpigotVersionList
import java.io.File

fun batchInstaller(remote: String?) {
    runBlocking {
        val cache = File("cache-${remote?.split('|')?.firstOrNull() ?: "local"}")
        @Suppress("BlockingMethodInNonBlockingContext")
        cache.createNewFile()
        val cacheList = cache.readLines()

        println("Download version list")
        val versions = getSpigotVersionList()
        val releases = versions.filter { it.startsWith("1.") }
        for (version in releases) {
            println("Check $version")
            val info = getSpigotVersion(version)
            if (cacheList.contains(info.name))
                continue

            println("$version is not cached")

            val workingDir = File("build")
            workingDir.mkdirs()

            println("Run buildtool")
            runBuildTool(version, workingDir)

            println("Install maven")
            if (remote != null)
                installRemote(workingDir, remote)
            else
                installLocal(workingDir)

            cache.appendText(info.name + "\n")
        }
    }
}