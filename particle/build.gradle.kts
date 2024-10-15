import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask
import org.openapitools.generator.gradle.plugin.tasks.ValidateTask

plugins {
    id("org.openapi.generator") version "7.8.0"
}

val validateAllTask = tasks.register<DefaultTask>("openApiValidateAll")
val generateAllTask = tasks.register<DefaultTask>("openApiGenerateAll")

configureTask()

fun Project.configureTask() {
    val packageName = "com.ndipatri.kmp.openapi.particle"

    val validateTask = tasks.register<ValidateTask>("openApiValidateparticle") {
        inputSpec.set("${rootProject.projectDir}/particle/openapi/client/particle.yml")
        recommend.set(true)
        outputs.cacheIf { false }
        outputs.upToDateWhen { false }
    }
    validateAllTask.dependsOn(validateTask)

    val targetDir = "${rootProject.projectDir}/particle/openapi/client/"

    val copyTemplateTask = tasks.register<Copy>("openApiCopyTemplatetparticle") {
        from("${rootProject.projectDir}/particle/openapi/template")
        include("*")
        into(targetDir)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        exclude {
            file("targetDir/${it.name}").exists()
        }
    }

    val generatedTask = tasks.register<GenerateTask>("openApiGenerateaparticle") {
        dependsOn(copyTemplateTask)
        inputSpec.set("${rootProject.projectDir}/particle/openapi/client/particle.yml")
        outputDir.set("${rootProject.projectDir}/particle/openapi/client")
        outputs.cacheIf { false }
        outputs.upToDateWhen { false }
        this.generatorName.set("kotlin")
        this.library.set("multiplatform")
        this.packageName.set(packageName)
        this.logToStderr.set(true)
        configOptions.set(
            buildMap {
                put("dateLibrary", "kotlinx-datetime")
                put("enumPropertyNaming", "UPPERCASE") // https://github.com/OpenAPITools/openapi-generator/issues/3804
                put("omitGradleWrapper", "true")
                put("useCoroutines", "true")
            }
        )
    }

    generateAllTask.dependsOn(generatedTask)
}
