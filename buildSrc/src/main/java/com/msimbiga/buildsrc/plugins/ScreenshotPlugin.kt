package com.payeye.buildsrc.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.kotlin.dsl.task

class ScreenshotPlugin : Plugin<Project> {

    private val clearScreenshotsTask = "clearScreenshotsTask"
    private val createScreenshotDirectoryTask = "createScreenshotDirectoryTask"
    private val copyScreenshotsTask = "copyScreenshotsTask"

    override fun apply(project: Project) {
        val projectScreenshotsDirectory = "${project.rootDir}/screenshots"
        val deviceScreenshotsDirectory = "sdcard/Pictures/${project.name}/debug/screenshots"

        project.task<Exec>(clearScreenshotsTask) {
            commandLine("adb", "shell", "rm", "-r", deviceScreenshotsDirectory)
        }

        project.task<Exec>(createScreenshotDirectoryTask) {
            commandLine("adb", "shell", "mkdir", "-p", deviceScreenshotsDirectory)
        }

        project.task<Exec>(copyScreenshotsTask) {
            commandLine("adb", "pull", "$deviceScreenshotsDirectory/.", projectScreenshotsDirectory)
            finalizedBy(clearScreenshotsTask)
        }

        project.tasks.whenTaskAdded {
            if (name == "connectedDevDebugAndroidTest") {
                dependsOn(createScreenshotDirectoryTask)
                finalizedBy(copyScreenshotsTask)
            }
        }
    }
}
