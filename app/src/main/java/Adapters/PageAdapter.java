package Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appointment_system_patient.FragmentOne;
import com.example.appointment_system_patient.FragmentTwo;

public class PageAdapter extends FragmentStatePagerAdapter {
        int tabCount;

        //Constructor to the class
    public PageAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            //Initializing tab count
            this.tabCount= tabCount;
        }

        //Overriding method getItem
        @Override
        public Fragment getItem(int position) {
            //Returning the current tabs
            switch (position) {
                case 0:
                    FragmentOne tab1 = new FragmentOne();
                    return tab1;
                case 1:
                    FragmentTwo tab2 = new FragmentTwo();
                    return tab2;

                default:
                    return null;
            }
        }

        //Overriden method getCount to get the number of tabs
        @Override
        public int getCount() {
            return tabCount;
        }

    }
