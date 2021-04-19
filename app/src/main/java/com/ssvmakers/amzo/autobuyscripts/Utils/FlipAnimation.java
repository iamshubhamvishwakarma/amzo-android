package com.ssvmakers.amzo.autobuyscripts.Utils;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class FlipAnimation extends Animation {
    private Camera camera;
    private float centerX;
    private float centerY;
    private boolean forward = true;
    private View fromView;
    private View toView;

    public FlipAnimation(View view, View view2) {
        this.fromView = view;
        this.toView = view2;
        setDuration(700);
        setFillAfter(false);
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    /* Access modifiers changed, original: protected */
    public void applyTransformation(float f, Transformation transformation) {
        float f2 = (float) ((180.0d * (((double) f) * 3.141592653589793d)) / 3.141592653589793d);
        if (f >= 0.5f) {
            f2 -= 180.0f;
            this.fromView.setVisibility(8);
            this.toView.setVisibility(0);
        }
        if (this.forward) {
            f2 = -f2;
        }
        Matrix matrix = transformation.getMatrix();
        this.camera.save();
        this.camera.rotateX(f2);
        this.camera.getMatrix(matrix);
        this.camera.restore();
        matrix.preTranslate(-this.centerX, -this.centerY);
        matrix.postTranslate(this.centerX, this.centerY);
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.centerX = (float) (i / 2);
        this.centerY = (float) (i2 / 2);
        this.camera = new Camera();
    }

    public void reverse() {
        this.forward = false;
        View view = this.toView;
        this.toView = this.fromView;
        this.fromView = view;
    }
}
