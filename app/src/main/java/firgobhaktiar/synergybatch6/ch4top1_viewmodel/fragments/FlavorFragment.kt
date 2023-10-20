package firgobhaktiar.synergybatch6.ch4top1_viewmodel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import firgobhaktiar.synergybatch6.ch4top1_viewmodel.R
import firgobhaktiar.synergybatch6.ch4top1_viewmodel.databinding.FragmentFlavorBinding
import firgobhaktiar.synergybatch6.ch4top1_viewmodel.viewmodels.OrderViewModel


class FlavorFragment : Fragment() {

    private var binding: FragmentFlavorBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFlavorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnNext?.setOnClickListener {
            if (binding?.rbVanilla?.isChecked == true) {
                sharedViewModel.setFlavor(getString(R.string.vanilla))
            } else if (binding?.rbChocolate?.isChecked == true){
                sharedViewModel.setFlavor(getString(R.string.chocolate))
            } else if (binding?.rbRedVelvet?.isChecked == true){
                sharedViewModel.setFlavor(getString(R.string.red_velvet))
            } else if (binding?.rbSaltedCaramel?.isChecked == true){
                sharedViewModel.setFlavor(getString(R.string.salted_caramel))
            } else if (binding?.rbCoffee?.isChecked == true){
                sharedViewModel.setFlavor(getString(R.string.coffee))
            }
            goToNextPage()
        }

        binding?.btnCancel?.setOnClickListener {
            cancelOrder()
        }

    }

    private fun goToNextPage() {
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }

    private fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_flavorFragment_to_startFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}