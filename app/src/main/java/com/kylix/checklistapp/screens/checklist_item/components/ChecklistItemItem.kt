package com.kylix.checklistapp.screens.checklist_item.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.checklistapp.data.api.response.ChecklistItemResponse
import com.kylix.checklistapp.ui.theme.Black
import com.kylix.checklistapp.ui.theme.DeepBlue
import com.kylix.checklistapp.ui.theme.DeepOrange

@Composable
fun ChecklistItemItem(
    modifier: Modifier = Modifier,
    item: ChecklistItemResponse,
    onDelete: (Int) -> Unit = {},
    onUpdateStatus : (Int) -> Unit = {},
    onSelectChecklistItem: (Int) -> Unit = {}
) {

    Surface(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 6.dp)
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(8.dp))
            .clickable { onSelectChecklistItem(item.id) },
        color = Color.White
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
            ) {
                Text(
                    text = item.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Black
                )
                Text(
                    text = "Status: ${if (item.itemCompletionStatus) "Done" else "Not Done"}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (item.itemCompletionStatus) DeepBlue else Color.Red
                )
            }

            Row {
                IconButton(
                    onClick = {
                        onUpdateStatus(item.id)
                    }
                ) {
                    if (item.itemCompletionStatus) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Mark as Not Done",
                            tint = Color.Red
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Mark as Done",
                            tint = DeepBlue
                        )
                    }
                }

                IconButton(
                    onClick = {
                        onDelete(item.id)
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

}