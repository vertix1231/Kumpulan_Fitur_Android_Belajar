package com.test.kerja.addcontactinfotoaddtransactionformcourotinetwo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.test.kerja.addcontactinfotoaddtransactionformcourotinetwo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),ItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactToAddtransactionFragment: ContactToAddtransactionFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,Array(1){ Manifest.permission.READ_CONTACTS},120)

        }else{
            readContact()
        }

//        val myViewModelFactory =
//            MyViewModelSearchContactFactory(application)
//        viewModel = ViewModelProvider(this, myViewModelFactory)[MainViewModelSearchContact::class.java]
//
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,Array(1){ Manifest.permission.READ_CONTACTS},120)
//
//        }else{
//            readContact()
//        }

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode==120&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            readContact()
        }
    }

    private fun readContact() {
        contactToAddtransactionFragment = ContactToAddtransactionFragment()
        binding.btnCariKontak.setOnClickListener {
            openBottomSheet()
        }
//        contactToAddtransactionFragment.setItemContactClickListener{
//            binding.textViewNama.text = it?.name
//            binding.textViewNomor.text = it?.number
//        }

    }

    fun openBottomSheet(){
        val addPhotoBottomDialogFragment = ActionBottom.newInstance()
        addPhotoBottomDialogFragment.show(
            supportFragmentManager,ActionBottom.TAG
        )
        addPhotoBottomDialogFragment.setItemContactClickListener{
            binding.textViewNama.text = it?.name
            binding.textViewNomor.text = it?.number

            addPhotoBottomDialogFragment.dismiss()
        }

    }

    override fun onItemClick(item: String?) {

    }

//    override fun onItemContactClick(item: MyContact?) {
//        if (item != null) {
//            binding.textViewNama.text = item.name
//            binding.textViewNomor.text = item.number
//        }
//
//    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if(requestCode==120&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//            readContact()
//        }
//    }

//    private fun readContact() {
//        binding.rvcontact.layoutManager = LinearLayoutManager(this)
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
//                    viewModel.userssearch.observe(this@MainActivity,{
//                        adapter.submitList(it)
//                    })
//                }
//                return true
//            }
//
//        })
//
//    }



//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.option_menu, menu)
//
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView = menu.findItem(R.id.search)?.actionView as androidx.appcompat.widget.SearchView
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//
//                return false
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                if (p0 != null) {
//                    viewModel.getUsers(p0)
//                    viewModel.userssearch.observe(this@MainActivity,{
//                        adapter.submitList(it)
//                    })
//                }
//                return true
//            }
//
//        })
//
//        return true
//
//    }

}