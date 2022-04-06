package com.payeye.eyepos.ui.feature.example

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.payeye.eyepos.domain.models.Mocks
import com.payeye.eyepos.ui.features.example.character.CharacterScreenContent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterScreenContentTest {

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @Test
    fun emptList_hasNoClickableNodes() {
        with(composeTestRule) {
            setContent {
                CharacterScreenContent(charList = listOf(), onClick = {})
            }

            // Has to be substring since `Name:` str is appended to character name
            onNodeWithText(Mocks.mockChar.name, substring = true).assertDoesNotExist()
        }
    }

    @Test
    fun providedData_hasClickableNodes() {
        with(composeTestRule) {
            setContent {
                CharacterScreenContent(charList = listOf(Mocks.mockChar), onClick = {})
            }

            // Has to be substring since `Name:` str is appended to character name
            onNodeWithText(Mocks.mockChar.name, substring = true)
                .assertExists()
                .assertIsDisplayed()
                .assertHasClickAction()
        }
    }
}
