// PizzaPartyScreen.kt
package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil

@Composable
fun PizzaPartyScreen(modifier: Modifier = Modifier, viewModel: PizzaPartyViewModel = viewModel()) {
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        Text(
            text = "Pizza Party",
            fontSize = 38.sp,
            modifier = modifier.padding(bottom = 16.dp)
        )
        NumberField(
            labelText = "Number of people?",
            textInput = viewModel.numPeopleInput,
            onValueChange = { viewModel.numPeopleInput = it },
            modifier = modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )
        RadioGroup(
            labelText = "How hungry?",
            radioOptions = listOf("Light", "Medium", "Hungry", "Very hungry"),
            selectedOption = viewModel.hungerLevel,
            onSelected = { viewModel.hungerLevel = it },
            modifier = modifier
        )
        Text(
            text = "Total pizzas: ${viewModel.totalPizzas}",
            fontSize = 22.sp,
            modifier = modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        Button(
            onClick = {
                viewModel.calculateNumPizzas()
            },
            modifier = modifier.fillMaxWidth()
        ) {
            Text("Calculate")
        }
    }
}

// NumberField.kt
@Composable
fun NumberField(
    labelText: String,
    textInput: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = textInput,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        modifier = modifier
    )
}

// RadioGroup.kt
@Composable
fun RadioGroup(
    labelText: String,
    radioOptions: List<String>,
    selectedOption: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isSelectedOption: (String) -> Boolean = { selectedOption == it }

    Column {
        Text(labelText)
        radioOptions.forEach { option ->
            Row(
                modifier = modifier
                    .selectable(
                        selected = isSelectedOption(option),
                        onClick = { onSelected(option) },
                        role = Role.RadioButton
                    )
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelectedOption(option),
                    onClick = null,
                    modifier = modifier.padding(end = 8.dp)
                )
                Text(
                    text = option,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}

class PizzaCalculator(
    private val numPeople: Int,
    private val hungerLevel: String
) {
    val totalPizzas: Int
        get() {
            // Logic to calculate total pizzas based on numPeople and hungerLevel
            val slicesPerPizza = 8
            val slicesPerPerson = when (hungerLevel) {
                "Light" -> 2
                "Medium" -> 3
                "Hungry" -> 4
                "Very Hungry" -> 5
                else -> 3 // Default case
            }
            return ceil(numPeople * slicesPerPerson / slicesPerPizza.toDouble()).toInt()
        }
}
