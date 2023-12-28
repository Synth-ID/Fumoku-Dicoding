package id.synth.fumoku

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeImageTransform
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.google.android.material.transition.platform.MaterialSharedAxis
import id.synth.fumoku.data.FumoStore
import id.synth.fumoku.databinding.ActivityFumoBinding
import id.synth.fumoku.model.Fumo

class FumoActivity : AppCompatActivity() {
    companion object {
        const val DATA = "fumo_data"
    }

    private lateinit var binding: ActivityFumoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityFumoBinding.inflate(layoutInflater)

        val layout = binding.layout
        val image = binding.image

        // Set up transition
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        image.transitionName = "image"

        val arcMotion = MaterialArcMotion()
        window.sharedElementEnterTransition.pathMotion = arcMotion
        window.sharedElementReturnTransition.pathMotion = arcMotion

        // App bar logic
        binding.appBar.setNavigationOnClickListener {
            finishAfterTransition()
        }

        // Deserialize parcel
        val fumo =
            if (Build.VERSION.SDK_INT >= 33)
                intent.getParcelableExtra(DATA, Fumo::class.java)!!
            else
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(DATA)!!

        with(binding) {
            id.text = fumo.idPadded
            character.text = fumo.character
            name.text = fumo.name

            type.text = fumo.type.toString()

            // Image
            Glide.with(root)
                .load(fumo.image)
                .into(image)
        }

        // Detail content
        val detail = FumoStore.get(fumo.id)!!.second

        with(binding) {
            if (detail.releaseYears != null) {
                for (year in detail.releaseYears) {
                    releaseYears.addView(
                        Chip(this@FumoActivity).apply {
                            text = year.toString()
                            isClickable = true
                            isFocusable = true
                        }
                    )
                }
            } else {
                releaseYearsLayout.visibility = View.GONE
            }

            if (detail.rarity != null) {
                rarity.text = detail.rarity.toString()
            } else {
                rarityLayout.visibility = View.GONE
            }

            if (detail.secondhandCost != null) {
                secondhandCost.text = detail.secondhandCost.toString()
            } else {
                secondhandCostLayout.visibility = View.GONE
            }

            if (detail.priceRangeUSD != null) {
                @SuppressLint("SetTextI18n")
                priceRange.text = "\$${detail.priceRangeUSD.lower.toInt()} - \$${detail.priceRangeUSD.upper.toInt()}"
            } else {
                priceRangeLayout.visibility = View.GONE
            }

            if (detail.link != null) {
                link.setOnClickListener {
                    startActivity(
                        Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(detail.link.toString())
                        }
                    )
                }
            } else {
                link.visibility = View.GONE
            }
        }

        setContentView(binding.root)
    }
}