package aj.tabletop;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawView extends View implements OnTouchListener
{
    ArrayList<List<Point>> lines = new ArrayList<List<Point>>();
    Paint paint = new Paint();

    public DrawView(Context context)
    {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setOnTouchListener(this);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
    	for (List<Point> line : lines)
    	{
    		Point point = line.get(0);
    		for (int i = 1; i < line.size(); ++i)
    		{
    			Point next = line.get(i);
    			canvas.drawLine(point.x, point.y, next.x, next.y, paint);
    			point = next;
    		}
    	}
    }

    public boolean onTouch(View view, MotionEvent event)
    {
    	List<Point> line;
    	
    	switch (event.getAction())
    	{
	    	case MotionEvent.ACTION_DOWN:
	    		line = new ArrayList<Point>();
	    		line.add(new Point(event.getX(), event.getY()));
		        lines.add(line);
		        
	    	case MotionEvent.ACTION_MOVE:
	    		line = lines.get(lines.size() - 1);
	    		line.add(new Point(event.getX(), event.getY()));
    	}
    	
        invalidate();
        return true;
    }
}

class Point
{
    float x, y;
    
    public Point(float x, float y)
    {
    	this.x = x;
    	this.y = y;
    }

    @Override
    public String toString()
    {
        return x + ", " + y;
    }
}