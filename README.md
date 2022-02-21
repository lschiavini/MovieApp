# MovieApp

App developed as a part of an online examination for Kotlin role

Minimal **Kotlin Android MVVM** project with Room, Navigation, RxJava, RetroFit, Shared preferences and [The MovieDataBase](https://developers.themoviedb.org/).

## Requirements
- Two screen app: (DONE)
    - ListView: List of movies fetched from either OMDB or The MovieDataBase APIs. 
        - Movies should have: Poster, Title and Release Year
    - DetailView: 
        - Movie should have: Poster, Title and Release Year, Director, Duration, Description, Score.
    - Should navigate from the main view(ListView) to detailed view.

- Extras:
    - Dark/Light mode support. (DONE)
    - Search by Name
    - Filter by year
    - Remove movies from list

## Building

- You will need to use Android Studio Arctic Fox (**note: Java 11 is now the minimum version required**).
- Supported Android Platforms:
    - Android SDK: min 23
    - JDK: 11
    - Kotlin: 1.6.10
    - Gradle: 4.2.1

- If you have the requirements, just hit the Run button from Android Studio

## Architecture
The architecture chosen for the app was Model View ViewModel (MVVM), whereas business logic is located at the many ViewModels. 

The View is only concerned with What to show on the screen.

The model has the responsibility of modeling the data and the implementation for fetching it in a repository (both from a local database and from the api chosen).

There is a simple adapter and a navigation graph that make possible for the two screens to interact. More importantly the id of the movie chosen from the ListView is declared as an argument at the navgraph. That way the detail view can fetch for more specific data, such as Director's name.


## Screenshots
**ListView/MainView**
<br/>
<img width="300" alt="List of movies screen" src="images\imageListApp.png">


**DetailView**
<br/>
<img width="300" alt="List of movies screen" src="images\imageDetailApp.png">

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
* [Room Database](https://developer.android.com/reference/androidx/room/RoomDatabase)
* [Retrofit](https://devtut.github.io/android/retrofit2.html)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [Androidx Navigation](https://developer.android.com/jetpack/androidx/releases/navigation)

