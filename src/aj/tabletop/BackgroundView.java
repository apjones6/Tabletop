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
	private final Paint paint = new Paint();
	
	public BackgroundView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        
        paint.setColor(Color.GRAY);
        setFocusable(false);
    }
	
	@Override
    public void onDraw(Canvas canvas)
    {
		final int width = getWidth();
		final int height = getHeight();
		
		for (int i = 0; i < width; i += GRID_SIZE)
		{
			canvas.drawLine(i, 0, i, height, paint);
		}
		
		for (int i = 0; i < height; i += GRID_SIZE)
		{
			canvas.drawLine(0, i, width, i, paint);
		}
    }
}
