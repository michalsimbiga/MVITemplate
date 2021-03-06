package com.msimbiga.application.ui.features.example.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.msimbiga.application.ui.features.example.destinations.DetailsRouteDestination
import com.msimbiga.domain.models.Character
import com.msimbiga.domain.models.Mocks
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun CharacterRoute(
    navigator: DestinationsNavigator,
    viewModel: CharacterRouteViewModel = hiltViewModel()
) {
    val screenState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleEvent(CharacterRouteEvent.LoadData)

        viewModel.navigationEventFlow.collect { destination ->
            when (destination) {
                CharacterDirections.Back -> navigator.navigateUp()
                is CharacterDirections.CharacterDetail -> navigator.navigate(
                    DetailsRouteDestination(destination.charId)
                )
            }
        }
    }

    CharacterScreen(
        state = screenState.value,
        onEvent = viewModel::handleEvent
    )
}

@Composable
fun CharacterScreen(
    state: HomeUiState,
    onEvent: (CharacterRouteEvent) -> Unit
) {

    when (state) {
        is HomeUiState.HasNoCharacters -> {
            CircularProgressIndicator()
        }
        is HomeUiState.HasCharacters -> {
            CharacterScreenContent(
                charList = state.characters,
                onClick = { id -> onEvent(CharacterRouteEvent.NavigateToId(id.toString())) }
            )
        }
    }
}

@Composable
fun CharacterScreenContent(charList: List<Character>, onClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag(CharacterConsts.LIST_TAG),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(charList) { character ->
            CharacterItem(
                modifier = Modifier
                    .testTag(character.toString())
                    .padding(horizontal = 10.dp)
                    .clickable(enabled = true, onClick = { onClick.invoke(character.id) }),
                character = character,
            )
        }
    }
}

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: Character,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Blue.copy(0.6f))
            .padding(10.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(20.dp))
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CharacterImage(url = character.image)
            CharacterDescription(
                character = character
            )
        }
    }
}

@Composable
fun CharacterDescription(modifier: Modifier = Modifier, character: Character) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .border(2.dp, Color.Cyan)
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Name: ${character.name}",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onPrimary
        )
        Text(text = "Gender: ${character.gender}")
        Text(text = "Specie: ${character.species}")
    }
}

@Composable
fun CharacterImage(modifier: Modifier = Modifier, url: String) {
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        ),
        contentDescription = null,
        modifier = Modifier
            .size(128.dp)
            .border(2.dp, Color.Yellow)
    )
}

@Preview
@Composable
fun CharacterItemPreview() {
    Surface(modifier = Modifier.fillMaxWidth()) {
        CharacterItem(character = Mocks.charMock)
    }
}

@Preview
@Composable
fun CharacterScreenPreview() {
    Surface(modifier = Modifier.fillMaxWidth()) {
        CharacterScreenContent(
            charList = listOf(Mocks.charMock, Mocks.charMock, Mocks.charMock),
            onClick = {})
    }
}




