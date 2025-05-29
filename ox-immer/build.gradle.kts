plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven2.publish)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "cn.kais.immer" // 请填入你的组件名
                artifactId = "OxImmer" // 请填入你的工件名
                version = "0.0.1" // 请填入工件的版本名
                afterEvaluate { artifact(tasks.getByName("bundleReleaseAar")) }
            }
        }
    }
}

android {
    namespace = "cn.kais.immer"
    compileSdk = 35

    defaultConfig {
        minSdk = 19
        multiDexEnabled = true
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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