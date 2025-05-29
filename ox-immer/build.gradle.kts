import com.android.build.gradle.internal.api.DefaultAndroidSourceDirectorySet

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven2.publish)
}

//val version = "0.0.2"
//val groupId = "cn.kais.immer"
//val artifactId = "OxImmer"

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                artifact(tasks["sourcesJar"])//打包 jar
//                afterEvaluate { artifact(tasks.getByName("sourcesJar")) }
//                artifact("$buildDir/outputs/aar/${artifactId}_${version}.aar")//打包 aar
                version = "0.0.3" // 请填入工件的版本名
                groupId = "cn.kais.immer" // 请填入你的组件名
                artifactId = "OxImmer" // 请填入你的工件名
            }
        }
    }
}

//task sourcesJar(type: Jar) {
//    if (project.hasProperty("kotlin")) {
//        from android.sourceSets.main.java.getSrcDirs()
//    } else if (project.hasProperty("android")) {
//        from android.sourceSets.main.java.sourceFiles
//    } else {
//        println project
//                from sourceSets.main.allSource
//    }
//    archiveClassifier='sources'
//}

tasks.register("sourcesJar", Jar::class.java) {
    archiveClassifier.set("source")
    from(android.sourceSets["main"].java.srcDirs)
    from((android.sourceSets["main"].kotlin as DefaultAndroidSourceDirectorySet).srcDirs)
}

android {
    namespace = "cn.kais.immer"
    compileSdk = 35

    defaultConfig {
        minSdk = 19
        multiDexEnabled = true
        consumerProguardFiles("consumer-rules.pro")
    }

//    libraryVariants.configureEach {
//        outputs.configureEach {
//            val output = this as BaseVariantOutputImpl
//            if (output.outputFileName.endsWith(".aar")) {
//                output.outputFileName = "${artifactId}_${version}.aar"
//            }
//        }
//    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = true
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation("androidx.multidex:multidex:2.0.1")

    implementation("androidx.startup:startup-runtime:1.1.1") // 启动初始化

    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.davemorrissey.labs:subsampling-scale-image-view-androidx:3.10.0") // 加载超大图

}