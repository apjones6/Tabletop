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
    private final Paint paint = new Paint();
    private final Path path = new Path();
    private Bitmap bitmap;
    private Canvas canvas;
    private Mode mode;
    
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
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        
    	setFocusable(true);
        setFocusableInTouchMode(true);
        setMode(Mode.Draw);
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }
    
    public Mode getMode()
    {
    	return mode;
    }
    
    public void setMode(Mode mode)
    {
    	this.mode = mode;
    	
    	switch (mode)
    	{
    	case Draw:
    		paint.setColor(Color.BLACK);
    		paint.setXfermode(null);
            paint.setStrokeWidth(5);
			setOnTouchListener(this);
			break;
    		
    	case Erase:
    		paint.setColor(Color.TRANSPARENT);
    		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            paint.setStrokeWidth(15);
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
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event)
    {
    	float x = event.getX();
    	float y = event.getY();
    	
    	switch (event.getAction())
    	{
	    	case MotionEvent.ACTION_DOWN:
	    		path.reset();
	    		path.moveTo(x, y);
		        invalidate();
		        break;
		        
	    	case MotionEvent.ACTION_MOVE:
	    		path.lineTo(x, y);
	    		if (mode == Mode.Erase)
	    		{
	    			canvas.drawPath(path, paint);
		    		path.reset();
	    		}
	    		
	    		path.moveTo(x, y);
	    		invalidate();
		        break;
	    		
	    	case MotionEvent.ACTION_UP:
	    		path.lineTo(x, y);
	    		canvas.drawPath(path, paint);
	    		path.reset();
	    		invalidate();
	    		break;
    	}
    	
        return true;
    }
}

enum Mode
{
	None,
	Draw,
	Erase
}