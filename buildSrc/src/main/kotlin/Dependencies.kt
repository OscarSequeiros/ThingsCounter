const val kotlinVersion = "1.3.21"

object AndroidSdk {
    const val min = 16
    const val compile = 28
    const val target = compile
}

object AndroidxLibraries {

    object Versions {
        const val appCompat = "1.1.0"
        const val coreKtx = "1.1.0"
        const val constraint = "1.1.3"
        const val material = "1.0.0"
    }

    const val appCompatLibrary = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtxLibrary = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayoutLibrary =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val materialLibrary = "com.google.android.material:material:${Versions.material}"
}

object KotlinLibraries {

    object Versions {
        const val kotlin = "1.3.21"
    }

    const val kotlinStdLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object TestLibraries {

    object Versions {
        const val junit = "4.12"
    }

    const val junitLibrary = "junit:junit:${Versions.junit}"
}

object PersistenceLibraries {

    object Versions {
        const val room = "2.1.0"
    }

    const val roomLibrary = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
}

object MultithreadLibraries {

    object Versions {
        const val rxjava2 = "2.2.8"
        const val rxandroid = "2.1.1"
    }

    const val rxJavaLibrary = "io.reactivex.rxjava2:rxjava:${Versions.rxjava2}"
    const val rxAndroidLibrary = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
}