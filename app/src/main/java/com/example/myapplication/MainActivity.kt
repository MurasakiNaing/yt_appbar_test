package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.animation.core.animate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.DummyItemBinding
import com.google.android.material.appbar.AppBarLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { view, insets ->
            val status = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            view.setPadding(0, status.top, 0, 0)
            insets
        }

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.statusBarScrim) { view, insets ->
            val top = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            view.layoutParams.height = top
            view.requestLayout()
            insets
        }

//        binding.appbar.addOnOffsetChangedListener(
//            AppBarLayout.OnOffsetChangedListener { _, offset ->
//
//                val atTop = offset == 0
//
//                binding.statusBarScrim.visibility = if (atTop) View.VISIBLE else View.GONE
//            }
//        )

//        setSupportActionBar(binding.toolbar)
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//
//        val dummyData = (1..50).map { "Video Title $it" }
//        binding.recyclerView.adapter = DummyAdapter(dummyData)
    }

    private class DummyAdapter(private val dataList: List<String>) :
        RecyclerView.Adapter<DummyAdapter.DummyViewHolder>() {

        // The ViewHolder now holds the generated binding class
        class DummyViewHolder(val binding: DummyItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            // Access views via binding: e.g., binding.textItemTitle
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): DummyViewHolder {
            val binding = DummyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return DummyViewHolder(binding)
        }

        override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
            val title = dataList[position]

            // 4. Set data directly on the bound views
            holder.binding.itemText.text = title
            //holder.binding.textItemSubtitle.text =
                //"This is the description for item $title. Scroll down to see the header collapse and the status bar scrim activate!"
        }

        override fun getItemCount(): Int = dataList.size
    }

//    private lateinit var appBarLayout: AppBarLayout
//    private lateinit var recyclerView: RecyclerView
//    private var lastScrollY = 0
//    private var isAppBarVisible = true
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Setup edge-to-edge BEFORE setContentView
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//
//        setContentView(R.layout.activity_main)
//
//        // Make status bar translucent
//        setupTranslucentStatusBar()
//
//        appBarLayout = findViewById(R.id.appBarLayout)
//        recyclerView = findViewById(R.id.recyclerView)
//
//        // Setup RecyclerView with dummy data
//        setupRecyclerView()
//
//        // Setup scroll listener
//        setupScrollBehavior()
//    }
//
//    private fun setupTranslucentStatusBar() {
//        // Modern approach for Android 15+ (API 35+)
//        window.statusBarColor = android.graphics.Color.TRANSPARENT
//        window.navigationBarColor = android.graphics.Color.TRANSPARENT
//
//        // Use WindowInsetsController for modern API
//        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
//        windowInsetsController.isAppearanceLightStatusBars = false // Dark icons on light bg, set to false for light icons
//
//        // Handle insets for proper padding - this ensures status bar stays translucent
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, windowInsets ->
//            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
//
//            // Apply padding to appbar for status bar
//            appBarLayout.setPadding(0, insets.top, 0, 0)
//
//            // Apply padding to recycler view so content isn't hidden
//            recyclerView.setPadding(
//                insets.left,
//                insets.top + appBarLayout.height,
//                insets.right,
//                insets.bottom
//            )
//            recyclerView.clipToPadding = false
//
//            WindowInsetsCompat.CONSUMED
//        }
//    }
//
//    private fun setupRecyclerView() {
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        // Create dummy data
//        val items = List(50) { "Video Item ${it + 1}" }
//        recyclerView.adapter = SimpleAdapter(items)
//    }
//
//    private fun setupScrollBehavior() {
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                val currentScrollY = recyclerView.computeVerticalScrollOffset()
//
//                when {
//                    // Scrolling up or at top - show app bar
//                    dy < 0 || currentScrollY < 10 -> {
//                        showAppBar()
//                    }
//                    // Scrolling down - hide app bar
//                    dy > 0 && currentScrollY > 80 -> {
//                        hideAppBar()
//                    }
//                }
//
//                // Update translucent effect based on scroll position
//                updateAppBarTranslucency(currentScrollY)
//
//                lastScrollY = currentScrollY
//            }
//        })
//    }
//
//    private fun showAppBar() {
//        if (!isAppBarVisible) {
//            appBarLayout.animate()
//                .translationY(0f)
//                .setDuration(200)
//                .withStartAction {
//                    // Keep status bar translucent when showing
//                    updateStatusBarTranslucency(true)
//                }
//                .start()
//            isAppBarVisible = true
//        }
//    }
//
//    private fun hideAppBar() {
//        if (isAppBarVisible) {
//            appBarLayout.animate()
//                .translationY(-appBarLayout.height.toFloat())
//                .setDuration(200)
//                .withEndAction {
//                    // Keep status bar translucent when hidden
//                    updateStatusBarTranslucency(true)
//                }
//                .start()
//            isAppBarVisible = false
//        }
//    }
//
//    private fun updateStatusBarTranslucency(translucent: Boolean) {
//        // Ensure status bar stays transparent
//       window.statusBarColor = 0x66000000
//
//    }
//
//    private fun updateAppBarTranslucency(scrollY: Int) {
//        // Calculate alpha from 0.0 (transparent) to 0.95 (almost opaque)
//        val alpha = (scrollY / 100f).coerceIn(0f, 0.95f)
//        appBarLayout.background?.alpha = (alpha * 255).toInt()
//    }
//}
//
//    class SimpleAdapter(private val items: List<String>) :
//        RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
//
//        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//            val textView: TextView = view.findViewById(R.id.itemText)
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.dummy_item, parent, false)
//            return ViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder.textView.text = items[position]
//        }
//
//        override fun getItemCount() = items.size

}