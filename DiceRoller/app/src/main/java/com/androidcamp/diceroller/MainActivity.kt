package com.androidcamp.diceroller
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.androidcamp.diceroller.ui.home.Player
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var isWin = false
    private var playerTurn = 0

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)






        Player.setPlayerCount(2)


        buttonRoll.setOnClickListener {
            if (!isWin) {


                Player.playerList[playerTurn].score += rollDice()
                val currentScore = Player.playerList[playerTurn].score
                Log.i("$Player.", "$currentScore")
                if (currentScore >= Player.WIN_SCORE) {
                    val playerName = Player.playerList[playerTurn].name
                    Toast.makeText(this, "WIN: $playerName", Toast.LENGTH_LONG).show()
                    isWin = true

                }
                playerTurn++
                if (playerTurn == Player.playerList.size) {
                    playerTurn = 0
                }
                val playerIdenticatior = playerTurn+1
                playerName.text = "Player: $playerIdenticatior"
            } else {
                Toast.makeText(this, "Start new game from menu", Toast.LENGTH_LONG).show()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun rollDice(): Int {

        val randomInt = Random().nextInt(6) + 1

        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)
        return randomInt
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_reset -> {
                Toast.makeText(this, "Started new game", Toast.LENGTH_LONG).show()
                Player.reset()
                playerName.text = "Player: 1"
                isWin = false
                return true
            }
            R.id.task1_homework ->{
                val intent = Intent(this, FirstTask::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }

}
