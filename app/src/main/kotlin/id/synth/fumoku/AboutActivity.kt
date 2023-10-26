package id.synth.fumoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeImageTransform
import android.view.Window
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bumptech.glide.Glide
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.google.android.material.transition.platform.MaterialSharedAxis
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
        window.enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        window.allowEnterTransitionOverlap = true
        window.returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        window.allowReturnTransitionOverlap = true

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