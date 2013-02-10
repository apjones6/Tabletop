package aj.tabletop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawView extends View implements OnTouchListener
{
    private final Paint mPaint = new Paint();
    private final Path mPath = new Path();
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Mode mMode;
    
    public DrawView(Context context)
    {
        super(context);
        initialize();
    }

    public DrawView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initialize();
    }
    
    private void initialize()
    {
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        
    	setFocusable(true);
        setFocusableInTouchMode(true);
        setMode(Mode.Draw);
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }
    
    public Mode getMode()
    {
    	return mMode;
    }
    
    public void setMode(Mode mode)
    {
    	this.mMode = mode;
    	
    	switch (mode)
    	{
    	case Draw:
    		mPaint.setColor(Color.BLACK);
    		mPaint.setXfermode(null);
            mPaint.setStrokeWidth(5);
			setOnTouchListener(this);
			break;
    		
    	case Erase:
    		mPaint.setColor(Color.TRANSPARENT);
    		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            mPaint.setStrokeWidth(15);
			setOnTouchListener(this);
			break;
			
    	case None:
			setOnTouchListener(null);
			break;
    	}
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event)
    {
    	final float x = event.getX();
    	final float y = event.getY();
    	
    	switch (event.getAction())
    	{
    	case MotionEvent.ACTION_DOWN:
    		mPath.reset();
    		mPath.moveTo(x, y);
	        invalidate();
	        break;
	        
    	case MotionEvent.ACTION_MOVE:
    		mPath.lineTo(x, y);
    		if (mMode == Mode.Erase)
    		{
    			mCanvas.drawPath(mPath, mPaint);
	    		mPath.reset();
    		}
    		
    		mPath.moveTo(x, y);
    		invalidate();
	        break;
    		
    	case MotionEvent.ACTION_UP:
    		mPath.lineTo(x, y);
    		mCanvas.drawPath(mPath, mPaint);
    		mPath.reset();
    		invalidate();
    		break;
    	}
    	
        return true;
    }
}
