package com.androidcamp.customprogressbar

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat


class MyProgressBar : LinearLayout {


    private val maxProgress = 100
    private val defCornerRadius = 20f
    private val defBackgroundColor = Color.LTGRAY
    private val defProgressColor = Color.GRAY
    private val defIsRestart = false
    private val defGradientMovement = false


    private var isRounded = false
    private var isRestart = false
    private var gradientMovement = false


    private var mCornerRadius = 20f
    private var mBackgroundColor = Color.LTGRAY
    private var mProgressColor = Color.GRAY

    private var mGradientColors = intArrayOf()

    private var oldProgress = 0
    private var currentProgress = 0


    private var progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val clipPath = Path()
    private var progressRectF = RectF()
    private var backRectF = RectF()
    private var doOnProgressEnd: ((v: View) -> Unit)? = null


    constructor(context: Context) : super(context) {
        initUI(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        initUI(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initUI(context, attrs)
    }

    private fun initUI(context: Context, attrs: AttributeSet?) {
        setWillNotDraw(false)

        attrs?.let {
            val array: TypedArray =
                context.obtainStyledAttributes(it, R.styleable.MyProgressBar)
            if (array.length() > 0) {
                mBackgroundColor = array.getColor(
                    R.styleable.MyProgressBar_fpl_backgroundColor,
                    defBackgroundColor
                )
                mProgressColor = array.getColor(
                    R.styleable.MyProgressBar_fpl_progressColor,
                    defProgressColor
                )

                val restart =
                    array.getBoolean(R.styleable.MyProgressBar_fpl_shouldRestart, defIsRestart)
                shouldStartFromZero(restart)

                val cornerRadius =
                    array.getFloat(
                        R.styleable.MyProgressBar_fpl_roundedCornerRadius,
                        defCornerRadius
                    )
                setCornerRadius(cornerRadius)

                val isRounded =
                    array.getBoolean(R.styleable.MyProgressBar_fpl_isRounded, true)

                setRoundedCorners(isRounded)



                val progress =
                    array.getFloat(R.styleable.MyProgressBar_fpl_progress, currentProgress.toFloat())
                setProgress(progress.toInt())



                val gradMovement =
                    array.getBoolean(R.styleable.MyProgressBar_fpl_gradientMovement, defGradientMovement)
                setGradientMovement(gradMovement)

                try {
                    val colorsId =
                        array.getResourceId(R.styleable.MyProgressBar_fpl_gradientColors, 0)
                    val gradColors = array.resources.getIntArray(colorsId)
                    if (gradColors.isNotEmpty())
                        setProgressColors(gradColors, false)
                } catch (e: Exception) {

                }
            }
            array.recycle()
        }
        initPaint()
    }

    private fun initPaint() {
        backgroundPaint.apply {
            style = Paint.Style.FILL
            color = mBackgroundColor
        }
        progressPaint.apply {
            style = Paint.Style.FILL
            color = if (mGradientColors.isEmpty()) mProgressColor else Color.BLACK
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        backRectF = RectF(0f, 0f, w.toFloat(), h.toFloat())
        progressRectF = RectF(0f, 0f, w.toFloat(), h.toFloat())
        progressRectF.bottom = h.toFloat()
        updateRect(progressRectF)
        if (isRounded) {
            clipPath.addRoundRect(backRectF, mCornerRadius, mCornerRadius, Path.Direction.CW)
            clipPath.close()
        }

    }

    private fun getProgress() = getSize() * currentProgress / 100

    private fun getSize() =
         width

    private fun updateRect(rectF: RectF) {

            rectF.right = getProgress().toFloat()


    }


    override fun onDraw(canvas: Canvas?) {
        if (isRounded) {
            drawRoundedProgress(canvas)
        } else {
            drawNormalProgress(canvas)
        }
        super.onDraw(canvas)
    }

    private fun drawNormalProgress(canvas: Canvas?) {
        canvas?.apply {
            drawRect(backRectF, backgroundPaint)
            applyGradientIfAny()
            drawRect(progressRectF, progressPaint)
        }
    }

    private fun drawRoundedProgress(canvas: Canvas?) {
        canvas?.apply {
            save()
            drawRoundRect(backRectF, mCornerRadius, mCornerRadius, backgroundPaint)
            clipPath(clipPath)
            applyGradientIfAny()
            drawRect(progressRectF, progressPaint)
            restore()
        }
    }

    private fun applyGradientIfAny() {
        if (mGradientColors.isNotEmpty()) {
            val gradientRect = getGradientRect(if (gradientMovement) progressRectF else backRectF)
            progressPaint.shader = LinearGradient(
                gradientRect.left,//x0
                gradientRect.top,//y0
                gradientRect.right,//x1
                gradientRect.bottom,//y1
                mGradientColors,
                null,
                Shader.TileMode.MIRROR
            )
        }
    }


    private fun isValidRes(res: Int) = res != View.NO_ID

    override fun onDetachedFromWindow() {
        clearAnimation()
        super.onDetachedFromWindow()
    }

    override fun dispatchDraw(canvas: Canvas?) { // child clipping done here
        if (isRounded)
            canvas?.clipPath(clipPath)
        super.dispatchDraw(canvas)
    }


    fun setProgress(p: Int) {
        if (p in 0..maxProgress) {
            clearAnimation()
            val animator = ValueAnimator.ofInt(oldProgress, p)
            animator.interpolator = AccelerateDecelerateInterpolator()
            animator.addUpdateListener { anm ->
                currentProgress = anm.animatedValue as Int
                updateRect(rectF = progressRectF)
                ViewCompat.postInvalidateOnAnimation(this)
            }
            animator.doOnEnd { doOnProgressEnd?.invoke(this); if (!isRestart) oldProgress = p }
            animator.setDuration(0).start()
        }
    }


    fun setProgressColors(@ColorRes resIds: IntArray, extractResColor: Boolean = true) {
        try {
            val filtered = resIds.filter { isValidRes(it) }
            mGradientColors = IntArray(filtered.size)
            filtered.forEachIndexed { index, i ->
                mGradientColors[index] =
                    if (extractResColor) ContextCompat.getColor(context, i) else i
            }
            initPaint()
        } catch (e: Exception) {

        }
    }


   private fun setCornerRadius(radius: Float) {
        if (radius in 0f..maxProgress.toFloat()) {
            setRoundedCorners(true)
            this.mCornerRadius = radius
        }
    }


  private  fun setRoundedCorners(isRounded: Boolean) {
        this.isRounded = isRounded
    }

  private  fun setGradientMovement(gradMovement: Boolean) {
        this.gradientMovement = gradMovement
    }


  private  fun shouldStartFromZero(isRestart: Boolean) {
        this.isRestart = isRestart
    }


    private fun getGradientRect(progressRect: RectF): RectF {
        val outRect = RectF(progressRect)
                outRect.left = progressRect.left
                outRect.top = progressRect.centerY()
                outRect.right = progressRect.right
                outRect.bottom = progressRect.centerY()
        return outRect
    }


    override fun setBackground(background: Drawable?) {}

    override fun setBackgroundColor(color: Int) {}

    override fun setBackgroundResource(resid: Int) {}

    override fun setBackgroundDrawable(background: Drawable?) {}


}