package com.ui.demo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ui.demo.fragments.FragmentOne;
import com.ui.demo.fragments.FragmentTwo;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new FragmentOne();
        } else {
            return new FragmentTwo();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
