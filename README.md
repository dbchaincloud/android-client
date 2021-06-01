# android-client
android client for dbchain

# Quick Setup

Step 1. Add the JitPack repository to your build file
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Step 2. Add the dependency
```
dependencies {
    implementation 'com.github.dbchaincloud:android-client:1.0.0'
}
```

# Initialize DBChain in Application
```
val appCode = "Your AppCode"
val baseUrl = "Your BaseUrl"
val chainId = "Your ChainId"
val debug = BuildConfig.DEBUG
DBChain.init(this, appCode, baseUrl, chainId, debug)
```
