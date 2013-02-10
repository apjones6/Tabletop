package aj.tabletop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BackgroundView extends View
{
	private static final int GRID_SIZE = 50;
	private final Paint mPaint = new Paint();

	public BackgroundView(Context context)
    {
        super(context);
        initialize();
    }
	
	public BackgroundView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initialize();
    }
	
	private void initialize()
	{
		mPaint.setColor(Color.GRAY);
        setFocusable(false);
	}
	
	@Override
    public void onDraw(Canvas canvas)
    {
		final int width = getWidth();
		final int height = getHeight();
		
		for (int i = 0; i < width; i += GRID_SIZE)
		{
			canvas.drawLine(i, 0, i, height, mPaint);
		}
		
		for (int i = 0; i < height; i += GRID_SIZE)
		{
			canvas.drawLine(0, i, width, i, mPaint);
		}
    }
}
