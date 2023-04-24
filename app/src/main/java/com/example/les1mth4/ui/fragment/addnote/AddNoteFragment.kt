package com.example.les1mth4.ui.fragment.addnote

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.provider.MediaStore
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.les1mth4.App
import com.example.les1mth4.base.BaseFragment
import com.example.les1mth4.data.model.NoteModel
import com.example.les1mth4.databinding.FragmentAddNoteBinding
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

private const val PICK_IMAGE_REQUEST = 1
class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {

    private var imageUrl = ""

    override fun setupUI() {

    }

    val date = getCurrentDateTime()
    val dateInString = date.toString("yyy`/MM/dd HH:mm:ss")

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    override fun setupObserver() {
        pickImage()
        binding.send.setOnClickListener {
            val title = binding.title.text.toString()
            val des = binding.description.text.toString()
            App.dataBase.getDao().addNote(
                NoteModel(
                    image = imageUrl,
                    title = title,
                    description = des,
                    date = dateInString
                )
            )
            findNavController().navigateUp()
        }
    }

    @SuppressLint("IntentReset")
    private fun pickImage(){
        binding.imgBoss.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type= "image/"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageUrl = data?.data.toString()
        if (requestCode == PICK_IMAGE_REQUEST && requestCode == RESULT_OK && data?.data != null)
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, data.data)
                binding.imgBoss.setImageBitmap(bitmap)
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            }catch (e: IOException){
                e.printStackTrace()
            }
    }
}