package com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.courseworkandroidweeklyplanner.domain.models.SortStateEnum
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme

class PriorityDialogWindow {
    @Composable
    fun SortDialogWindow(
        selectedOption: SortStateEnum,
        onOptionSelected: (SortStateEnum) -> Unit,
        onDismissRequest: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        var tempSelectedOption by remember(selectedOption) { mutableStateOf(selectedOption) }

        Dialog(onDismissRequest = onDismissRequest) {
            Card(
                modifier = modifier,
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Выберите тип сортировки",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Группа радиокнопок
                    RadioButtonGroupWithEnum(
                        selectedOption = tempSelectedOption,
                        onOptionSelected =  { option ->
                            tempSelectedOption = option
                        }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Кнопки подтверждения и отмены
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(
                            onClick = onDismissRequest,
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(text = "Отмена")
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        TextButton(
                            onClick = {
                                onOptionSelected(tempSelectedOption)
                                onDismissRequest() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(text = "Подтвердить")
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun RadioButtonGroupWithEnum(
        selectedOption: SortStateEnum,
        onOptionSelected: (SortStateEnum) -> Unit
    ) {
        Column {
            SortStateEnum.entries.forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOptionSelected(option) }
                        .padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = (option == selectedOption),
                        onClick = { onOptionSelected(option) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.tertiary,
                            unselectedColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    Text(
                        text = option.description,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Preview(uiMode = UI_MODE_NIGHT_YES)
    @Composable
    fun RadioDialogWindowPreview() {
        CourseWorkAndroidWeeklyPlannerTheme {
            SortDialogWindow(
                selectedOption = SortStateEnum.STANDART,
                onOptionSelected = {},
                onDismissRequest = {}
            )
        }
    }

}