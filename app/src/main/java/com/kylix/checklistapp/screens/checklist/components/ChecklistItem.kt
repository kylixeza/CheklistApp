package com.kylix.checklistapp.screens.checklist.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.checklistapp.data.api.response.ChecklistResponse
import com.kylix.checklistapp.ui.theme.Black
import com.kylix.checklistapp.ui.theme.DeepOrange
import com.kylix.checklistapp.ui.theme.LightBlue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChecklistItem(
    modifier: Modifier = Modifier,
    checklist: ChecklistResponse,
    onDeleteChecklist: (Int) -> Unit = {},
) {
    Surface(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 6.dp)
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(8.dp)),
        color = White
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
            ) {
                Text(
                    text = checklist.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Black
                )
                Text(
                    text = "Status: ${if (checklist.checklistCompletionStatus) "Done" else "Not Done"}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LightBlue
                )
            }

            IconButton(
                onClick = {
                    onDeleteChecklist(checklist.id)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = DeepOrange
                )
            }
        }
    }

}