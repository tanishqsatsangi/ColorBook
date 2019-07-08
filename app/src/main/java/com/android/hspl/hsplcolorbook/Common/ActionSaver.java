package com.android.hspl.hsplcolorbook.Common;

import android.graphics.Point;

public class ActionSaver {

    Point p;
    int sourcecolor;
    int replacementcolor;

    public ActionSaver(Point p, int sourcecolor, int replacementcolor) {
        this.p = p;
        this.sourcecolor = sourcecolor;
        this.replacementcolor = replacementcolor;
    }


    public Point getP() {
        return p;
    }

    public int getSourcecolor() {
        return sourcecolor;
    }

    public int getReplacementcolor() {
        return replacementcolor;
    }
}
