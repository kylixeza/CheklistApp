package com.kylix.checklistapp.screens.checklist_item.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.checklistapp.ui.theme.DeepBlue
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailChecklistItemScreen(
    modifier: Modifier = Modifier,
    checklistId: Int,
    itemId: Int,
    viewModel : DetailChecklistItemViewModel = koinViewModel(),
    onBack: () -> Unit = {}
) {

    val item by viewModel.item.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getItem(checklistId, itemId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Detail Checklist Item",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily.Default
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = onBack
                        ) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = null,
                                tint = DeepBlue,
                            )
                        }
                    }
                )
                Divider(
                    color = Color(0xFFE0E0E0),
                    thickness = 1.dp
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "What you need to do?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default

            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = item?.name ?: "",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Default
            )
        }
    }
}