package ru.study.darklab.mtstask

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import ru.study.darklab.mtstask.ui.main.LocalPagerAdapter

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initViews()
  }

  private fun initViews() {
    val h = resources.displayMetrics.heightPixels / 2

    findViewById<TextView>(R.id.title_tv).apply {
      height = h
      invalidate()
    }
    val adapter = LocalPagerAdapter(this, listOfView())
    val viewPager: ViewPager = findViewById(R.id.view_pager)
    viewPager.adapter = adapter
    val tabs: TabLayout = findViewById(R.id.tabs)
    tabs.setupWithViewPager(viewPager)

    val coordinatorContainer = findViewById<CoordinatorLayout>(R.id.coordinator_container)
    val openDetailsButton = findViewById<Button>(R.id.open_details_btn).apply {
      setOnClickListener {
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        intent.putExtra(SecondActivity.TRANSFER_DATA_STRING, context.getString(R.string.here_is_turnip))
        startActivity(intent)
      }
    }

    findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
      setOnNavigationItemSelectedListener {
        printlog("click on $it")
        if (it.itemId == R.id.second) {
          openDetailsButton.visibility = View.VISIBLE
          coordinatorContainer.visibility = View.INVISIBLE
        } else {
          coordinatorContainer.visibility = View.VISIBLE
          openDetailsButton.visibility = View.GONE
        }
        return@setOnNavigationItemSelectedListener true
      }
    }
  }

  private fun listOfView(): List<View> {
    val recycler = RecyclerView(this).apply {
      setBackgroundColor(Color.YELLOW)
      layoutManager =
        LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
      adapter = TestAdapter()
    }
    val textView = TextView(this).apply {
      setBackgroundColor(Color.CYAN)
      setTextSize(TypedValue.COMPLEX_UNIT_SP, 28f)
      gravity = Gravity.CENTER
      text = context.getString(R.string.second_item)
    }

    return listOf(recycler, textView)
  }

  class TestAdapter : RecyclerView.Adapter<TestAdapter.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
      val view = LayoutInflater.from(parent.context)
        .inflate(android.R.layout.simple_list_item_1, parent, false)
      return ItemHolder(view)
    }

    override fun getItemCount(): Int = titles.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
      holder.bind(position)
    }

    class ItemHolder(item: View) : RecyclerView.ViewHolder(item) {

      fun bind(position: Int) {
        with(itemView as TextView) {
          setTextSize(TypedValue.COMPLEX_UNIT_SP, 28f)
          gravity = Gravity.CENTER
          setPadding(32, 32, 32, 32)
          text = titles[position]
        }
      }
    }
  }
}