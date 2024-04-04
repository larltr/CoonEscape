package com.angelika.coonescape.ui.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Rect
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.angelika.coonescape.R
import com.angelika.coonescape.databinding.FragmentGameBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameFragment : Fragment(R.layout.fragment_game) {

    private val binding by viewBinding(FragmentGameBinding::bind)
    private val isPofigLiveData = MutableLiveData(false)
    private var bool: Boolean = true
    private lateinit var horizontalAnim: ViewPropertyAnimator
    private lateinit var verticalAnim: ViewPropertyAnimator
    private lateinit var animatorRaccoon: ObjectAnimator
    private lateinit var animatorTree: ObjectAnimator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivTree2.visibility = View.VISIBLE

//        animateRaccoon()
        trackTree1Movement()
        animateTree()
        treeVerticalAnimate()
        treeFallClick()
//        checkRaccoonOutOfBounds()
        raccoonAnimation()
        animateRaccoonWithTree()
        bearAnimation()
        clickPauseAndMenu()
    }

//    private fun checkRaccoonOutOfBounds() {
//        val raccoonLocation = IntArray(2)
//        binding.ivRaccoon.getLocationOnScreen(raccoonLocation)
//        val raccoonY = raccoonLocation[1] + binding.ivRaccoon.height
//        val screenHeight = resources.displayMetrics.heightPixels
//        if (raccoonY > screenHeight) {
//            findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
//        }
//    }

    private fun animateRaccoonWithTree() {
        val raccoon = binding.ivRaccoon
        val tree2 = binding.ivTree2
        val endY = resources.displayMetrics.heightPixels.toFloat()
        val duration = 10000L

        animatorTree = ObjectAnimator.ofFloat(tree2, "translationY", endY).apply {
            this.duration = duration
        }
        animatorRaccoon = ObjectAnimator.ofFloat(raccoon, "translationY", endY).apply {
            this.duration = duration
            addUpdateListener { animator ->
                val raccoonY = raccoon.y + raccoon.height
                val screenHeight = resources.displayMetrics.heightPixels.toFloat()
                if (raccoonY > screenHeight) {
                    findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                    cancel()
                    animatorTree.cancel()
                }
            }
        }
        animatorTree.start()
        animatorRaccoon.start()
    }

    private fun animateRaccoon() {
        val raccoon = binding.ivRaccoon
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()
        val startY = screenHeight / 2
        val endY = 850f
        val duration = 5000L
        raccoon.translationY = startY
        val animator = ValueAnimator.ofFloat(startY, endY).apply {
            this.duration = duration
            addUpdateListener { valueAnimator ->
                val animatedValue = valueAnimator.animatedValue as Float
                raccoon.translationY = animatedValue
            }
        }
        animator.start()
    }

    private fun trackTree1Movement() {
        val tree1 = binding.ivTree1
        val container = binding.containerTree

        tree1.addOnLayoutChangeListener { _, left, top, right, bottom, _, _, _, _ ->
            val tree1Rect = Rect(left, top, right, bottom)
            val containerRect = Rect()

            container.getGlobalVisibleRect(containerRect)

            if (Rect.intersects(tree1Rect, containerRect)) {
                binding.ivTree2.visibility = View.VISIBLE
            } else {
                binding.ivTree2.visibility = View.INVISIBLE
            }
        }
    }

    private fun animateTree() {
        val rightXOffset = 0f
        val leftXOffset = resources.displayMetrics.widthPixels - 200f

        isPofigLiveData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                if (isPofigLiveData.value!!) {
                    treeLeftRightAnimate(leftXOffset)
                    if (bool) {
                        delay(1000)
                        isPofigLiveData.value = false
                    }
                } else {
                    treeLeftRightAnimate(rightXOffset)
                    if (bool) {
                        delay(1000)
                        isPofigLiveData.value = true
                    }
                }
            }
        }
    }

    private fun treeLeftRightAnimate(
        offset: Float
    ) {
        horizontalAnim = binding.ivTree1.animate().apply {
            duration = 800
            x(offset)
            start()
        }
    }

    private fun treeVerticalAnimate() {
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()

        verticalAnim = binding.ivTree2.animate().apply {
            duration = 10000
            translationY(screenHeight + 100f)
            setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (isAdded) {
                        binding.ivTree3.visibility = View.INVISIBLE
                    }
                }
            })
            start()
        }

        binding.ivTree3.animate().apply {
            duration = 10000
            translationY(screenHeight + 200f)
            setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (isAdded) {
                        binding.ivTree3.visibility = View.INVISIBLE
                    }
                }
            })
            start()
        }

        binding.ivTree4.animate().apply {
            duration = 10000
            translationY(screenHeight + 300f)
            setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (isAdded) {
                        binding.ivTree4.visibility = View.INVISIBLE
                    }
                }
            })
            start()
        }
    }


    private fun treeFallClick() {
        binding.root.setOnClickListener {
            horizontalAnim.cancel()
            trackTree1Movement()
            bool = false
            startTreeFallAnimation()
        }
    }

    private fun raccoonAnimation() {
        val animationRaccoon = AnimationDrawable().apply {
            addFrame(resources.getDrawable(R.drawable.image_raccoon_1), 150)
            addFrame(resources.getDrawable(R.drawable.image_raccoon_2), 150)
            addFrame(resources.getDrawable(R.drawable.image_raccoon_3), 150)
            isOneShot = false
        }
        binding.ivRaccoon.setImageDrawable(animationRaccoon)
        animationRaccoon.start()
    }

    private fun bearAnimation() {
        val animationBear = AnimationDrawable().apply {
            addFrame(resources.getDrawable(R.drawable.image_bear_2), 150)
            addFrame(resources.getDrawable(R.drawable.image_bear_1), 150)
            addFrame(resources.getDrawable(R.drawable.image_bear_3), 150)
            isOneShot = false
        }
        binding.ivBear.setImageDrawable(animationBear)
        animationBear.start()
    }

    private fun clickPauseAndMenu() = with(binding) {
        ivPause.setOnClickListener {
            animatorRaccoon.cancel()
            animatorTree.cancel()
            findNavController().navigate(R.id.action_gameFragment_to_pauseFragment)
            stopAnimations()
        }
        ivMenu.setOnClickListener {
            animatorRaccoon.cancel()
            animatorTree.cancel()
            findNavController().navigate(R.id.action_gameFragment_to_menuFragment)
        }
    }

    private fun startTreeFallAnimation() {
        val fallAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.tree_fall)
        fallAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.ivTree2.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
                bool = true
                animateTree()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
        binding.ivTree1.startAnimation(fallAnimation)
    }

    private fun stopAnimations() {
        horizontalAnim.cancel()
        verticalAnim.cancel()
    }
}