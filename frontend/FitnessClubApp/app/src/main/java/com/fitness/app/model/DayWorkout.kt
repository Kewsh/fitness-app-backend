package com.fitness.app.model

data class DayWorkout(
    val title:String,
    val isChecked: Int,
    val workouts:List<Workout>,
)
