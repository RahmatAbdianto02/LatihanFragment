package com.dicoding.latihanfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

//Kode ini mendefinisikan sebuah class bernama DetailCategoryFragment yang mewakili
// sebuah fragmen di aplikasi Android. Fragmen ini digunakan untuk menampilkan detail dari sebuah kategori.
class DetailCategoryFragment : Fragment(), View.OnClickListener {

    private lateinit var tvCategoryName: TextView
    private lateinit var tvCategoryDescription: TextView
    private lateinit var btnProfile: Button
    private lateinit var btnShowDialog: Button
    var description: String? = null

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"
    }

    internal var optionDialogListener: OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener {
        override fun onOptionChosen(text: String?) {
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCategoryName = view.findViewById(R.id.tv_category_name)
        tvCategoryDescription = view.findViewById(R.id.tv_category_description)
        btnProfile = view.findViewById(R.id.btn_profile)
        btnShowDialog = view.findViewById(R.id.btn_show_dialog)

        if (savedInstanceState != null) {
            val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)
            description = descFromBundle
        }

        if (arguments != null) {
            val categoryName = arguments?.getString(EXTRA_NAME)
            tvCategoryName.text = categoryName
            tvCategoryDescription.text = description
        }

        btnShowDialog.setOnClickListener{
            val optionDialogFragment = OptionDialogFragment()

            val fragmentManager = childFragmentManager
            optionDialogFragment.show(fragmentManager,optionDialogFragment::class.java.simpleName)
        }
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_show_dialog) {
            val detailCategoryFragment = DetailCategoryFragment()

            val bundle = Bundle()
            bundle.putString(DetailCategoryFragment.EXTRA_NAME, "LifeStyle")
            val description = "Kategori ini akan berisi produk produk lifestyle"

            detailCategoryFragment.arguments = bundle
            detailCategoryFragment.description = description

            val fragmentManager = parentFragmentManager
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_container, detailCategoryFragment, DetailCategoryFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }
}
