package com.payeye.eyepos.ui.feature.example

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.payeye.eyepos.coreui.theme.EyePosTheme
import com.payeye.eyepos.domain.models.Mocks
import com.payeye.eyepos.ui.features.example.character.CharacterRoute
import com.payeye.eyepos.ui.features.example.character.CharacterRoute.includeCharacterRoute
import com.payeye.eyepos.ui.features.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CharacterScreenUiTest() {

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()

        composeTestRule.setContent {
            val navController = rememberNavController()

            EyePosTheme(darkTheme = false) {
                NavHost(
                    navController = navController,
                    startDestination = CharacterRoute.destination
                ) {
                    includeCharacterRoute()
                }
            }
        }
    }

    @Test
    fun assertListTag_isVisible() {
        composeTestRule.onNodeWithTag(CharacterRoute.LIST_TAG).assertIsDisplayed()
    }

    @Test
    fun listWithData_hasClickableItems() {
        composeTestRule.onNodeWithText(Mocks.mockChar.name, substring = true)
            .assertExists()
            .assertIsDisplayed()
            .assertHasClickAction()
    }
}