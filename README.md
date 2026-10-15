# WeatherPatterns - Architectural Design Patterns Practice Class

An Android application built with **Jetpack Compose**, **Material 3**, and **Kotlin** demonstrating four architectural design patterns applied to the exact same weather feature:
1. **MVC** (Model-View-Controller)
2. **MVP** (Model-View-Presenter)
3. **MVVM** (Model-View-ViewModel)
4. **MVI** (Model-View-Intent)

## Project Structure

- `data`: Shared data layer (`Weather` model, `WeatherApi`, `FakeWeatherApi`, `WeatherRepository`).
- `di`: Dependency injection module (`WeatherModule`).
- `ui`: Shared `WeatherScreen` composable.
- `mvc`: MVC implementation (`WeatherModel`, `WeatherView`, `WeatherController`, `MvcActivity`).
- `mvp`: MVP implementation (`WeatherContract`, `WeatherPresenter`, `MvpActivity`).
- `mvvm`: MVVM implementation (`WeatherUiState`, `WeatherViewModel`, `MvvmActivity`).
- `mvi`: MVI implementation (`WeatherState`, `WeatherIntent`, `WeatherMviViewModel`, `MviActivity`).

## Running Unit Tests
All business logic, presenters, and view models are fully testable on the local JVM without an emulator:
- `WeatherRepositoryTest`
- `WeatherPresenterTest`
- `WeatherMviViewModelTest`

Run tests via Gradle:
```bash
./gradlew testDebugUnitTest
```
