package id.synth.fumoku

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.appcompat.widget.TooltipCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.mikepenz.aboutlibraries.LibsBuilder
import id.synth.fumoku.data.FumoStore
import id.synth.fumoku.databinding.ActivityMainBinding
import id.synth.fumoku.feature.home.FumoAdapter
import id.synth.fumoku.model.Fumo
import android.util.Pair

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val about = binding.searchBar.menu.findItem(R.id.about)
        val avatar = about.actionView as ShapeableImageView

        // Set up transition
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        avatar.transitionName = "avatar"

        fun startActivityAbout() {
            startActivity(
                Intent(this, AboutActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(
                    this, avatar, "avatar"
                ).toBundle(),
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

        // Set account avatar from image
        Glide.with(this)
            .load(R.drawable.avatar)
            .into(avatar)

        fun startActivityFumo(holder: FumoAdapter.ViewHolder, fumo: Fumo) {
            startActivity(
                Intent(this, FumoActivity::class.java).apply {
                    putExtra(FumoActivity.DATA, fumo)
                },
                ActivityOptions.makeSceneTransitionAnimation(
                    this, holder.binding.image, "image",
                ).toBundle(),
            )
        }

        val data = FumoStore.getAll().map { it.first }.sortedBy { it.id }

        with(binding.recycler) {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL,
            )
            adapter = FumoAdapter().apply {
                submit(data)
            }.apply {
                onClickListener = ::startActivityFumo
            }
        }

        setContentView(binding.root)
    }
}