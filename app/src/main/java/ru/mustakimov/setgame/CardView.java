package ru.mustakimov.setgame;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by mikhail on 18/05/16.
 */
public class CardView extends LinearLayout {

    private ImageView[] mImages;
    private Context mContext;

    private CardType cardType;
    private CardColor cardColor;
    private ShadingType shadingType;
    private int cardCount;

    public CardView(Context context, AttributeSet attrs) {
        super(context);
        mContext = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CardView,
                0, 0);

        try {
            int mCardType = a.getInt(R.styleable.CardView_cardType, 0);
            int mCardCount = a.getInt(R.styleable.CardView_cardCount, 3);
            int mShadingType = a.getInt(R.styleable.CardView_shadingType, 0);
            int mCardColor = a.getInt(R.styleable.CardView_cardColor, 0);

            cardCount = mCardCount;
            switch (mCardType){
                case 0: cardType = CardType.DIAMOND; break;
                case 1: cardType = CardType.SQUIGGLE; break;
                case 2: cardType = CardType.OVAL; break;
            }
            switch (mCardColor){
                case 0: cardColor = CardColor.BLUE; break;
                case 1: cardColor = CardColor.GREEN; break;
                case 2: cardColor = CardColor.RED; break;
            }
            switch (mShadingType){
                case 0: shadingType = ShadingType.SOLID; break;
                case 1: shadingType = ShadingType.STRIPED; break;
                case 2: shadingType = ShadingType.OPEN; break;
            }
        } finally {
            a.recycle();
        }
        initAllMagic(cardCount);
    }

    public CardView(CardType cardType, CardColor cardColor, int cardCount, Context context) {
        super(context);
        mContext = context;

        this.cardType = cardType;
        this.cardCount = cardCount;
        this.cardColor = cardColor;

        initAllMagic(cardCount);
    }

    private void initAllMagic(int cardCount) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_view, this, true);

        mImages = new ImageView[3];
        for (int i = 0; i < mImages.length; i++) {
            mImages[i] = (ImageView) getChildAt(i);
        }

        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);

        setImages();
        setCardCount(cardCount);
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
        setImages();
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
        setImages();
    }

    public void setShadingType(ShadingType shadingType) {
        this.shadingType = shadingType;
        setImages();
    }

    public void setCardCount(int count) {
        cardCount = count;
        for (int i = 0; i < mImages.length; i++) {
            if (i < cardCount) {
                mImages[i].setVisibility(View.VISIBLE);
            } else {
                mImages[i].setVisibility(View.GONE);
            }
        }
    }

    private int getRightImageId(CardColor color, ShadingType shadingType, CardType cardType) {
        switch (cardType) {
            case OVAL:
                switch (shadingType){
                    case SOLID:
                        switch (color) {
                            case BLUE:  return R.drawable.oval_solid_blue;
                            case RED:   return R.drawable.oval_solid_red;
                            case GREEN: return R.drawable.oval_solid_green;
                        }
                    case STRIPED:
                        switch (color) {
                            case BLUE:  return R.drawable.oval_striped_blue;
                            case RED:   return R.drawable.oval_striped_red;
                            case GREEN: return R.drawable.oval_striped_green;
                        }
                    case OPEN:
                        switch (color) {
                            case BLUE:  return R.drawable.oval_open_blue;
                            case RED:   return R.drawable.oval_open_red;
                            case GREEN: return R.drawable.oval_open_green;
                        }
                }
                break;
            case DIAMOND:
                switch (shadingType){
                    case SOLID:
                        switch (color) {
                            case BLUE:  return R.drawable.diamond_solid_blue;
                            case RED:   return R.drawable.diamond_solid_red;
                            case GREEN: return R.drawable.diamond_solid_green;
                        }
                    case STRIPED:
                        switch (color) {
                            case BLUE:  return R.drawable.diamond_striped_blue;
                            case RED:   return R.drawable.diamond_striped_red;
                            case GREEN: return R.drawable.diamond_striped_green;
                        }
                    case OPEN:
                        switch (color) {
                            case BLUE:  return R.drawable.diamond_open_blue;
                            case RED:   return R.drawable.diamond_open_red;
                            case GREEN: return R.drawable.diamond_open_green;
                        }
                }
                break;
            case SQUIGGLE:
                switch (shadingType){
                    case SOLID:
                        switch (color) {
                            case BLUE:  return R.drawable.squiggle_solid_blue;
                            case RED:   return R.drawable.squiggle_solid_red;
                            case GREEN: return R.drawable.squiggle_solid_green;
                        }
                    case STRIPED:
                        switch (color) {
                            case BLUE:  return R.drawable.squiggle_striped_blue;
                            case RED:   return R.drawable.squiggle_striped_red;
                            case GREEN: return R.drawable.squiggle_striped_green;
                        }
                    case OPEN:
                        switch (color) {
                            case BLUE:  return R.drawable.squiggle_open_blue;
                            case RED:   return R.drawable.squiggle_open_red;
                            case GREEN: return R.drawable.squiggle_open_green;
                        }
                }
                break;
        }

        return 0;
    }

    private void setImages() {
        for (int i = 0; i < mImages.length; i++) {
            mImages[i].setImageResource(getRightImageId(cardColor, shadingType, cardType));
        }
    }

}
