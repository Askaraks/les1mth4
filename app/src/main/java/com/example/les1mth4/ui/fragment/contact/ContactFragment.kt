package com.example.les1mth4.ui.fragment.contact

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import com.example.les1mth4.base.BaseFragment
import com.example.les1mth4.data.model.ContactModel
import com.example.les1mth4.databinding.FragmentContactBinding



class ContactFragment(contactFragment: ContactFragment)
    : BaseFragment<FragmentContactBinding>(FragmentContactBinding::inflate),
    ContactAdapter.ShareContactListener  {
    private val adapter by lazy { ContactFragment(this) }
    override fun setupUI() {
        binding.rvNote.adapter = adapter
        getContact()

    }
    @SuppressLint("Range")
    private fun getContact(){
        val list = arrayListOf<ContactModel>()
        val contentResolver = requireActivity().contentResolver
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )
        if (cursor?.count!! > 0){
            while (cursor.moveToNext())
                if (Integer.parseInt
                        (cursor.getString
                        (cursor.getColumnIndex
                        (ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name =
                        cursor.
                        getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val phoneCursor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                    arrayOf(id),
                        null
                    )
                    if (phoneCursor?.moveToNext()!!) {
                        val phoneNumber =
                            phoneCursor.getString(phoneCursor.getColumnIndex
                                (ContactsContract.CommonDataKinds.Phone.NUMBER))
                        phoneCursor.close()
                        list.add(ContactModel(name = name, phone = phoneNumber,))
                    }
                    phoneCursor.close()
                }
            cursor.close()
            adapter.setList(list)
        }

    }

    override fun shareCall(number: String) {
        AlertDialog.Builder(requireContext()).setTitle("Перейти по номеру $number ?")
            .setNegativeButton("Да", null)
            .setPositiveButton("нет") { _, _ ->
                val intent = Intent(Intent.ACTION_DIAL,
                    Uri.fromParts("tel", number, null))
                requireActivity().startActivity(intent)

            }.show()

    }

    override fun shareWhatsApp(number: String) {
        AlertDialog.Builder(requireContext()).setTitle("Перейти по номеру $number ?")
            .setNegativeButton("Да", null)
            .setPositiveButton("нет") { _, _ ->
                val url = "https://api.whatsapp.com/send?phone=$number"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                requireActivity().startActivity(intent)
    }.show()


}