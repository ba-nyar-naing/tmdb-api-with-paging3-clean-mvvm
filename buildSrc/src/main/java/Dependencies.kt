object BuildConfig {
    const val compileSdk = 31
    const val minSdk = 23
    const val targetSdk = 31

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0
    private const val versionBuild = 0

    const val versionName =
        "$versionMajor.$versionMinor.$versionPatch($versionBuild)"
    const val versionCode =
        21 * 10000000 + versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100 + versionBuild

}

object CommonLibs {
    const val junit = "junit:junit:4.13.2"
    const val timber = "com.jakewharton.timber:timber:4.7.1"
}

object DaggerHilt {
    const val android = "com.google.dagger:hilt-android:2.38.1"
    const val androidCompiler = "com.google.dagger:hilt-android-compiler:2.38.1"
    const val compiler = "com.google.dagger:hilt-compiler:2.38.1"
}

object AndroidXHilt {
    const val lifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    const val compiler = "androidx.hilt:hilt-compiler:1.0.0"
}

object AndroidXCore {
    const val coreKtx = "androidx.core:core-ktx:1.6.0"
}

object AndroidXAppCompat {
    const val appCompat = "androidx.appcompat:appcompat:1.3.1"
}

object AndroidXConstraintLayout {
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
}

object AndroidXFragment {
    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.6"
}
object Material {
    const val material = "com.google.android.material:material:1.4.0"
}

object AndroidXTest {
    private const val version = "1.1.3"

    const val junit = "androidx.test.ext:junit:$version"
    const val junitKtx = "androidx.test.ext:junit-ktx:$version"
}

object AndroidXEspresso {
    private const val version = "3.4.0"
    const val core = "androidx.test.espresso:espresso-core:$version"
}