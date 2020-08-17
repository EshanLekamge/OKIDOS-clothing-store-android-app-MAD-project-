package com.store.okidosmobileapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_categories, container, false);
        Button buttonmen = (Button)view.findViewById(R.id.buttonMen);
        Button buttonwomen = (Button) view.findViewById(R.id.buttonWomen);

        buttonmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.linearcatogires, new MenCatagoriesFragment());
                    fragmentTransaction.commit();




            }


        });

        buttonwomen.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.linearcatogires, new WomenCatogoriesFragment());
                fragmentTransaction.commit();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}