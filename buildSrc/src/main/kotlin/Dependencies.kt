const val kotlinVersion = "1.3.21"

private object Versions {
    const val navigation = "2.1.0"

    const val junit = "4.12"
}

object Dependencies {

    const val navigationUiLibrary = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragmentLibrary =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
}

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
    }

    const val appCompatLibrary = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtxLibrary = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayoutLibrary =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
}

object KotlinLibraries {

    object Versions {
        const val kotlin = "1.3.21"
    }

    const val kotlinStdLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object TestLibraries {
    const val junitLibrary = "junit:junit:${Versions.junit}"
}