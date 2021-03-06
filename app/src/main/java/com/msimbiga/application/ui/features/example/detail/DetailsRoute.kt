package com.msimbiga.application.ui.features.example.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.msimbiga.domain.models.Character
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(navArgsDelegate = DetailsRouteNavArgs::class)
@Composable
fun DetailsRoute(
    navigator: DestinationsNavigator,
    viewModel: DetailsRouteViewModel = hiltViewModel()
) {
    val screenState = viewModel.uiState.collectAsState()

    BackHandler {
        viewModel.handleEvent(DetailsScreenEvent.NavigateUp)
    }

    LaunchedEffect(Unit) {
        viewModel.navigationEventFlow.collect { destination ->
            when (destination) {
                DetailsScreenDirections.Back -> navigator.navigateUp()
            }
        }
    }

    DetailScreen(state = screenState.value)
}

@Composable
fun DetailScreen(
    state: DetailViewModelState,
) {
    state.character?.let { char ->
        DetailScreenContent(character = char)
    }
}

@Composable
fun DetailScreenContent(character: Character) {
    Column(modifier = Modifier.fillMaxSize()) {
        CharacterDetailImage(
            modifier = Modifier.weight(1f),
            url = character.image
        )
        CharacterDescription(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            character = character
        )
    }
}

@Composable
fun CharacterDescription(modifier: Modifier = Modifier, character: Character) {
    LazyColumn(
        modifier = modifier
            .border(2.dp, Color.Cyan)
            .testTag(DetailConsts.LIST_TAG),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        item { Text(text = "Id: ${character.id}") }
        item { Text(text = "Name: ${character.name}") }
        item { Text(text = "Gender: ${character.gender}") }
        item { Text(text = "status: ${character.status}") }
        item { Text(text = "Specie: ${character.species}") }
        item { Text(text = "type: ${character.type}") }
        item { Text(text = "Origin: ${character.origin}") }
        item { Text(text = "Location: ${character.location}") }
        item { Text(text = "Episodes: ${character.episode}") }
        item { Text(text = "created: ${character.created}") }
    }
}

@Composable
fun CharacterDetailImage(modifier: Modifier = Modifier, url: String) {
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = { crossfade(true) }
        ),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, Color.Yellow)
    )
}
