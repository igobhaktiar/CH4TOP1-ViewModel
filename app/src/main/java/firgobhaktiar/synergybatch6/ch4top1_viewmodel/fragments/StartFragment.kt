package firgobhaktiar.synergybatch6.ch4top1_viewmodel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import firgobhaktiar.synergybatch6.ch4top1_viewmodel.R
import firgobhaktiar.synergybatch6.ch4top1_viewmodel.databinding.FragmentStartBinding
import firgobhaktiar.synergybatch6.ch4top1_viewmodel.viewmodels.OrderViewModel

class StartFragment : Fragment() {

    private var binding: FragmentStartBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set OnClick Listener
        binding?.btnOneCupcake?.setOnClickListener { orderCupcake(1) }
        binding?.btnSixCupcakes?.setOnClickListener { orderCupcake(6) }
        binding?.btnTwelveCupcakes?.setOnClickListener { orderCupcake(12) }
    }

    // Order Cupcake
    fun orderCupcake (quantity : Int){
        sharedViewModel.setQuantity(quantity)

        if (sharedViewModel.hasNoFlavorSet()){
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }

        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }

    // Destroy Binding
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}