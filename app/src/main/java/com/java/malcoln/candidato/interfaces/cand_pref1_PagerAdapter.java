package com.java.malcoln.candidato.interfaces;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.java.malcoln.candidato.tab_fragment_prefeitos.cand_pref1_tab1_frag;
import com.java.malcoln.candidato.tab_fragment_prefeitos.cand_pref1_tab2_frag;



/**
 * Created by T.I on 14/07/2016.
 */
public class cand_pref1_PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public cand_pref1_PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                cand_pref1_tab1_frag tab1 = new cand_pref1_tab1_frag();
                return tab1;
            case 1:
                cand_pref1_tab2_frag tab2 = new cand_pref1_tab2_frag();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
