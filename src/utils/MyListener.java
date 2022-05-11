package utils;

import entity.Boutique;
import entity.Produit;

public interface MyListener {
    public void onClickListener(Produit prod);
    public void onClickListener2(Boutique bout);

}
