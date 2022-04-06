package com.payeye.eyepos.ui.feature.endtoend

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.payeye.eyepos.domain.models.Mocks
import com.payeye.eyepos.ui.features.example.character.CharacterRoute
import com.payeye.eyepos.ui.features.example.detail.DetailRoute
import com.payeye.eyepos.ui.features.main.MainActivity
import com.payeye.eyepos.util.saveScreenshot
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CharacterToDetailScenario {

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun onCharacterClick_navigatesToDetails() {
        with(composeTestRule) {
            saveScreenshot("1.CharacterToDetail_ClickOnFirst")
            onNodeWithTag(CharacterRoute.LIST_TAG).assertIsDisplayed()

            onNodeWithText(Mocks.mockChar.name, substring = true, ignoreCase = true)
                .assertHasClickAction()
                .performClick()

            saveScreenshot("1.CharacterToDetail_DetailScreenVisible")
            onNodeWithTag(DetailRoute.LIST_TAG)
                .assertIsDisplayed()
            onNodeWithText(Mocks.mockChar.name, substring = true).assertIsDisplayed()
            onNodeWithText(Mocks.mockChar.gender, substring = true)
            onNodeWithText(Mocks.mockChar.id.toString(), substring = true)
        }
    }


    @Test
    fun onNavigateBackFromDetails_listIsVisible() {
        with(composeTestRule) {
            onCharacterClick_navigatesToDetails()

            saveScreenshot("2.CharacterToDetail_BackFromDetail")
            onNodeWithTag(DetailRoute.LIST_TAG)
                .assertIsDisplayed()
            onNodeWithText(Mocks.mockChar.name, substring = true).assertIsDisplayed()
            onNodeWithText(Mocks.mockChar.gender, substring = true)
            onNodeWithText(Mocks.mockChar.id.toString(), substring = true)
            activity.onBackPressed()

            saveScreenshot("2.CharacterToDetail_ListIsVisible")
            onNodeWithTag(CharacterRoute.LIST_TAG).assertIsDisplayed()

            onNodeWithText(Mocks.mockChar.name, substring = true, ignoreCase = true)
                .assertHasClickAction()
                .performClick()
        }
    }
}