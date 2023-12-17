package dev.no.room113.app.ui.preferences

import dev.no.room113.utils.preferences.Preferences

data class Test(val name: String, val age: Int)
class UserPreferences : Preferences() {
    var emailAccount by stringPref()
    var showSystemAppsPreference by booleanPref()
    var test by objectPref<Test>()
}