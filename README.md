# Pro Clubs Stats

A multiplatform app for tracking EA Sports FC Pro Clubs statistics — leaderboards, club details, member careers, and match history, all in one place.

Built with Kotlin Multiplatform and Compose Multiplatform, targeting Android, iOS, and Desktop from a single shared codebase.

---

## What it does

- Browse the **Top 100 leaderboard** across current-gen, last-gen, and Switch platforms
- Switch between **All Time** and **Current Season** rankings
- **Search** for any club by name
- View **detailed club stats** — wins, losses, goals, clean sheets, division finishes, win streaks, playoff achievements
- See **member career stats** and recent match history
- **Bookmark** clubs you care about so they're always at the top

---

## Tech stack

| Layer | Library |
|---|---|
| UI | Compose Multiplatform 1.9.2 |
| Networking | Ktor 3.3.1 |
| Local DB | Room 2.8.3 |
| DI | Koin 4.1.1 |
| Image loading | Coil 3.3.0 |
| Serialization | kotlinx.serialization 1.9.0 |
| Navigation | Navigation Compose 2.9.1 |

Kotlin 2.2.10 · min SDK 24 · targets Android, iOS (arm64 + simulator), Desktop JVM

---

## Project structure

```
composeApp/src/
├── commonMain/        # All shared code
│   ├── core/          # API client, DB, schemas, utilities
│   ├── domain/        # Repository interfaces & use cases
│   ├── presentation/  # ViewModels & screens
│   ├── ui/            # Reusable components & theme
│   └── navigation/    # Routes
├── androidMain/       # Android entry point & platform specifics
├── iosMain/           # iOS entry point
└── jvmMain/           # Desktop entry point
```

Clean architecture — data, domain, and presentation are separated. The ViewModel talks only to use cases, never directly to the API or DB.

---

## Getting started

**Prerequisites:** Android Studio Hedgehog or later with the Kotlin Multiplatform plugin installed.

```bash
git clone https://github.com/your-username/Clubs.git
cd Clubs
```

Open the project in Android Studio and let Gradle sync. Then run on your target:

- **Android** — select the `composeApp` run configuration and pick a device/emulator
- **Desktop** — run `./gradlew :composeApp:run`
- **iOS** — open `iosApp/iosApp.xcodeproj` in Xcode and run on a simulator or device

No API keys needed. The app pulls data from the public EA Sports FC Pro Clubs API.

---

## Data source

All stats come from the official EA Sports Pro Clubs API at `proclubs.ea.com`. The app does not store any data on a server — bookmarks are saved locally on your device using Room.
