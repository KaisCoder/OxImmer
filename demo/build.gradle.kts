plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "cn.kais.immer.demo"
    compileSdk = 35

    defaultConfig {
        applicationId = "cn.kais.immer.demo"
        minSdk = 19
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
    }

    signingConfigs {
        create("demo") {
            storeFile = file("${rootDir.absolutePath}/demo/demo.jks")
            storePassword = "123456"
            keyAlias = "demo"
            keyPassword = "123456"
        }
    }

    buildTypes {
        val demo = signingConfigs.getByName("demo")
        debug {
            signingConfig = demo
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            signingConfig = demo
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
        viewBinding = true
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":ox-immer"))

    implementation("androidx.multidex:multidex:2.0.1")

    // 替换上面的权限
    implementation("com.github.getActivity:XXPermissions:21.3")
    // 替换上面的adapter
    implementation("io.github.cymchad:BaseRecyclerViewAdapterHelper4:4.1.4")

    implementation("com.github.bumptech.glide:glide:4.16.0")

//    implementation("com.gitee.kais_coder.OxImmer:OxImmer:0.0.11")

}