package id.synth.fumoku

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.mikepenz.aboutlibraries.LibsBuilder
import id.synth.fumoku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.appBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.about -> {
                    LibsBuilder()
                        .withEdgeToEdge(true)
                        .withSearchEnabled(true)
                        .start(this)
                    true
                }
                else -> false
            }
        }

        setContentView(binding.root)

        Glide.with(this)
            .load(R.drawable.account)
            .circleCrop()
            .into(object : CustomTarget<Drawable>() {
                private val about: MenuItem = binding.appBar.menu.findItem(R.id.about)

                override fun onResourceReady(icon: Drawable, transition: Transition<in Drawable>?) {
                    about.icon = icon
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    about.icon = AppCompatResources.getDrawable(
                        this@MainActivity,
                        R.drawable.account_circle,
                    )
                }

            })
    }
}