package by.paranoidandroid.notesapp.utils;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ItemDecoration that adds top and bottom margin around items of RecyclerView.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Context context;
    private int space;

    /**
     * @param space The DP space around child views in RecyclerView.
     */
    public DividerItemDecoration(Context context, int space) {
        this.context = context;
        this.space = convertDpToPx(space);
    }

    /**
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate.
     * @param parent  RecyclerView this ItemDecoration is decorating.
     * @param state   The current state of RecyclerView.
     */
    public void getItemOffsets(@NonNull android.graphics.Rect outRect,
                               @NonNull View view,
                               @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        outRect.top = space;
        outRect.bottom = space;
    }

    private int convertDpToPx(int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
