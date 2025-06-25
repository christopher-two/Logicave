@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidLibrary {
        namespace = "org.override.book.logicave"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    wasmJs {
        outputModuleName.set("bookLogicave")
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "bookLogicave.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }

    val xcfName = "bookLogicaveKit"

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = xcfName
            isStatic = true
        }
    }

    jvm {
        testRuns["test"].executionTask.configure { useJUnitPlatform() }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)

            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.navigation.compose)

            with(compose) {
                implementation(runtime)
                implementation(foundation)
                implementation(material3)
                implementation(ui)
                implementation(components.resources)
                implementation(components.uiToolingPreview)
            }
        }

        val commonMain by getting {
            resources.srcDirs("src/commonMain/composeResources")
        }

        commonTest.dependencies { implementation(libs.kotlin.test) }
    }
}

//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
//    compilerOptions.freeCompilerArgs.addAll(
//        listOf(
//            "-Xopt-in=org.jetbrains.compose.resources.ExperimentalResourceApi"
//        )
//    )
//}