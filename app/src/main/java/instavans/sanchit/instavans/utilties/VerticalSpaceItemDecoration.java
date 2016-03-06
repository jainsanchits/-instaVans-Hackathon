package instavans.sanchit.instavans.utilties;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by sanchitjain on 26/10/15.
 */
public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration  {
    private int horizontalSpace,verticalSpace;

    public VerticalSpaceItemDecoration(int verticalSpace, int horizontalSpace) {
        this.horizontalSpace = horizontalSpace;
        this.verticalSpace = verticalSpace;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        if(parent.getChildLayoutPosition(view)==0){
           // outRect.left =horizontalSpace;
            outRect.bottom=verticalSpace;
           // outRect.right = horizontalSpace;
        }
        else
        {
            //outRect.left =horizontalSpace;
            outRect.top =verticalSpace;
            outRect.bottom=verticalSpace;
           // outRect.right = horizontalSpace;
        }

    }
}
