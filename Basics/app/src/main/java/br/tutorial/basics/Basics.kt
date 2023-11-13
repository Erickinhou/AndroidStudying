package br.tutorial.basics

class Car(val model: String, val brand: String){
    fun showOf(): String{
        return "You drive a $brand - $model"
    }
}

fun main(args: Array<String>){

    val numbers = arrayListOf<Double>(1.0, 2.0, 3.0, 6.0, 10.0)
    var sum: Double = 0.0
    for (number in numbers){
        sum += number
    }


    println("average is ${sum / numbers.size}")
    println("average using the list is ${numbers.average()}")

}
// Subclasses of a class that implements an interface
// (in this case, ElectricCar) are also considered
// to be implementing the interface.
