package com.oechslerbernardo.diaryapp.presentation.screens.write

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.oechslerbernardo.diaryapp.model.Diary
import com.oechslerbernardo.diaryapp.model.Mood
import java.time.ZonedDateTime

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WriteScreen(
    uiState: UiState,
    onBackPressed: () -> Unit,
    pagerState: PagerState,
    moodName: () -> String,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onDeleteConfirmed: () -> Unit,
    onSaveClicked: (Diary) -> Unit,
    onUpdatedDateTime: (ZonedDateTime) -> Unit
) {

    // Update the Mood when selecting an existin Diary
    LaunchedEffect(key1 = uiState.mood) {
        pagerState.scrollToPage(Mood.valueOf(uiState.mood.name).ordinal)
    }

    Scaffold(
        topBar = {
            WriteTopBar(
                selectedDiary = uiState.selectedDiary,
                moodName = moodName,
                onDeleteConfirmed = onDeleteConfirmed,
                onBackPressed = onBackPressed,
                onUpdatedDateTime = onUpdatedDateTime
            )
        },
        content = { paddingValues ->
            WriteContent(
                uiState = uiState,
                paddingValues = paddingValues,
                pagerState = pagerState,
                title = uiState.title,
                onTitleChanged = onTitleChanged,
                description = uiState.description,
                onDescriptionChanged = onDescriptionChanged,
                onSaveClicked = onSaveClicked,
            )
        }
    )
}