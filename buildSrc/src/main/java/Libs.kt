object Libs {

    // ClassPath
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildGradleVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val googleService = "com.google.gms:google-services:${Versions.googleServiceVersion}"
    const val detektGradle =
        "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detektGradleVersion}"
    const val hiltPlugin =  "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    const val navSafeArgPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navSafArgPluginVersion}"

    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"

    // Coroutines
    const val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineVersion}"
    const val coroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineVersion}"

    // rx java
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"

    // AndroidX
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val legacySupport =
        "androidx.legacy:legacy-support-v4:${Versions.legacySupportVersion}"
    const val activity = "androidx.activity:activity:${Versions.activityVersion}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityVersion}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragmentVersion}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerviewVersion}"
    const val databindingCompiler =
        "com.android.databinding:compiler:${Versions.databindingVersion}"

    // Test
    const val testCore = "androidx.test:core:${Versions.testVersion}"
    const val testrunner = "androidx.test:runner:${Versions.testVersion}"
    const val testrules = "androidx.test:rules:${Versions.testVersion}"
    const val espressoCore =
        "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExtVersion}"

    // Lifecycle
    const val lifeCyle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleVersion}"
    const val viewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycleVersion}"
    const val extensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleVersion}"
    const val liveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycleVersion}"
    const val commonJava8 =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycleVersion}"
    const val compiler =
        "androidx.lifecycle:lifecycle-compiler:${Versions.lifeCycleVersion}"


    // Navigation
    const val navFragment =
        "androidx.navigation:navigation-ui:${Versions.navigationVersion}"
    const val navFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navUi = "androidx.navigation:navigation-ui:${Versions.navigationVersion}"
    const val navUiKtx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"

    // StartUp
    const val startUp = "androidx.startup:startup-runtime:${Versions.startupVersion}"

    // Paging
    const val paging3 = "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"

    // Google
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"

    // Junit
    const val junit4 = "junit:junit:${Versions.junitVersion}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val gsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"


    // Okhttp
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp3Version}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3Version}"

    // RxBiding
    const val rxbinding = "com.jakewharton.rxbinding3:rxbinding:${Versions.rxbindingVersion}"

    // Debug
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"


    // Klint
    const val klint = "com.pinterest:ktlint:${Versions.klintVersion}"

    // Hilt
    const val hiltAndroid =  "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltViewModel}"

    // Colil
    const val coil = "io.coil-kt:coil:${Versions.coilVersion}"
}
