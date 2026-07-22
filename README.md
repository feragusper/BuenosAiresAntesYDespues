# BAAD — Buenos Aires Antes y Después (Android)

A journey through time between Buenos Aires yesterday and today: a curated set of old photos
of the city paired with a current photo taken from the same spot and angle.

Old photos come from the General Archive of the Nation and the "Fotos Viejas de Buenos Aires"
Facebook group; the current ones were taken by Gastón de la Llana, mentor of the project.

- [Google Play](https://play.google.com/store/apps/details?id=com.feragusper.buenosairesantesydespues)
- [Web](http://bsasantesydespues.com.ar)
- [Facebook](https://www.facebook.com/bsasantesydespues)

## Tech stack

Fully modernized (2026) from the original Java / RxJava / Dagger / XML app:

| Area | Technology |
|------|------------|
| Language | Kotlin 2.3, Java 25 bytecode |
| UI | Jetpack Compose + Material 3 |
| Architecture | MVVM + unidirectional data flow, multi-module (feature + core) |
| Async | Coroutines + Flow / StateFlow |
| DI | Hilt |
| Networking | Retrofit 3 + OkHttp 5 + kotlinx.serialization |
| Images | Coil 3 |
| Maps | Maps Compose |
| Navigation | Navigation-Compose |
| Build | Gradle 9.6 (Kotlin DSL), AGP 9.3 (built-in Kotlin), version catalog, convention plugins |
| Min / Target / Compile SDK | 24 / 36 / 36 |

## Module structure

```
:app                     Application, MainActivity, navigation host, splash, about
:feature:records         Records list (grid + pagination) and detail (before/after + map)
:core:model              Pure-Kotlin domain models
:core:common             Coroutine dispatchers, shared errors
:core:network            Retrofit API, DTOs, DTO→domain mappers
:core:data               Repository (single source of truth)
:core:designsystem       Compose theme (Material 3)
build-logic              Gradle convention plugins shared by all modules
```

Dependency direction: `app → feature → core:data → core:network → core:model`.

## Building

Requires **JDK 25** and the Android SDK (compileSdk 36).

```bash
./gradlew assembleDebug
```

Create a `local.properties` with your SDK path and a Google Maps key:

```properties
sdk.dir=/path/to/Android/sdk
MAPS_API_KEY=your_maps_api_key
```

A Firebase `google-services.json` is required under `app/src/debug/` and `app/src/release/`
(kept out of version control).

## Architecture guidelines

The layering follows the modern Android app architecture guidance (UI ← ViewModel ← Repository
← data source) with immutable `UiState` exposed as `StateFlow`.

## Contributing

Pull requests are welcome. Fork, branch, commit, push, open a PR.

## License

See [LICENSE](LICENSE).
