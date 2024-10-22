package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.ceil

class PizzaPartyViewModel : ViewModel() {
    var numPeopleInput by mutableStateOf("")
    var hungerLevel by mutableStateOf("Hungry") // Default to "Hungry"
    var totalPizzas by mutableIntStateOf(0)
        private set

    fun calculateNumPizzas() {
        val numPeople = numPeopleInput.toIntOrNull() ?: 0
        val slicesPerPizza = 8
        val slicesPerPerson = when (hungerLevel) {
            "Light" -> 2
            "Medium" -> 3
            "Hungry" -> 4
            else -> 5 // "Very hungry" or any other input
        }

        totalPizzas = ceil(numPeople * slicesPerPerson / slicesPerPizza.toDouble()).toInt()
    }
}
