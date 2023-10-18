package id.synth.fumoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}