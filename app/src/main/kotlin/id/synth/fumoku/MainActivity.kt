package id.synth.fumoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.TooltipCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.mikepenz.aboutlibraries.LibsBuilder
import id.synth.fumoku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val about = binding.searchBar.menu.findItem(R.id.about)
        val avatar = about.actionView as ShapeableImageView

        fun startActivityAbout() {
            startActivity(
                Intent(this, AboutActivity::class.java)
            )
        }

        // App bar logic
        binding.searchBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.about -> {
                    // TODO: Replace with fragment
//                    LibsBuilder()
//                        .withEdgeToEdge(true)
//                        .withSearchEnabled(true)
//                        .activity(this)
                    startActivityAbout()
                    true
                }
                else -> false
            }
        }

        TooltipCompat.setTooltipText(avatar, getString(R.string.about))
        avatar.setOnClickListener {
            startActivityAbout()
        }

        setContentView(binding.root)

        // Set account avatar from image
        Glide.with(this)
            .load(R.drawable.avatar)
            .into(avatar)

        // TODO: Implement RecyclerView
//        binding.recycler
    }
}