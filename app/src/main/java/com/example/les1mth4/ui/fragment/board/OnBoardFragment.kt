package com.example.les1mth4.ui.fragment.board

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.les1mth4.App
import com.example.les1mth4.R
import com.example.les1mth4.base.BaseFragment
import com.example.les1mth4.databinding.FragmentOnBoardPageBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class OnBoardFragment : BaseFragment<FragmentOnBoardPageBinding>(FragmentOnBoardPageBinding::inflate),
    BoardAdapter.ClickListener {
    private lateinit var adapter: BoardAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun setupUI() {
        adapter = BoardAdapter(this)
        binding.boardPager.adapter = adapter
        binding.springDotsIndicator.setViewPager2(binding.boardPager)

    }
    
    //adapter = BoardAdapter(this)
    //binding.boardPager.adapter = adapter
    //val tabLayout = binding.tabLayout
    //TabLayoutMediator(tabLayout, binding.boardPager) { tab, position ->
        // Задайте название вкладки и другие настройки таба, если это нужно
   // }.attach()
    override fun setupObserver() {
        GoogleSignInClient()
    }

    private fun GoogleSignInClient(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.defaul_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(),gso)
        auth = Firebase.auth
    }

    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) {
            task ->
            if (task.isSuccessful) {
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), task.exception.toString(),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    // Объявляем наш лаунчер результата
    val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Результат успешен
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account.idToken?.let { firebaseAuthWithGoogle(it) }
            } catch (e: ApiException) {
                e.localizedMessage?.let { Log.e("ololo", it) }
            }
        } else {
            // Результат неудачен
        }
    }
    private fun singIn(){
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }
    override fun click() {
        singIn()
        findNavController().navigateUp()
        App.pref.saveBoardState()

    }

    override fun NextCLicked() {
        binding.boardPager.setCurrentItem(++binding.boardPager.currentItem, true)
    }

    override fun SkipClicked() {
        binding.boardPager.setCurrentItem(binding.boardPager.adapter?.itemCount ?: 0, true)
    }
}

