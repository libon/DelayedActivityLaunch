This project demonstrates an issue launching an activity "from the background", on Android 10.

Context: Android 10 introduces new restrictions launching activities "from the background":

https://developer.android.com/guide/components/activities/background-starts

One exemption is noted:
> The app has an activity in the back stack of an existing task on the Recents screen
>
>     Note: When such an app attempts to start a new activity, the system places that activity on top of the app's existing task but doesn't navigate away from the currently-visible task. When the user later returns to the app's task, the system starts the new activity instead of the activity that had previously been on top of the app's task.

This app demonstrates that this exemption doesn't work as described: 
- when putting the app in the background with "home": an activity cannot be launched from the background, even if it's in the list of recent apps.
- when putting the app in the background with "recents": when the app launches the second activity, it takes over the currently-visible task

This app has the following functionality: the first screen has a button, which, when pressed, opens a second screen after a 5 second delay.

Steps to reproduce the problem:

*Pressing "Home"*

On < Android 10:
* Launch the app
* Tap on the button
* Before 5 seconds pass, press on home to bring the app in the background
* Wait for 5 seconds to pass
* :white_check_mark: The app becomes visible again, opened in the second activity
* [Screen recording](images/android7-home.webm)

On Android 10:
* Launch the app
* Tap on the button
* Before 5 seconds pass, press on home to bring the app in the background
* Wait for 5 seconds to pass
* Reopen the app from the recents list
* Expected behavior: the second screen appears
* :x: Actual behavior: On Android 10, the second screen isn't launched. The sample app is still on the first screen.
* [Screen recording](images/android10-home.webm)


*Pressing recents*

On < Android 10:
* Launch the app
* Tap on the button
* Before 5 seconds pass, press on recents to navigate to another app.
* Wait for 5 seconds to pass
* :white_check_mark: The sample app becomes visible again, opened in the second activity
* [Screen recording](images/android7-recents.webm)


On Android 10:
* Launch the app
* Tap on the button
* Before 5 seconds pass, press on recents to navigate to a different app
* Wait for 5 seconds to pass
* Expected behavior: The user doesn't notice anything. The second activity should be launched silently inside the sample app's task, but the currently-visible task remains visible.
* :x: Actual behavior: The sample app becomes visible again, opened in the second activity
* [Screen recording](images/android10-recents-doesntwork.webm)

Note, during some tests, this scenario actually worked. I didn't figure out what made it work sometimes and not work sometimes. Here's the [screen recording of it working](images/android10-recents-works.webm).

