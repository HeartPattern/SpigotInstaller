package kr.heartpattern.spigotinstaller

import org.apache.maven.model.DeploymentRepository
import org.apache.maven.model.DistributionManagement
import org.apache.maven.model.io.xpp3.MavenXpp3Reader
import org.apache.maven.model.io.xpp3.MavenXpp3Writer
import java.io.File

fun installLocal(directory: File){
    val process = ProcessBuilder()
        .directory(File(directory, "Spigot"))
        .command(
            "mvn",
            "clean",
            "install"
        )
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()

    process.waitFor()
}

fun installRemote(directory: File, remote: String) {
    val spigot = File(directory, "Spigot")
    remapPom(File(spigot, "pom.xml"), remote)
    remapPom(File(spigot,"Spigot-Server/pom.xml"), remote)
    remapPom(File(spigot, "Spigot-API/pom.xml"), remote)

    val process = ProcessBuilder()
        .directory(spigot)
        .command(
            "mvn",
            "clean",
            "deploy"
        )
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()

    process.waitFor()
}

fun remapPom(pom: File, remote: String){
    val (name, adress)  = remote.split(",")
    val model = MavenXpp3Reader().read(pom.inputStream())
    model.distributionManagement = DistributionManagement().apply{
        this.snapshotRepository = DeploymentRepository().apply {
            id = name
            url = adress
        }
    }
    MavenXpp3Writer().write(pom.outputStream(), model)
}