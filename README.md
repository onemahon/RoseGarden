# README

This project consists of the following gradle structure:

```
app
\--- project :garden
     \--- project :tulips -> project :roses
```

Where `:garden` constains a transitive dependency into `:tulips`. But, in this case, `:app` replaces `:tulips` with `:roses` with [the following snippet](https://github.com/onemahon/RoseGarden/blob/master/app/build.gradle#L19):

```
configurations.all {
    resolutionStrategy.dependencySubstitution {
        substitute project(":tulips") with project(":roses")
    }
}
```

Building `./gradlew app:assembleDebug` works fine on command line, and generates a working APK, but Android Studio shows an error in `app/src/main/AndroidManifest.xml` (when looking at the merged manifest view) saying:

> Merging Errors: Error: Attribute meta-data#garden_type@value value=(This is where tulips grow) from AndroidManifest.xml:5:47-88 is also present at AndroidManifest.xml:5:47-87 value=(This is where roses grow). Suggestion: add 'tools:replace="android:value"' to <meta-data> element at AndroidManifest.xml:5:9-90 to override. app main manifest (this file), line 4

This shouldn't be the case. Android Studio should be aware of the gradle substitution that completely replaces `:tulips` with `:roses`, and shouldn't display an error when merging the manifest of `:app`.

---

This example is trivial (and intentionally simplified to highlight the source of the issue), but in our more complicated project, this issue (for a reason I can't figure out) is preventing our app from even *building* successfully via Android Studio, despite still working fine on the command line.
