package com.kylix.checklistapp.screens.checklist_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.kylix.checklistapp.screens.checklist.components.ChecklistItem
import com.kylix.checklistapp.data.api.response.ChecklistItemResponse
import com.kylix.checklistapp.screens.checklist_item.components.ChecklistItemItem
import com.kylix.checklistapp.ui.theme.DeepBlue
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChecklistItemScreen(
    modifier: Modifier = Modifier,
    checklistId: Int,
    viewModel: ChecklistItemViewModel = koinViewModel(),
    onAddChecklistItem: () -> Unit = {},
    onSelectChecklistItem: (Int) -> Unit = {}
) {

    val checklistItems by viewModel.checklistItems.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.getChecklistItems(checklistId)
            }
        }

        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onAddChecklistItem() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepBlue
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            tint = Color.White,
                            contentDescription = "Add Icon",
                        )
                        Text(
                            text = "New Checklist Item"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(checklistItems) {
                    ChecklistItemItem(
                        item = it,
                        onDelete = { id -> viewModel.deleteItem(checklistId, id) },
                        onUpdateStatus = { id -> viewModel.updateItemStatus(checklistId, id) },
                        onSelectChecklistItem = { id -> onSelectChecklistItem(id) }
                    )
                }
            }
        }
    }
}