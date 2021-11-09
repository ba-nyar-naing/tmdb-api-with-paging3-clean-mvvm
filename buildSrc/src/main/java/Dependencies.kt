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

object Retrofit {
    private const val version = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$version"
    const val gson = "com.squareup.retrofit2:converter-gson:$version"
    const val logging = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
}

object Chuck {
    const val chuck = "com.readystatesoftware.chuck:library:1.1.0"
}

object Coroutine {
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1"
}

object DaggerHilt {
    private const val version = "2.38.1"
    const val core = "com.google.dagger:hilt-core:$version"
    const val android = "com.google.dagger:hilt-android:$version"
    const val androidCompiler = "com.google.dagger:hilt-android-compiler:$version"
    const val compiler = "com.google.dagger:hilt-compiler:$version"
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

object AndroidXNavigation {
    private const val version = "2.3.5"
    const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
    const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
}

object AndroidXLegacy {
    const val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"
}

object Material {
    const val material = "com.google.android.material:material:1.4.0"
}

object Paging3 {
    const val pagingKtx = "androidx.paging:paging-runtime-ktx:3.1.0-beta01"
    const val commonKtx = "androidx.paging:paging-common-ktx:3.0.1"
}

object Image {
    const val coil = "io.coil-kt:coil:1.4.0"
}

object AndoidXRoom {
    private const val version = "2.3.0"

    const val roomRuntime = "androidx.room:room-runtime:$version"
    const val roomCommon = "androidx.room:room-common:$version"
    const val roomKtx = "androidx.room:room-ktx:$version"
    const val roomCompiler = "androidx.room:room-compiler:$version"
    const val roomTest = "androidx.room:room-testing:$version"
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