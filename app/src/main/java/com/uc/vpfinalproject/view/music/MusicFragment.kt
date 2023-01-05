package com.uc.vpfinalproject.view.music

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.viewmodel.music.MusicViewModel
import com.uc.vpfinalproject.databinding.FragmentMusicBinding

class MusicFragment : Fragment() {

    private var _binding: FragmentMusicBinding? = null
    private lateinit var mp: MediaPlayer
    private var totalTime: Int = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var status = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(MusicViewModel::class.java)

        _binding = FragmentMusicBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.songslistCV.visibility = GONE

        mediaplayer(R.raw.lagu)

        binding.playBtn.setOnClickListener(){
            playBtnClick()
        }

        binding.openlistFAB.setOnClickListener(){
            binding.songslistCV.visibility = VISIBLE
            binding.openlistFAB.visibility = GONE
        }

        binding.laguTV.setOnClickListener(){
            mp.pause()
            mp.reset()
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(context, notification)
            r.play()
            mediaplayer(R.raw.lagu)
            binding.songtitleTV.text = "Choir Music"
            binding.playBtn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
            binding.songslistCV.visibility = GONE
            binding.openlistFAB.visibility = VISIBLE
        }

        binding.fluteTV.setOnClickListener(){
            mp.pause()
            mp.reset()
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(context, notification)
            r.play()
            mediaplayer(R.raw.flute)
            binding.songtitleTV.text = "Flute Music"
            binding.playBtn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
            binding.songslistCV.visibility = GONE
            binding.openlistFAB.visibility = VISIBLE
        }

        binding.waterTV.setOnClickListener(){
            mp.pause()
            mp.reset()
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(context, notification)
            r.play()
            mediaplayer(R.raw.water)
            binding.songtitleTV.text = "Water Music"
            binding.playBtn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
            binding.songslistCV.visibility = GONE
            binding.openlistFAB.visibility = VISIBLE
        }

        binding.namasteTV.setOnClickListener(){
            mp.pause()
            mp.reset()
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(context, notification)
            r.play()
            mediaplayer(R.raw.namaste)
            binding.songtitleTV.text = "Namaste Music"
            binding.playBtn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
            binding.songslistCV.visibility = GONE
            binding.openlistFAB.visibility = VISIBLE
        }

        binding.dismisslistBTN.setOnClickListener(){
            binding.songslistCV.visibility = GONE
            binding.openlistFAB.visibility = VISIBLE
        }

        return root
    }

    private fun mediaplayer(lagu: Int) {
        mp = MediaPlayer.create(context, lagu)
        mp.isLooping = true
        mp.setVolume(0.5f, 0.5f)
        totalTime = mp.duration

        binding.volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekbar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        var volumeNum = progress / 100.0f
                        mp.setVolume(volumeNum, volumeNum)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )

        // Position Bar
        binding.positionBar.max = totalTime
        binding.positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        mp.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )

        // Thread
        Thread(Runnable {
            while (mp != null) {
                try {
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if(status){
                var currentPosition = msg.what

                // Update positionBar
                binding.positionBar.progress = currentPosition

                // Update Labels
                var elapsedTime = createTimeLabel(currentPosition)
                binding.elapsedTimeLabel.text = elapsedTime

                var remainingTime = createTimeLabel(totalTime - currentPosition)
                binding.remainingTimeLabel.text = "-$remainingTime"
            }
        }
    }

    fun createTimeLabel(time: Int): String {
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }

    fun playBtnClick() {

        if (mp.isPlaying) {
            // Stop
            mp.pause()
            binding.playBtn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)

        } else {
            // Start
            mp.start()
            binding.playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_24)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        status = false
        mp.reset()
        _binding = null
    }
}