package firgobhaktiar.synergybatch6.ch4top1_viewmodel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar


// price for cupcakes
private const val PRICE_PER_CUPCAKE = 2.00

// additional cost for same day pickup of an order
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00



class OrderViewModel : ViewModel() {

    // quantity of cupcakes in the order
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    // flavor for the cupcakes
    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    // possible date options
    val dateOptions: List<String> = getPickupOptions()

    // pickup date
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    // possible price options
    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = _price.map {
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        // set initial values for the order
        resetOrder()
    }

    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    //set the quantity of cupcakes
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    // update the price of the order
    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE

        _price.value = calculatedPrice
        // add extra for same day pickup
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
    }

    // set the flavor of cupcakes
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    // set the pickup date
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }

    // check if the user has selected a flavor for the cupcakes
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }



    // get the pickup options as a list of strings
    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", java.util.Locale.getDefault())
        val calendar = Calendar.getInstance()

        repeat(4){
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }

        return options
    }

}