package com.test.kerja.addcontactinfotoaddtransactionformcourotinetwo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.test.kerja.addcontactinfotoaddtransactionformcourotinetwo.databinding.FragmentContactToAddtransactionBinding
import java.lang.RuntimeException


class ContactToAddtransactionFragment : BottomSheetDialogFragment(),View.OnClickListener{
    private val adapter = MyContactsAdapter()
    private lateinit var viewModelSearchContact : MainViewModelSearchContact
    private var mListener: ItemClickListener? = null
    var mListenerContact:ItemContactClickListener? = null
    private var _binding: FragmentContactToAddtransactionBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContactToAddtransactionBinding.inflate(inflater, container, false)
        adapter.setItemContactClickListener{
            mListenerContact?.onItemContactClick(it)
        }
        return binding.root
    }
    fun setItemContactClickListener(itemContactClickListener: ItemContactClickListener){
        this.mListenerContact = itemContactClickListener
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myViewModelFactory =
            MyViewModelSearchContactFactory(requireActivity().application)
        viewModelSearchContact = ViewModelProvider(this, myViewModelFactory)[MainViewModelSearchContact::class.java]
        readContact()

    }
    private fun readContact() {
        binding.rvcontact.layoutManager = LinearLayoutManager(context)
        binding.rvcontact.adapter = adapter
        viewModelSearchContact.myContacts.observe(this, {
            if(it!=null){
                if (it.isNotEmpty()){
                    adapter.submitList(it)
                    binding.progressBar.visibility = View.GONE
                    binding.rvcontact.visibility = View.VISIBLE
                }else{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvcontact.visibility = View.GONE
                }
            }
        })
        binding.searchViewContact.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    viewModelSearchContact.getUsers(p0)
                    viewModelSearchContact.userssearch.observe(viewLifecycleOwner,{
                        adapter.submitList(it)
                    })
                }
                return true
            }

        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = if (context is ItemClickListener){
            context
        }else{
            throw RuntimeException(
                context.toString() + "Must implement ItemClickListener"
            )
        }

    }
    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onClick(v: View?) {
        val tvSelected = v as TextView
        mListener!!.onItemClick(tvSelected.text.toString())
        dismiss()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onItemContactClick(item: MyContact?) {
//        mListenerContact!!.onItemContactClick(item)
//    }

//    private var mListener:ItemClickListener? = null
//    private val adapter = MyContactsAdapter()
//    lateinit var viewModel : MainViewModelSearchContact
//    private var mListenerContact:ItemContactClickListener? = null
//    private var _binding: FragmentContactToAddtransactionBinding?=null
//    private val binding get() = _binding!!
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        _binding = FragmentContactToAddtransactionBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val myViewModelFactory =
//            MyViewModelSearchContactFactory(requireActivity().application)
//        viewModel = ViewModelProvider(this, myViewModelFactory)[MainViewModelSearchContact::class.java]
//        readContact()
//
////        if(ActivityCompat.checkSelfPermission(requireActivity().application, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
////            ActivityCompat.requestPermissions(contextt,Array(1){ Manifest.permission.READ_CONTACTS},120)
////
////        }else{
////            readContact()
////        }
////        /**now call all textView*/
////        text0.setOnClickListener(this)
////        text1.setOnClickListener(this)
////        text2.setOnClickListener(this)
////        text3.setOnClickListener(this)
//    }
//    private fun readContact() {
//        binding.rvcontact.layoutManager = LinearLayoutManager(context)
//        binding.rvcontact.adapter = adapter
//        viewModel.myContacts.observe(this, Observer {
//            if(it!=null){
//                if (it.isNotEmpty()){
//                    adapter.submitList(it)
//                    binding.progressBar.visibility = View.GONE
//                    binding.rvcontact.visibility = View.VISIBLE
//                }else{
//                    binding.progressBar.visibility = View.VISIBLE
//                    binding.rvcontact.visibility = View.GONE
//                }
//            }
//        })
//        binding.searchViewContact.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//
//                return false
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                if (p0 != null) {
//                    viewModel.getUsers(p0)
//                    viewModel.userssearch.observe(viewLifecycleOwner,{
//                        adapter.submitList(it)
//                    })
//                }
//                return true
//            }
//
//        })
//
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
//        mListener = if (context is ItemClickListener){
//            context
//        }else{
//            throw RuntimeException(
//                context.toString() + "Must implement ItemClickListener"
//            )
//        }
//
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        mListener = null
////        mListenerContact = null
//    }
//
//
//
//    override fun onClick(v: View?) {
//        val tvSelected = v as TextView
//        mListener!!.onItemClick(tvSelected.text.toString())
//        dismiss()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//

}