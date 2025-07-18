Tech Stack:
  Language: Kotlin
  Architecture: MVVM (Model-View-ViewModel)
  Dependency Injection: Hilt
  Asynchronous Programming: Coroutines
  Push Notifications: Firebase Cloud Messaging (FCM)

Project Structure :
  app/
  │── data/                 # Data layer (repositories, models, and Firebase service)
  │── domain/               # Use cases
  │── presentation/         # UI layer (ViewModels, Composables)
  │── di/                   # Dependency injection (Hilt modules)
  │── utils/                # Utility functions and helpers


Firebase Integration:
  FCM (Firebase Cloud Messaging) is used to send notifications.
  FirebaseService handles sending notifications to the rental company.
  The FirebaseMessagingService listens for incoming messages.
