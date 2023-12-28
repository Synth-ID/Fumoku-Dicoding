package id.synth.fumoku

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bumptech.glide.Glide
import com.google.android.material.transition.platform.MaterialArcMotion
import id.synth.fumoku.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)

        val avatar = binding.avatar

        // Set up transition
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        avatar.transitionName = "avatar"

        val arcMotion = MaterialArcMotion()
        window.sharedElementEnterTransition.pathMotion = arcMotion
        window.sharedElementReturnTransition.pathMotion = arcMotion

        // App bar logic
        binding.appBar.setNavigationOnClickListener {
            finishAfterTransition()
        }

        setContentView(binding.root)

        // Set account avatar from image
        Glide.with(this)
            .load(R.drawable.avatar)
            .circleCrop()
            .into(binding.avatar)
    }
}